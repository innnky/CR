<template>
<div>
  <el-form :model="conditions">
    <div class="row px-3">
      <div class="col-3">
        <el-form-item label="账号">
          <el-input placeholder="请输入账号" class="w-75" v-model="conditions.account"></el-input>
        </el-form-item>
      </div>
      <div class="col-1 offset-7">      <el-button  class="" size="small"  @click="initTableData">查询</el-button></div>
      <div class="col-1 ">      <el-button  class="" size="small" @click="handelShowAdd">新增</el-button></div>

    </div>

  </el-form>
  <el-table
      :data="tableData"
      stripe
      style="width: 100%"
      height="360">
    <el-table-column
        prop="account"
        label="账号">
    </el-table-column>
    <el-table-column
        prop="password"
        width="350"
        label="密码">
    </el-table-column>
    <el-table-column
        label="头像">
      <template slot-scope="scope">
        <img :src="scope.row.avatarUrl" width="50" height="50">
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
  <el-dialog :title="dialogFormTitle" :visible.sync="dialogFormVisible" width="70%">
    <el-form :model="inputData">
      <div class="container">
        <div class="row">
          <div class="col-4">
            <el-form-item label="账号">
              <el-input placeholder="请输入账号" class="w-75" v-model="inputData.account"></el-input>
            </el-form-item>
          </div>
          <div class="col-4">
            <el-form-item label="密码">
              <el-input placeholder="请输入密码" class="w-75" v-model="inputData.password"></el-input>
            </el-form-item>
          </div>
          <div class="col-4">
            <el-form-item label="头像地址">
              <el-input placeholder="请输入头像地址" class="w-75" v-model="inputData.avatarUrl"></el-input>
            </el-form-item>
          </div>
        </div>
      </div>
    </el-form>
    <div slot="footer" class="dialog-footer">
      <el-button @click="dialogFormVisible = false">取 消</el-button>
      <el-button type="primary" @click="doAddOrUpdate">确 定</el-button>
    </div>
  </el-dialog>
</div>
</template>

<script>
import {delRequest, getRequest, postRequest, putRequest} from "@/api/data";

export default {
  name: "AdminAccountPanel",
  data() {
    return {
      tableData: [],
      pageInfo: {page:1, pageSize: 10, total: 0},
      conditions: {
        account: "",
      },
      inputData: {
        account: "",
        password: "",
        avatarUrl: "",
        roleId: 3
      },
      dialogFormVisible: false,
      dialogFormTitle: '',

    }
  },
  mounted() {
    this.initTableData();
  },
  methods: {
    initTableData() {
      getRequest("/admin/account/admin/", {
        page: this.pageInfo.page,
        pageSize: this.pageInfo.pageSize,
        account: this.conditions.account,
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
        delRequest("/admin/account/" + id.uid).then(() => {
          this.initTableData();
        })
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        });
      });
    },
    handelShowAdd(){
      this.inputData = {
        account: "",
        password: "",
        avatarUrl: "",
        roleId: 3
      };
      this.dialogFormTitle = '添加';
      this.dialogFormVisible = true;
    },
    handleShowEdit(row){
      this.dialogFormTitle = '编辑';
      Object.assign(this.inputData, row);
      this.dialogFormVisible = true;
    },
    doAddOrUpdate(){
      if(this.dialogFormTitle === '编辑'){
        putRequest("/admin/account/", this.inputData).then(() => {
          this.dialogFormVisible = false;
          this.initTableData();
        })
      }else{
        postRequest("/admin/account/", this.inputData).then(() => {
          this.dialogFormVisible = false;
          this.initTableData();
        })
      }
    },

  }

}
</script>

<style scoped>

</style>