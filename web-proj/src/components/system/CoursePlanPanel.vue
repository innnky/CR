<template>
  <div>
    <div class="row px-3">
      <div class="col-3">
        <span>班级名：</span>
        <el-select v-model="value" class="w-75" placeholder="请选择" >
          <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value">
          </el-option>
        </el-select>
      </div>
      <div class="col-3">
        <span>课程名：</span>
        <el-select class="w-75" v-model="value" placeholder="请选择">
          <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value">
          </el-option>
        </el-select>
      </div>

      <div class="col-3">
        <span>教师名：</span>
        <el-select class="w-75" v-model="value" placeholder="请选择">
          <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value">
          </el-option>
        </el-select>
      </div>
      <div class="col-1 offset-1">      <el-button  class="" size="small" >查询</el-button></div>
      <div class="col-1 ">      <el-button  class="" size="small" >新增</el-button></div>

    </div>
    <el-table
        :data="tableData"
        stripe
        style="width: 100%"
        max-height="360">
      <el-table-column
          prop="date"
          label="计划id">
      </el-table-column>

      <el-table-column
          prop="date"
          label="课程名称">
      </el-table-column>
      <el-table-column
          prop="date"
          label="授课教师">
      </el-table-column>
      <el-table-column
          prop="date"
          label="授课班级">
        <el-tag>查看</el-tag>
      </el-table-column>
      <el-table-column
          prop="date"
          label="学期">
      </el-table-column>
      <el-table-column
          prop="date"
          label="操作">
        <template>
          <el-button type="info" size="small">编辑</el-button>
          <el-button type="danger" size="small">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
export default {
  name: "CoursePlanPanel",
  data(){
    return{
      tableData: [
        {
          date: '2016-05-02',
          name: '王小虎',
          address: '上海市普陀区金沙江路 1518 弄'
        },
        {
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
        }]
    }
  }
}
</script>

<style scoped>

</style>