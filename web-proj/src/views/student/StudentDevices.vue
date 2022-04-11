<template>
  <div class="container h-100">
    <div class="row  align-items-center" style="height: 100%;">
      <div class="col-12 bg-white py-2 mb-0" style="height: 90%">
        <div class="row ">
          <div class="col-3">      <i class="el-icon-search m-2"></i><span class="smalltitle">我的设备</span></div>
        </div>
        <div class="row py-2 px-4 ">
          <el-table
              :data="tableData"
              stripe
              style="width: 100%"
              max-height="360">
            <el-table-column
                prop="date"
                label="设备id">
              <template slot-scope="scope">
                <span @click="showExerciseDetail()" style="margin-left: 10px">{{ scope.row.name }}</span>
              </template>
            </el-table-column>
            <el-table-column
                prop="name"
                label="设备类型">
            </el-table-column>
            <el-table-column
                prop="address"
                label="预约时间">
            </el-table-column>
            <el-table-column
                prop="date"
                label="所属课程">
            </el-table-column>
            <el-table-column
                prop="date"
                label="状态">
              <template slot-scope="scope">
                <el-tag size="medium" :href="scope">正在使用</el-tag>
              </template>
            </el-table-column>
            <el-table-column
                prop="date"
                label="操作">
              <template slot-scope="scope">
                <el-tag type="warning" size="medium" :href="scope">撤销</el-tag>
              </template>
            </el-table-column>

          </el-table>

        </div>
      </div>
    </div>
  </div>

</template>

<script>
export default {
  name: "StudentDevices",
  data() {
    return {
      activeName:"first",
      tableData: [
        {
          date: '2016-05-02',
          name: '王小虎',
          address: '上海市普陀'
        },
        {
          date: '2016-05-02',
          name: '王小虎',
          address: '上海市普陀'
        },
        {
          date: '2016-05-02',
          name: '王小虎',
          address: '上海市普陀'
        },
        {
          date: '2016-05-02',
          name: '王小虎',
          address: '上海市普陀'
        },
        {
          date: '2016-05-02',
          name: '王小虎',
          address: '上海市普陀'
        },
]
    }
  }
}
</script>

<style scoped>

</style>