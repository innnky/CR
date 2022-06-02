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
                <el-link :href="scope.row.attachmentPath"> 下载</el-link>
              </template>
            </el-table-column>
            <el-table-column
                prop="sceneId"
                label="场景">
            </el-table-column>
            <el-table-column
                prop="scourseId"
                label="操作">
              <template slot-scope="scope">
                <el-button type="primary" size="small" @click="handleComplete(scope.row)">去完成</el-button>
              </template>
            </el-table-column>
          </el-table>
        </el-tab-pane>
        <el-tab-pane label="课程资料" name="third">
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
<!--            <el-table-column-->
<!--                prop="materialId"-->
<!--                label="资料id">-->
<!--            </el-table-column>-->
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
                label="操作">
              <template slot-scope="scope">
                <el-link :href="scope.row.materialPath"> 下载</el-link>
              </template>
            </el-table-column>

          </el-table>

        </el-tab-pane>
      </el-tabs>


    </div>



  </div>



</div>
</template>

<script>
import {getRequest} from "@/api/data";

export default {
  name: "StudentCourseDetail",
  data(){
    return{
      activeName:"first",
      tableData: [],
      exerciseData: [],
      materialData: [],
      scourseId: '',
      courseInfo: {},
    }
  },
  mounted() {
    this.scourseId = this.$route.query.scourseId;
    getRequest("/student/course/"+this.scourseId).then(res=>{
      this.courseInfo = res;
    })
    this.initTime()
    this.initExercise()
    this.initMaterial()
  },
  methods: {
    initTime(){
      getRequest("/student/course/"+this.scourseId+"/time").then(res=>{
        this.tableData = res;
      })
    },
    initExercise(){
      getRequest("/student/course/"+this.scourseId+"/exercise").then(res=>{
        this.exerciseData = res;
      })

    },
    initMaterial(){
      getRequest("/student/course/"+this.scourseId+"/material").then(res=>{
        this.materialData = res;
      })


    },
    handleComplete(row){
      this.$router.push({
        path: "/StudentExerciseDetail",
        query: {
          id: row.exerciseId,
        }
      })
    }
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