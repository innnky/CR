<template>
  <div class="container h-100">
    <div class="row  align-items-center" style="height: 100%;">
      <div class="col-12 bg-white py-2 mb-0" style="height: 90%">

        <div class="row px-4 py-3">
          <el-tabs v-model="activeName" @tab-click="handleClick">
            <el-tab-pane label="全部作业" name="first">

              <el-table
                  :data="tableAll"
                  stripe
                  style="width: 100%"
                  max-height="360">
                <el-table-column
                    prop="content"
                    label="作业任务">
                </el-table-column>
                <el-table-column
                    prop="course.courseName"
                    label="从属课程">
                </el-table-column>
                <el-table-column
                    prop="startTime"
                    label="开始时间">
                </el-table-column>
                <el-table-column
                    prop="endTime"
                    label="截止时间">
                </el-table-column>
                <el-table-column
                    prop="date"
                    label="班级">
                  <template slot-scope="scope">
                    <span v-for="item in scope.row.classInfoList" :key="item.classId">{{item.className}} </span>
                  </template>
                </el-table-column>
                <el-table-column
                    prop="submittedCount"
                    label="已提交">
                  <template slot-scope="scope">
                    <span >{{scope.row.submittedCount}}/{{scope.row.studentCount}} </span>
                  </template>
                </el-table-column>
                <el-table-column
                    prop="markedCount"
                    label="已评阅">
                  <template slot-scope="scope">
                    <span >{{scope.row.markedCount}}/{{scope.row.submittedCount}} </span>
                  </template>
                </el-table-column>
                <el-table-column
                    label="状态">
                  <template >
                    <el-tag type="success" >已发布</el-tag>
                  </template>
                </el-table-column>
                <el-table-column
                    label="操作"
                    width="200">
                  <template slot-scope="scope">
                    <el-button type="primary" size="small" @click="doScore(scope.row)">评分</el-button>
                  </template>
                </el-table-column>
              </el-table>
            </el-tab-pane>
            <el-tab-pane label="作业评分" name="second">

              <el-form :model="exerciseId">
                <div class="row px-3">
                  <div class="col-4">
                    <el-form-item label="作业">
                      <el-input placeholder="请输入" class="w-75" v-model="exerciseId"></el-input>
                    </el-form-item>
                  </div>

                  <div class="col-1 offset-7">      <el-button  class="" size="small"  @click="initData">查询</el-button></div>

                </div>

              </el-form>
              <el-table
                  :data="tableMark"
                  stripe
                  style="width: 100%"
                  max-height="360">
                <el-table-column
                    prop="content"
                    label="作业任务">
                </el-table-column>
                <el-table-column
                    prop="courseName"
                    label="从属课程">
                </el-table-column>
                <el-table-column
                    prop="studentName"
                    label="学生">
                </el-table-column>
                <el-table-column
                    prop="className"
                    label="班级">
                </el-table-column>
                <el-table-column
                    prop="submitTime"
                    label="提交时间">
                </el-table-column>
                <el-table-column
                    label="详情">
                  <template slot-scope="scope">
                    <el-button type="primary" size="small" @click="handleShowDetail(scope.row)">查看</el-button>
                  </template>
                </el-table-column>
                <el-table-column
                    label="状态">
                  <template slot-scope="scope">
<!--                    若scope.row.submitTime为空则显示未提交,若scope.row.score不为空则显示已批改,此外显示未批改-->
                    <el-tag type="danger" v-if="scope.row.submitTime==null">未提交</el-tag>
                    <el-tag type="success" v-else-if="scope.row.score!=null">已批改</el-tag>

                    <el-tag type="warning" v-else>未批改</el-tag>

                  </template>
                </el-table-column>
<!--                <el-table-column-->
<!--                    prop="date"-->
<!--                    label="操作"-->
<!--                    width="200">-->
<!--                  <template slot-scope="scope">-->
<!--                    <el-button type="info" size="small" @click="handleShowEdit(scope.row)">编辑</el-button>-->
<!--                    <el-button type="danger" size="small" @click="handleDelete(scope.row)">删除</el-button>-->
<!--                  </template>-->

<!--                </el-table-column>-->
              </el-table>
            </el-tab-pane>
          </el-tabs>

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
  name: "TeacherExercise",
  data() {
    return {
      activeName:"first",
      tableAll: [],
      tableMark: [],
      exerciseId:'',
      dialogVisible: false,
      form:{},
      score:'',
    }
  },
  methods: {
    initData(){
      getRequest("/teacher/exercise/").then(res=>{
        this.tableAll = res;
      })
      getRequest("/teacher/exercise/mark",{
        exerciseId: this.exerciseId
      }).then(res=>{
        this.tableMark = res;
        for(let i=0;i<this.tableMark.length;i++){
          this.tableMark[i].image = this.tableMark[i].image.split(",");
        }
        console.log(res);
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
      this.exerciseId = row.exerciseId;
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