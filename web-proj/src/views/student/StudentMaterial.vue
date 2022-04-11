<template>
  <div class="container h-100">
    <div class="row  align-items-center" style="height: 100%;">
      <div class="col-12 bg-white py-2 mb-0" style="height: 90%">
        <div class="row ">
          <div class="col-3">      <i class="el-icon-search m-2"></i><span class="smalltitle">资料</span></div>
        </div>
        <div class="row py-2 px-4 ">
          <el-table
              :data="tableData"
              stripe
              style="width: 100%"
              max-height="360">
            <el-table-column
                prop="date"
                label="文件">
              <template slot-scope="scope">
                <i class="el-icon-notebook-2"></i>
                <span @click="showExerciseDetail()" style="margin-left: 10px;color: #7fb6ff;cursor: pointer">{{ scope.row.name }}</span>
              </template>
            </el-table-column>
            <el-table-column
                prop="name"
                label="类别">
            </el-table-column>
            <el-table-column
                prop="address"
                label="所属课程">
            </el-table-column>
            <el-table-column
                prop="date"
                label="上传日期">
            </el-table-column>

          </el-table>
        </div>
      </div>
    </div>
  </div>

</template>

<script>
export default {
  name: "StudentMaterial",
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