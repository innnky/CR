<template>
  <div class="container h-100">
    <div class="row  align-items-center" style="height: 100%;">
      <div class="col-12 bg-white py-2 mb-0" style="height: 90%">

        <div class="row px-4 py-3">

          <el-table
              :data="tableData"
              stripe
              style="width: 100%"
              height="400">
            <el-table-column
                prop="studentNumber" width="150"
                label="学号">
            </el-table-column>
            <el-table-column
                prop="studentName" width="150"
                label="姓名">
            </el-table-column>

            <el-table-column
                prop="classInfo.className" width="230"
                label="班级">
              <template slot-scope="scope">
                <el-tag type="success">{{scope.row.classInfo.className}}</el-tag>
              </template>
            </el-table-column>
            <el-table-column
                prop="classInfo.college" width="150"
                label="院系">
            </el-table-column>
            <el-table-column
                prop="classInfo.major" width="150"
                label="专业">
            </el-table-column>
            <el-table-column
                prop="date" width="150"
                label="班级职务">
              <template>
                <el-button type="text" size="small">查看</el-button>
              </template>
            </el-table-column>
            <el-table-column
                prop="admissiondate" width="150"
                label="入学时间">
            </el-table-column>
            <el-table-column
                prop="gender" width="150"
                label="性别">
            </el-table-column>
            <el-table-column
                prop="contact" width="150"
                label="联系方式">
            </el-table-column>


          </el-table>


        </div>
      </div>
    </div>

    <el-dialog title="查看详情" :visible.sync="dialogVisible" width="60%">
      <div>
        <el-form :model="form" label-width="80px">
          <span>截图</span><br>
          <el-image
              v-for="(item,index) in form.image"
              :key="index"
              style="width: 40%;margin-left: 10px;margin-bottom: 10px;"
              :src="item"
              fit="fill"></el-image>
          <el-form-item label="结果分析">
            <el-input v-model="form.resultsAnalysis" disabled></el-input>
          </el-form-item>
          <el-form-item label="评分">
            <el-input v-model="form.score"></el-input>
          </el-form-item>

        </el-form>
      </div>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false">取 消</el-button>
        <el-button @click="submitMark">评 分</el-button>
      </div>
    </el-dialog>
  </div>

</template>

<script>

import {getRequest, postRequest} from "@/api/data";

export default {
  name: "TeacherClasses",
  data() {
    return {
      activeName:"first",
      tableData: [],
      tableMark: [],
      scid:'',
      form:{},
      score:'',
    }
  },
  methods: {
    initData(){
      getRequest("/teacher/class/").then(res=>{
        this.tableData = res;
      })
    },
    handleShowDetail(row){
      this.dialogVisible = true;
      this.form = row;
    },
    submitMark(){
      postRequest("/teacher/exercise/mark/"+this.form.id+"/"+this.form.score,{}).then(res=>{
        res
        this.dialogVisible = false;
        this.initData();
      })
    },
    doScore(row){
      // console.log(row);

      this.activeName = 'second'
      this.scid = row.scourseId
      this.initData()
    }
  },
  mounted() {
    this.initData();
  }
}
</script>

<style scoped>

</style>