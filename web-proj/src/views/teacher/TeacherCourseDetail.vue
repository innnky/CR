<template>
  <div class="container h-100">
    <div class="row  align-items-center" style="height: 100%;">
      <div class="col-12 bg-white mt-2 py-3" style="height: 20%">
        <div class="row h-100 align-items-center px-3">
          <div class="col-12"><h5>{{courseInfo.courseName}}</h5></div>
          <div class="col-12 px-4"><span>{{courseInfo.courseDescription}}</span></div>
        </div>
      </div>
      <div class="col-12 bg-white py-3 px-5 mb-3" style="height: 70%">
        <el-tabs v-model="activeName" @tab-click="handleClick">
          <el-tab-pane label="课程时间" name="first">
            <el-table
                :data="tableData"
                stripe
                style="width: 100%"
                height="400">
              <!--        <el-table-column-->
              <!--            prop="scourseId"-->
              <!--            label="计划id">-->
              <!--        </el-table-column>-->


              <el-table-column
                  prop="week"
                  label="教学周">
                <template slot-scope="scope">
                  <span>{{scope.row.week}}</span>
                </template>
              </el-table-column>
              <el-table-column
                  prop="weekday"
                  label="周几">
                <template slot-scope="scope">
                  <el-tag type="info">星期{{scope.row.weekday}}</el-tag>
                </template>
              </el-table-column>
              <el-table-column
                  prop="classSequence"
                  label="节次">
                <template slot-scope="scope">
                  <el-tag>第{{scope.row.weekday}}大节</el-tag>
                </template>
              </el-table-column>

            </el-table>

          </el-tab-pane>
          <el-tab-pane label="作业任务" name="second">
            <div class="row justify-content-end">
              <div class="col-1"><el-button @click="handelShowAdd">新增</el-button></div>
            </div>
            <!--          {
                  "exerciseId": 4,
                  "content": "阿斯达四大多所撒",
                  "startTime": null,
                  "endTime": null,
                  "attachmentPath": null,
                  "sceneId": null,
                  "scourseId": 3
                },-->
            <el-table
                :data="exerciseData"
                stripe
                style="width: 100%"
                height="400">
<!--              <el-table-column-->
<!--                  prop="exerciseId"-->
<!--                  label="作业id">-->
<!--              </el-table-column>-->
              <el-table-column
                  prop="content"
                  label="作业内容">
              </el-table-column>
              <el-table-column
                  prop="startTime"
                  label="开始时间">
              </el-table-column>
              <el-table-column
                  prop="endTime"
                  label="结束时间">
              </el-table-column>
              <el-table-column
                  prop="attachmentPath"
                  label="附件">
                <template slot-scope="scope">
                  <el-link :href="scope.row.attachmentPath"  type="primary">下载</el-link>
                </template>
              </el-table-column>
              <el-table-column
                  prop="sceneId"
                  label="场景">
              </el-table-column>
              <el-table-column
                  prop="scourseId"
                  label="课程">
              </el-table-column>
              <el-table-column
                  fixed="right"
                  label="操作" width="200">
                <template slot-scope="scope">
                  <el-button type="info" size="small" @click="handleShowEdit(scope.row)">编辑</el-button>
                  <el-button type="danger" size="small" @click="handleDeleteExercise(scope.row)">删除</el-button>
                </template>
              </el-table-column>
            </el-table>
          </el-tab-pane>
          <el-tab-pane label="课程资料" name="third">
            <div class="container">
              <div class="row justify-content-end">
                <div class="col-1">
<!--                  :action="'http://localhost:8081/teacher/material/file/'+this.scourseId"-->

                  <el-upload
                      :action="'http://localhost:8081/teacher/material/file/'+this.scourseId"
                      :file-list="fileList2"
                      :with-credentials='true'
                      :on-success="onSuccess2"
                      >
                    <el-button size="small" type="primary">上传</el-button>
                  </el-upload>
                </div>
              </div>
            </div>
            <!--          {
                  "materialId": 1,
                  "materialName": "sadas",
                  "updateTime": null,
                  "materialPath": null,
                  "scourseId": 3
                },-->
            <el-table
                :data="materialData"
                stripe
                style="width: 100%"
                height="400">
<!--              <el-table-column-->
<!--                  prop="materialId"-->
<!--                  label="资料id">-->
<!--              </el-table-column>-->
              <el-table-column
                  prop="materialName"
                  label="资料名称">
              </el-table-column>
              <el-table-column
                  prop="updateTime"
                  label="更新时间">
              </el-table-column>
              <el-table-column
                  prop="materialPath"
                  label="资料路径">
                <template slot-scope="scope">
                  <el-link :href="scope.row.materialPath"  type="primary">下载</el-link>
                </template>
              </el-table-column>
              <el-table-column
                  label="操作">
                <template slot-scope="scope">
                  <el-button type="danger" size="small" @click="handleDeleteMaterial(scope.row)">删除</el-button>
                </template>

              </el-table-column>

            </el-table>

          </el-tab-pane>
        </el-tabs>


      </div>



    </div>


    <el-dialog :title="dialogFormTitle" :visible.sync="dialogFormVisible" width="70%">
      <el-form :model="inputData">
        <div class="container">
          <div class="row">
            <div class="col-12">
              <el-form-item label="作业内容">
                <el-input v-model="inputData.content" class="w-75"></el-input>
              </el-form-item>
            </div>
            <div class="col-4">
              <span class="me-2">开始时间</span>
              <el-date-picker
                  v-model="inputData.startTime"
                  type="date"
                  placeholder="选择日期"
                  format="yyyy 年 MM 月 dd 日"
                  value-format="yyyy-MM-dd HH:mm:ss"
                  class="w-75">
              </el-date-picker>
            </div>
            <div class="col-4">
              <span class="me-2">截止时间</span>
              <el-date-picker
                  v-model="inputData.endTime"
                  type="date"
                  placeholder="选择日期"
                  format="yyyy 年 MM 月 dd 日"
                  value-format="yyyy-MM-dd HH:mm:ss"
                  class="w-75">
              </el-date-picker>
            </div>
            <div class="col-4">
              <el-form-item label="场景">
<!--                <el-input v-model="inputData.sceneId" class="w-75"></el-input>-->
                <el-select v-model="inputData.sceneId" placeholder="请选择场景" class="w-75">
                  <el-option
                      v-for="item in sceneList"
                      :key="item.sceneId"
                      :label="item.sceneName"
                      :value="item.sceneId">
                  </el-option>
                </el-select>
              </el-form-item>
            </div>
            <div class="col-4">
              <span>作业附件
              </span>
              <el-upload
                  v-if="dialogFormTitle==='添加'"
                  class="upload-demo w-50"
                  action="http://localhost:8081/teacher/exercise/file"
                  :file-list="fileList"
                  :with-credentials='true'
                  ref="imageUpload"
                  :on-success="onSuccess">
                <el-button size="small" type="primary">点击上传</el-button>
              </el-upload>
            </div>
          </div>
        </div>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="doAddOrUpdate">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import {delRequest, getRequest, postRequest, putRequest} from "@/api/data";

export default {
  name: "TeacherCourseDetail",
  data(){
    return{
      activeName:"first",
      tableData: [],
      exerciseData: [],
      materialData: [],
      scourseId: '',
      inputData: {
        content: '',
        startTime: '',
        endTime: '',
        sceneId: '',
        file: '',
      },
      dialogFormTitle: '',
      dialogFormVisible: false,
      fileList: [],
      fileList2: [],
      sceneList: [],
      courseInfo: {},
    }
  },
  mounted() {
    this.scourseId = this.$route.query.scourseId;
    getRequest('/teacher/course/' + this.scourseId).then(response => {
      this.courseInfo = response;
    });
    this.initTime()
    this.initExercise()
    this.initMaterial()
  },
  methods: {
    initTime(){
      getRequest("/teacher/course/"+this.scourseId+"/time").then(res=>{
        this.tableData = res;
      })
    },
    initExercise(){
      getRequest("/teacher/course/"+this.scourseId+"/exercise").then(res=>{
        this.exerciseData = res;
      })
      getRequest("/common/scene/list").then(res=>{
        this.sceneList = res;
      })

    },
    initMaterial(){
      getRequest("/teacher/course/"+this.scourseId+"/material").then(res=>{
        this.materialData = res;
      })

    },
    handelShowAdd(){
      this.inputData = {
      };
      this.fileList=[]
      this.dialogFormTitle = '添加';
      this.dialogFormVisible = true;
    },
    handleShowEdit(row){
      this.dialogFormTitle = '编辑';
      Object.assign(this.inputData, row);
      this.dialogFormVisible = true;
    },
    doAddOrUpdate(){
      if(this.dialogFormTitle === '编辑'){
        putRequest("/teacher/exercise/", this.inputData).then(() => {
          this.dialogFormVisible = false;
          this.initExercise();
        })
      }else{
        postRequest("/teacher/course/"+this.scourseId+"/exercise", this.inputData).then(() => {
          this.dialogFormVisible = false;
          this.initExercise();
        })
      }
    },
    onSuccess(response, file, fileList){
      this.inputData.attachmentPath = file.name;
      fileList
    },
    onSuccess2(response, file, fileList){
      this.fileList2=[];
      this.initMaterial();
      fileList
    },
    handleDeleteMaterial(row){
      this.$confirm('此操作将永久删除该项, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        delRequest("/teacher/material/"+row.materialId).then(() => {
          this.initMaterial();
        })
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        });
      });
    },
    handleDeleteExercise(id){
      this.$confirm('此操作将永久删除该项, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        delRequest("/teacher/exercise/"+id.exerciseId).then(() => {
          this.initExercise();
        })
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        });
      });
    },


  },
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