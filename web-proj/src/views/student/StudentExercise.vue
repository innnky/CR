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
                prop="date"
                label="作业任务">
              <template slot-scope="scope">
                <i class="el-icon-time"></i>
                <span @click="showExerciseDetail()" style="margin-left: 10px;color: #7fb6ff;cursor: pointer">{{ scope.row.name }}</span>
              </template>
            </el-table-column>
            <el-table-column
                prop="name"
                label="开始时间">
            </el-table-column>
            <el-table-column
                prop="address"
                label="截止时间">
            </el-table-column>
            <el-table-column
                prop="date"
                label="所属课程">
            </el-table-column>
            <el-table-column
                prop="date"
                label="可用场景">
              <template slot-scope="scope">
                <el-tag size="medium" :href="scope">查看</el-tag>
              </template>
            </el-table-column>
            <el-table-column
                prop="date"
                label="状态">
            </el-table-column>
            <el-table-column
                prop="date"
                label="成绩">
            </el-table-column>
          </el-table>

        </div>
      </div>
    </div>
  </div>

</template>

<script>
export default {
  name: "StudentExercise",
  data() {
    return {
      activeName:"first",
      tableData: [
        {
          date: '2016-05-02',
          name: '王小虎',
          address: '上海市普陀区金'
        },
        {
          date: '2016-05-02',
          name: '王小虎',
          address: '上海市普8 弄'
        }, {
        date: '2016-05-04',
        name: '王小虎',
        address: '上海市普陀弄'
      }, {
        date: '2016-05-01',
        name: '王小虎',
        address: '上海市普陀19 弄'
      }, {
        date: '2016-05-03',
        name: '王小虎',
        address: '上海市普'
      }]
    }
  },
  methods:{
    showExerciseDetail(){
      console.log(111);
      this.$router.push("/StudentExerciseDetail")
    }
  }
}
</script>

<style scoped>

</style>