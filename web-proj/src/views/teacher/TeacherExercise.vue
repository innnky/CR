<template>
  <div class="container h-100">
    <div class="row  align-items-center" style="height: 100%;">
      <div class="col-12 bg-white py-2 mb-0" style="height: 90%">

        <div class="row px-4 py-3">
          <el-tabs v-model="activeName" @tab-click="handleClick">
            <el-tab-pane label="全部作业" name="first">

              <el-table
                  :data="tableData"
                  stripe
                  style="width: 100%"
                  max-height="360">
                <el-table-column
                    prop="date"
                    label="作业任务">
                </el-table-column>
                <el-table-column
                    prop="date"
                    label="从属课程">
                </el-table-column>
                <el-table-column
                    prop="date"
                    label="开始时间">
                </el-table-column>
                <el-table-column
                    prop="date"
                    label="截止时间">
                </el-table-column>
                <el-table-column
                    prop="date"
                    label="班级">
                </el-table-column>
                <el-table-column
                    prop="date"
                    label="已提交">
                </el-table-column>
                <el-table-column
                    prop="date"
                    label="已评阅">
                </el-table-column>
                <el-table-column
                    prop="date"
                    label="状态">
                </el-table-column>
                <el-table-column
                    prop="date"
                    label="场景">
                </el-table-column>
                <el-table-column
                    prop="date"
                    label="操作">
                </el-table-column>
              </el-table>
            </el-tab-pane>
            <el-tab-pane label="作业评分" name="second">
              <el-table
                  :data="tableData"
                  stripe
                  style="width: 100%"
                  max-height="360">
                <el-table-column
                    prop="date"
                    label="作业任务">
                </el-table-column>
                <el-table-column
                    prop="date"
                    label="从属课程">
                </el-table-column>
                <el-table-column
                    prop="date"
                    label="学生">
                </el-table-column>
                <el-table-column
                    prop="date"
                    label="班级">
                </el-table-column>
                <el-table-column
                    prop="date"
                    label="提交时间">
                </el-table-column>
                <el-table-column
                    prop="date"
                    label="详情">
                </el-table-column>
                <el-table-column
                    prop="date"
                    label="状态">
                </el-table-column>
                <el-table-column
                    prop="date"
                    label="操作">
                </el-table-column>
              </el-table>
            </el-tab-pane>
          </el-tabs>

        </div>
      </div>
    </div>
  </div>

</template>

<script>
export default {
  name: "TeacherExercise",
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