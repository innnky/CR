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
                label="文件">
              <template slot-scope="scope">
                <i class="el-icon-notebook-2"></i>
                <span  style="margin-left: 10px;color: #7fb6ff">{{ scope.row.materialName }}</span>
              </template>
            </el-table-column>
            <el-table-column
                prop="updateTime"
                label="上传时间">
            </el-table-column>
            <el-table-column
                prop="scourseId"
                label="所属课程">
            </el-table-column>
            <el-table-column
                prop="date"
                label="操作">
              <template slot-scope="scope">
                <el-link :href="scope.row.materialPath"> 下载</el-link>
              </template>
            </el-table-column>

          </el-table>
        </div>
      </div>
    </div>
  </div>

</template>

<script>
import {getRequest} from "@/api/data";

export default {
  name: "StudentMaterial",
  data() {
    return {
      activeName:"first",
      tableData: []
    }
  },
  methods: {
    initData() {
      getRequest("/student/material/").then(res => {
        this.tableData = res;
      })
    }
  },
  mounted() {
    this.initData();
  }
}
</script>

<style scoped>

</style>