<template>
  <div class="container h-100">
    <div class="row  align-items-center" style="height: 100%;">
      <div class="col-12 bg-white py-2 mb-0" style="height: 90%">
        <div class="row ">
          <div class="col-3">      <i class="el-icon-search m-2"></i><span class="smalltitle">账号管理</span></div>
        </div>
        <div class="row px-4  py-2">
          <el-tabs v-model="activeName" @tab-click="handleClick">
            <el-tab-pane label="学生账号" name="first">
              <student-account-panel/>
            </el-tab-pane>
            <el-tab-pane label="教师账号" name="second">
              <teacher-account-panel/>
            </el-tab-pane>
            <el-tab-pane label="管理员" name="third">
              <admin-account-panel/>
            </el-tab-pane>
          </el-tabs>

        </div>
      </div>
    </div>
  </div>

</template>

<script>
import StudentAccountPanel from "@/components/system/StudentAccountPanel";
import TeacherAccountPanel from "@/components/system/TeacherAccountPanel";
import AdminAccountPanel from "@/components/system/AdminAccountPanel";
export default {
  name: "AccountManagement",
  components: {AdminAccountPanel, TeacherAccountPanel, StudentAccountPanel},
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