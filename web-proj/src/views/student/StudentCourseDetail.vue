<template>
<div class="container h-100">
  <div class="row  align-items-center" style="height: 100%;">
    <div class="col-12 bg-white mt-2 py-3" style="height: 20%">
      <div class="row h-100 align-items-center px-3">
        <div class="col-12"><h5>计算机网络</h5></div>
        <div class="col-12 px-4"><span>教师：xxxxx</span></div>
        <div class="col-12 px-4"><span>简介：xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx</span></div>
      </div>
    </div>
    <div class="col-12 bg-white py-3 px-5 mb-3" style="height: 70%">
      <el-tabs v-model="activeName" @tab-click="handleClick">
        <el-tab-pane label="课程时间" name="first">
          <el-table
              :data="tableData"
              style="width: 100%"
              max-height="300">
            <el-table-column
                prop="date"
                label="日期"
                width="180">
            </el-table-column>
            <el-table-column
                prop="name"
                label="姓名"
                width="180">
            </el-table-column>
            <el-table-column
                prop="address"
                label="地址">
            </el-table-column>
          </el-table>

        </el-tab-pane>
        <el-tab-pane label="作业任务" name="second">作业任务</el-tab-pane>
        <el-tab-pane label="课程资料" name="third">课程资料</el-tab-pane>
      </el-tabs>


    </div>



  </div>



</div>
</template>

<script>
export default {
  name: "StudentCourseDetail",
  data(){
    return{
      activeName:"first",
      tableData: [{
        date: '2016-05-02',
        name: '王小虎',
        address: '上海市普陀区金沙江路 1518 弄'
      }, {
        date: '2016-05-04',
        name: '王小虎',
        address: '上海市普陀区金沙江路 1517 弄'
      }, {
        date: '2016-05-01',
        name: '王小虎',
        address: '上海市普陀区金沙江路 1519 弄'
      }, {
        date: '2016-05-03',
        name: '王小虎',
        address: '上海市普陀区金沙江路 1516 弄'
      }, {
        date: '2016-05-03',
        name: '王小虎',
        address: '上海市普陀区金沙江路 1516 弄'
      }, {
        date: '2016-05-03',
        name: '王小虎',
        address: '上海市普陀区金沙江路 1516 弄'
      }, {
        date: '2016-05-03',
        name: '王小虎',
        address: '上海市普陀区金沙江路 1516 弄'
      }, {
        date: '2016-05-03',
        name: '王小虎',
        address: '上海市普陀区金沙江路 1516 弄'
      }, {
        date: '2016-05-03',
        name: '王小虎',
        address: '上海市普陀区金沙江路 1516 弄'
      }, {
        date: '2016-05-03',
        name: '王小虎',
        address: '上海市普陀区金沙江路 1516 弄'
      }, {
        date: '2016-05-03',
        name: '王小虎',
        address: '上海市普陀区金沙江路 1516 弄'
      }, {
        date: '2016-05-03',
        name: '王小虎',
        address: '上海市普陀区金沙江路 1516 弄'
      }, {
        date: '2016-05-03',
        name: '王小虎',
        address: '上海市普陀区金沙江路 1516 弄'
      }]
    }
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