<template>
  <div class="container h-100">
    <div class="row  align-items-center" style="height: 100%;">
      <div class="col-12 bg-white py-2 mb-0" style="height: 90%">
        <div class="row ">
          <div class="col-3">      <i class="el-icon-search m-2"></i><span class="smalltitle">我的作业</span></div>
        </div>
        <div class="row py-2 px-4 ">
          <el-table
              :data="tableData"
              stripe
              style="width: 100%"
              max-height="360">
            <el-table-column
                prop="name"
                label="作业任务">
              <template slot-scope="scope">
                <i class="el-icon-time"></i>
                <span @click="showExerciseDetail(scope.row)" style="margin-left: 10px;color: #7fb6ff;cursor: pointer">{{ scope.row.content }}</span>
              </template>
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
                prop="scourseId"
                label="所属课程">
            </el-table-column>
            <el-table-column
                prop="sceneId"
                label="场景">

            </el-table-column>
            <el-table-column
                prop="status"
                label="状态">
              <template slot-scope="scope">
                <el-tag size="medium" :href="scope">查看</el-tag>
              </template>
            </el-table-column>
            <el-table-column
                label="操作">
              <template slot-scope="scope">
                <el-button size="small" type="primary" @click="handleComplete(scope.row)">去完成</el-button>
              </template>
            </el-table-column>
            <el-table-column
                prop="score"
                label="成绩">
            </el-table-column>

          </el-table>

        </div>
      </div>
    </div>

  </div>

</template>

<script>
import {getRequest} from "@/api/data";

export default {
  name: "StudentExercise",
  data() {
    return {
      activeName:"first",
      tableData: [],
      dialogFormVisibleComplete: false,
      inputData:{},
    }
  },
  methods:{
    showExerciseDetail(row){
      console.log(111);
      // "/StudentExerciseDetail"
      this.$router.push({
        path: "/StudentExerciseDetail",
        query: {
          id: row.exerciseId
        }
      })
    },
    initData(){
      getRequest("/student/exercise/").then(res=>{
        this.tableData = res;
      })
    },
    handleComplete(row){
      this.$router.push({
        path: "/StudentExerciseDetail",
        query: {
          id: row.exerciseId,
          complete: true
        }
      })
    },
  },
  mounted(){
    this.initData();
  }
}
</script>

<style scoped>

</style>