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
              <el-form-item label="教师姓名">
                <el-input class="w-75" v-model="conditions.teacherName" placeholder="请输入教师姓名"></el-input>
              </el-form-item>
            </div>
            <div class="col-4">
              <el-form-item label="教师id">
                <el-input class="w-75" v-model="conditions.teacherId" placeholder="请输入教师id"></el-input>
              </el-form-item>
            </div>
            <div class="col-4">
              <el-form-item label="部门id">
                <el-input class="w-75" v-model="conditions.departmentId" placeholder="请输入院系id"></el-input>
              </el-form-item>
            </div>
            <div class="col-4">
              <el-form-item label="性别">
                <el-input class="w-75" v-model="conditions.gender" placeholder="请输入性别"></el-input>
              </el-form-item>
            </div>

          </div>
        </el-form>

      </div>
      <div class="col-12 bg-white py-2 mt-3" >
        <div class="row justify-content-between">
          <div class="col-3">      <i class="el-icon-data-analysis m-2"></i><span class="smalltitle">数据列表</span></div>
          <div class="col-1 p-2">      <el-button  class="" size="small" @click="handelShowAdd">新增</el-button></div>
        </div>
        <div class="row px-3">
          <div class="container">
          <el-table
              :data="tableData"
              stripe
              style="width: 100%"
              height="400">
            <el-table-column
                prop="teacherId"
                label="教师id">
            </el-table-column>
            <el-table-column
                prop="teacherName"
                label="姓名">
            </el-table-column>


            <el-table-column
                prop="departmentName"
                label="所在单位">
            </el-table-column>
            <el-table-column
                prop="contact"
                label="联系方式">
            </el-table-column>
            <el-table-column
                prop="gender"
                label="性别">
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
    <el-dialog :title="dialogFormTitle" :visible.sync="dialogFormVisible" width="70%">
      <el-form :model="inputData">
        <div class="container">
          <div class="row">
            <div class="col-4">
              <el-form-item label="姓名">
                <el-input class="w-75" v-model="inputData.teacherName" placeholder="请输入姓名"></el-input>
              </el-form-item>
            </div>
            <div class="col-4">
              <el-form-item label="单位id">
                <el-input class="w-75" v-model="inputData.departmentId" placeholder="请输入单位id"></el-input>
              </el-form-item>
            </div>
            <div class="col-4">
              <el-form-item label="联系方式">
                <el-input class="w-75" v-model="inputData.contact" placeholder="请输入联系方式"></el-input>
              </el-form-item>
            </div>
            <div class="col-4">
              <el-form-item label="性别">
                <el-input class="w-75" v-model="inputData.gender"  placeholder="请输入性别"></el-input>
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
  name: "TeacherManagement",
  data() {
    return {
      tableData: [],
      pageInfo: {page:1, pageSize: 10, total: 0},
      conditions: {
        teacherId: '',
        teacherName: '',
        departmentId: '',
        gender: ''
      },
      inputData: {
        contact: '',
        departmentId: '',
        gender: '',
        teacherName: ''
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
      getRequest("/admin/teacher/", {
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
        delRequest("/admin/teacher/" + id.teacherId).then(() => {
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
        contact: '',
        departmentId: '',
        gender: '',
        teacherName: ''
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
        putRequest("/admin/teacher/", this.inputData).then(() => {
          this.dialogFormVisible = false;
          this.initTableData();
        })
      }else{
        postRequest("/admin/teacher/", this.inputData).then(() => {
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