<template>
<div class="container h-100">
  <div class="row  align-items-center" style="height: 100%;">

    <div class="col-12 bg-white py-3 px-5 mb-3" style="height: 90%">

      <el-tabs v-model="activeName" @tab-click="handleClick" >
        <el-tab-pane label="任务内容" name="first">
          <div class="row justify-content-between">
            <div class="col-8">          <span class="ms-1" style="font-size: 20px"> {{dta.content}}</span>

            </div>
            <div class="col-3"><el-link class="ms-3" :ref="dta.attachmentPath">下载附件</el-link>
              <el-button class="ms-3" type="primary" @click="handleComplete" :disabled="dta.submitTime!=null">去完成</el-button>
              <el-button class="ms-3" type="primary" @click="handleComplete" :disabled="dta.submitTime==null">查看</el-button>
            </div>
          </div>
          <span class="ms-2">开始时间  {{dta.startTime}}</span><br>
          <span class="ms-2">截止时间  {{dta.endTime}}</span>
        </el-tab-pane>
      </el-tabs>
      <el-tabs  class="mt-3">
        <el-tab-pane label="可用设备">

          <div v-if="reservation">
            <span class="ms-3">已预约:{{reservation.deviceName}}</span>
            <el-date-picker
                class="ms-3"
              v-model="reservation.date"
              type="date"
              placeholder="选择日期"
              disabled></el-date-picker>
            <span class="ms-3">第{{reservation.sequence}}节</span>
            <el-button type="primary" @click="handleDeleteReservation" class="ms-3">撤销</el-button>
          </div>
          <el-table
              class="mt-2"
              :data="tableData"
              style="width: 100%"
              max-height="300">
            <el-table-column
                prop="deviceName"
                label="设备名称"
                width="180">
            </el-table-column>
            <el-table-column
                prop="role"
                label="角色"
                width="180">
            </el-table-column>
            <el-table-column
                prop="address"
                label="设备列表">
              <template slot-scope="scope">
                <el-button @click="handleReservation(scope.row)" :disabled="reservation!=null || dta.submitTime!=null">预约</el-button>
              </template>

            </el-table-column>
          </el-table>

        </el-tab-pane>
      </el-tabs>
    </div>
    <el-dialog title="预约" :visible.sync="dialogFormVisibleReservation" width="50%">
      <el-form :model="inputData"  ref="form" label-width="80px">
        <el-form-item label="设备名称" prop="deviceName">
          <el-input v-model="form.deviceName" disabled></el-input>
        </el-form-item>
        <el-form-item label="角色" prop="role">
          <el-input v-model="form.role" disabled></el-input>
        </el-form-item>
        <el-form-item label="预约日期" prop="reservationTime">
          <el-date-picker
              v-model="inputData.date"
              type="date"
              value-format="yyyy-MM-dd HH:mm:ss"
              placeholder="选择日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="节次" prop="sequence">
          <el-select v-model="inputData.sequence" placeholder="请选择节次">
            <el-option
                v-for="item in [0,1,2,3,4,5,6,7]"
                :key="item"
                :label="'第'+(item+1)+'节'"
                :value="item">
            </el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisibleReservation = false">取 消</el-button>
        <el-button type="primary" @click="doReservation">确 定</el-button>
      </div>
    </el-dialog>

    <el-dialog title="完成作业" :visible.sync="dialogFormVisibleComplete" width="50%">
      <el-form :model="completeData"  ref="form" label-width="80px">
        <el-form-item label="结果分析" >
          <el-input :disabled="this.dta.submitTime!=null" v-model="completeData.resultsAnalysis"></el-input>
        </el-form-item>
        <el-form-item label="远程连接" v-if="this.dta.submitTime==null">

          <el-button type="primary" size="small" @click="handleConnect" v-if="vncStatus==='正在使用'">连接</el-button>
          <el-button type="danger" size="small"  v-else disabled>{{vncStatus}}</el-button>
        </el-form-item>
        <el-form-item label="结果截图" v-if="this.dta.submitTime==null">
          <el-upload
              class="upload-demo w-50"
              action="http://localhost:8081/student/exercise/image"
              :file-list="fileList"
              :with-credentials='true'
              ref="imageUpload"
              :on-success="uploadSuccess"
              :on-remove="onRemove">
            <el-button size="small" type="primary">点击上传</el-button>
          </el-upload>        </el-form-item>
        <div v-if="this.dta.submitTime!=null">
<!--        -->
          <el-image class="w-25 mx-3"  v-for="img in this.dta.image.split(',')" :key="img" :src="img"></el-image>
        </div>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisibleComplete = false">取 消</el-button>
        <el-button type="primary" @click="doSave" v-if="this.dta.submitTime===null">保 存</el-button>
        <el-button type="primary" @click="doComplete" v-if="this.dta.submitTime===null">确 定</el-button>
      </div>
    </el-dialog>

  </div>



</div>
</template>

<script>
import {delRequest, getRequest, postRequest} from "@/api/data";

export default {
  name: "StudentExerciseDetail",
  data(){
    return{
      activeName:"first",
      dta:"",
      tableData: [],
      exerciseId: "",
      form: {

      },
      inputData:{
        date: "",
        sequence: "",
        exerciseId: "",
      },
      dialogFormVisibleReservation: false,
      reservation:{},
      dialogFormVisibleComplete: false,
      completeData:{
        exerciseId: "",
        resultsAnalysis: "",
      },
      complete:false,
      fileList: [],
      vncAddress: "",
      vncStatus: '',

    }
  },
  methods:{
    initData() {
      const baseUrl = "http://localhost:8081/imgs/"

      getRequest("/student/exercise/"+this.exerciseId).then((res)=>{
        this.dta = res;

        // alert(this.dta.sceneId)
        getRequest("/student/device/all/"+this.dta.sceneId).then((r2)=>{
          this.tableData = r2;
          getRequest("/student/device/reservation/"+this.exerciseId).then((res3)=>{
            this.reservation = res3;
            if (res3!=null){
              this.tableData.forEach((item)=>{
                if (item.deviceId === res3.deviceId){
                  this.reservation.deviceName = item.deviceName;
                }
              })
            }

            if(this.complete){
              this.handleComplete()
            }
          })
        })
        this.fileList = this.dta.image.split(",").map(item=>{
          //item删除前面的baseUrl
          return {
            name: item.replace(baseUrl, ""),
            percent: 100,
            status: 'success'
          }
        })

      })

    },
    handleReservation(row){
      this.form.deviceName = row.deviceName;
      this.form.role = row.role;
      this.form.deviceId = row.deviceId;
      this.dialogFormVisibleReservation = true;
    },
    handleDeleteReservation(){
      delRequest("/student/device/"+this.reservation.deviceId+"/"+this.exerciseId).then((res)=>{
        res
        this.reservation = null;
        this.initData();
      })
    },
    doReservation(){
      this.inputData.exerciseId = this.exerciseId;
      postRequest("/student/device/"+this.form.deviceId, this.inputData).then((res)=>{
        res
        this.dialogFormVisibleReservation = false;
        this.initData();
      })
    },
    handleComplete(){
      // if(this.dta.submitTime!=null){
      //   this.$refs.analysis.disabled = true;
      // }
      this.getVncAddress()
      this.completeData.resultsAnalysis = this.dta.resultAnalysis;
      this.completeData.exerciseId = this.exerciseId;
      this.dialogFormVisibleComplete = true;
      //将fileList从["img1","img2"]转化为"img1,img2"并保存在this.completeData.image
      //逆向操作
      //将dta.image转化为 ["img1","img2"] 并保存在fileList, 每一个元素删除前面的baseUrl

      console.log(this.fileList);

    },
    doComplete(){
      //将fileList从["img1","img2"]转化为"img1,img2"并保存在this.completeData.image
      console.log(this.fileList);
      const baseUrl = "http://localhost:8081/imgs/"
      this.completeData.image = this.fileList.map(item => baseUrl+item.name).join(",");

      postRequest("/student/exercise/"+this.exerciseId, this.completeData).then((res)=>{
        res
        this.dialogFormVisibleComplete = false;
        this.initData();
      })
    },
    doSave(){
      //将fileList从["img1","img2"]转化为"img1,img2"并保存在this.completeData.image
      console.log(this.fileList);
      const baseUrl = "http://localhost:8081/imgs/"
      this.completeData.image = this.fileList.map(item => baseUrl+item.name).join(",");

      postRequest("/student/exercise/"+this.exerciseId+"/save", this.completeData).then((res)=>{
        res
        this.dialogFormVisibleComplete = false;
        this.initData();
      })
    },
    uploadSuccess(res, file, fileList){
      // console.log(this.fileList);
      this.fileList = fileList
    },
    onRemove(file, fileList){
      console.log(this.fileList);
      this.fileList = fileList;
    },
    getVncAddress(){
      getRequest("student/device/"+this.exerciseId+"/vnc").then((res)=>{
        this.vncAddress = res.vncUrl;
        this.vncStatus = res.status;
      })
    },
    handleConnect(){
      window.open(this.vncAddress);
    },


  },
  mounted() {
    //从query中取出exerciseId
    this.exerciseId = this.$route.query.id
    this.complete = false

    this.initData()
  }
}
</script>

<style scoped>
.container{
  color: #505050;

}
span{
  font-size: 13px;
}
</style>