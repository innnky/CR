<template>
  <div class="container h-100">
    <div class="row  align-items-center" style="height: 100%;">
      <div class="col-12 bg-white py-2 mb-0" style="height: 90%">
        <div class="row ">
          <div class="col-3">      <i class="el-icon-search m-2"></i><span class="smalltitle">账号</span></div>
        </div>
        <div class="row px-4  py-2">
          <el-tabs v-model="activeName" @tab-click="handleClick">
            <el-tab-pane label="基本信息" name="first">
              <div class="w-100 my-2"><span class="m-3">邮箱</span><el-input class="w-25"></el-input></div>
              <div class="w-100 my-2"><span class="m-3">个人简介</span><el-input class="w-25"></el-input></div>
              <el-button class="m-2 ms-1">提交修改</el-button>
            </el-tab-pane>
            <el-tab-pane label="修改密码" name="second">
              <div class="w-100 my-2"><span class="m-3">旧密码</span><el-input class="w-25"></el-input></div>
              <div class="w-100 my-2"><span class="m-3">新密码</span><el-input class="w-25"></el-input></div>
              <div class="w-100 my-2"><span class="m-3">新密码确认</span><el-input class="w-25"></el-input></div>
              <el-button class="m-2 ms-1">提交修改</el-button>
            </el-tab-pane>
          </el-tabs>

        </div>
      </div>
    </div>
  </div>

</template>

<script>
export default {
  name: "TeacherAccount",
  data() {
    return {
      activeName:"first",
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