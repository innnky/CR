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
                prop="deviceId"
                label="设备名称">

            </el-table-column>
            <el-table-column
                prop="date"
                label="预约时间">
            </el-table-column>
            <el-table-column
                prop="sequence"
                label="预约节次">
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
                <el-button size="small" type="primary" disabled :href="scope">远程连接</el-button>
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
import {getRequest} from "@/api/data";

export default {
  name: "StudentDevices",
  data() {
    return {
      activeName:"first",
      tableData: []
    }
  },
  methods: {
    initData() {
      getRequest("/student/device/all").then(res => {
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