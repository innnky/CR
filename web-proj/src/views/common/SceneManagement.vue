<template>
  <div class="container h-100">
    <div class="row">
      <div class="col-12 bg-white mt-3 py-2">
        <div class="row justify-content-between">
          <div class="col-3">      <i class="el-icon-search m-2"></i><span class="smalltitle">筛选查询</span></div>
          <div class="col-1 p-2">      <el-button  class="" size="small" @click="initTableData">查询</el-button></div>
        </div>
      <el-form :model="conditions">
      <div class="row px-5">
          <div class="col-4">
            <el-form-item label="场景名称">
              <el-input class="w-75" v-model="conditions.sceneName" placeholder="请输入场景名称"></el-input>
            </el-form-item>
          </div>
          <div class="col-4">
            <el-form-item label="场景id">
              <el-input class="w-75" v-model="conditions.sceneId" placeholder="请输入场景id"></el-input>
            </el-form-item>
          </div>
        </div>
      </el-form>

      </div>
      <div class="col-12 bg-white py-2 mt-3" >
        <div class="row justify-content-between">
          <div class="col-3">      <i class="el-icon-data-analysis m-2"></i><span class="smalltitle">数据列表</span></div>
          <div class="col-1 p-2">      <el-button  class="" size="small">新增</el-button></div>
        </div>
        <div class="row px-3">
          <div class="container">
          <el-table
              :data="tableData"
              stripe
              style="width: 100%"
              height="400">
            <el-table-column
                prop="sceneId"
                label="场景id">
            </el-table-column>
            <el-table-column
                prop="sceneName"
                label="场景名称">
            </el-table-column>
            <el-table-column
                label="设备列表">
              <template>
                <el-button type="text" size="small">查看</el-button>

              </template>
            </el-table-column>
            <el-table-column
                fixed="right"
                label="操作" width="200">
              <template slot-scope="scope">
                <el-button type="info" size="small" @click="handleShowEdit(scope.row)">编辑</el-button>
                <el-button type="danger" size="small" @click="handleDelete(scope.row)">删除</el-button>
              </template>
            </el-table-column>

          </el-table>
            <div class="justify-content-end d-flex mt-2">
              <el-pagination
                  @size-change="handleSizeChange"
                  @current-change="handleCurrentChange"
                  :page-size="pageInfo.pageSize"
                  :current-page.sync="pageInfo.page"
                  layout="sizes, prev, pager, next, jumper, ->, total, slot"
                  :total="pageInfo.total">
              </el-pagination>
            </div>
        </div>
      </div>
      </div>
    </div>
  </div>

</template>

<script>
import {delRequest, getRequest} from "@/api/data";

export default {
  name: "SceneManagement",
  data() {
    return {
      tableData: [],
      pageInfo: {page:1, pageSize: 10, total: 0},
      conditions: {
        sceneId: '',
        sceneName: '',
      },
    }
  },
  mounted() {
    this.initTableData();
  },
  methods: {
    initTableData() {
      getRequest("/common/scene/", {
        page: this.pageInfo.page,
        pageSize: this.pageInfo.pageSize,
        ...this.conditions
      }).then(res => {
        this.tableData = res.records;
        this.pageInfo.total = res.total;
      })
    },
    handleCurrentChange(val) {
      this.pageInfo.page = val;
      this.initTableData();
    },
    handleSizeChange(val) {
      this.pageInfo.pageSize = val;
      this.initTableData();
    },
    handleDelete(id) {
      this.$confirm('此操作将永久删除该项, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        delRequest("/common/scene/" + id.sceneId).then(() => {
          this.initTableData();
        })
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        });
      });
    },
  }

}
</script>

<style scoped>

</style>