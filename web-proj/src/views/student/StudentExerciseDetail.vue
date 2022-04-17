<template>
<div class="container h-100">
  <div class="row  align-items-center" style="height: 100%;">
    <div class="col-12 bg-white mt-2 py-3" style="height: 20%">
      <div class="row h-100 align-items-center px-3">
        <div class="col-12"><h5>攻防训练任务1</h5></div>
        <div class="col-12 px-4"><span>教师：xxxxx</span></div>
        <div class="col-12 px-4"><span>简介：xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx</span></div>
      </div>
    </div>
    <div class="col-12 bg-white py-3 px-5 mb-3" style="height: 70%">
      <el-tabs v-model="activeName" @tab-click="handleClick">
        <el-tab-pane label="任务内容" name="first">

        <div class="row justify-content-center">
          <div class="col-11 mt-3">{{dta.content}}</div>

        </div>
        </el-tab-pane>
        <el-tab-pane label="任务附件" name="second">
          <el-table
              :data="tableData"
              style="width: 100%"
              max-height="300">
            <el-table-column
                prop="date"
                label="附件名称"
                width="500">
              <template slot-scope="scope">
                <i class="el-icon-notebook-2"></i>
                <span @click="showExerciseDetail()" style="margin-left: 10px;color: #7fb6ff;cursor: pointer">{{ scope.row.address }}</span>
              </template>

            </el-table-column>
            <el-table-column
                prop="name"
                label="时间"
                >
            </el-table-column>

          </el-table>
        </el-tab-pane>
        <el-tab-pane label="可用场景" name="third">
          <el-table
              :data="tableData"
              style="width: 100%"
              max-height="300">
            <el-table-column
                prop="date"
                label="场景id"
                width="180">
            </el-table-column>
            <el-table-column
                prop="name"
                label="场景名称"
                width="180">
            </el-table-column>
            <el-table-column
                prop="address"
                label="设备列表">
              <template >
                <span @click="showExerciseDetail()" style="margin-left: 10px;color: #7fb6ff;cursor: pointer">查看</span>
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
  name: "StudentExerciseDetail",
  data(){
    return{
      activeName:"first",
      dta:"",
      tableData: [],
      exerciseId: "",
    }
  },
  methods:{
    initData() {
      getRequest("/student/exercise/"+this.exerciseId).then((res)=>{
        this.dta = res;
      })
    }
  },
  mounted() {
    //从query中取出exerciseId
    this.exerciseId = this.$route.query.id
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