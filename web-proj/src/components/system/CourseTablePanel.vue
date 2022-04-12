<template>
  <div class="container bg-white mt-4">
    <div class="d-flex justify-content-sm-between pt-3 mb-3">
      <el-button icon="el-icon-arrow-left" type="text" @click="goBack">返回</el-button>
      <el-button type="primary" @click="handelShowAdd">新增课表</el-button>
    </div>

    <div class="container">
      <el-table
          :data="tableData"
          stripe
          style="width: 100%"
          height="560">
        <el-table-column
            prop="scourseId"
            label="计划id">
        </el-table-column>


        <el-table-column
            prop="week"
            label="周次">
        </el-table-column>
        <el-table-column
            prop="weekday"
            label="周几">
        </el-table-column>
        <el-table-column
            prop="classSequence"
            label="节次">
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
    <el-dialog :title="dialogFormTitle" :visible.sync="dialogFormVisible" width="70%">
      <el-form :model="inputData">
        <div class="container">
          <div class="row">
            <div class="col-4">
              <el-form-item label="周次">
                <el-input v-model="inputData.week" class="w-75" placeholder="请输入周次"></el-input>
              </el-form-item>
            </div>
            <div class="col-4">
              <el-form-item label="周几">
                <el-input v-model="inputData.weekday" class="w-75" placeholder="请输入周几"></el-input>
              </el-form-item>
            </div>
            <div class="col-4">
              <el-form-item label="节次">
                <el-input v-model="inputData.classSequence" class="w-75" placeholder="请输入节次"></el-input>
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
  name: "CourseTablePanel",
  data(){
    return{
      tableData: [],
      pageInfo: {page:1, pageSize: 10, total: 0},
      scourseId: "",
      inputData: {
        week: "",
        weekday: "",
        classSequence: "",
      },
      dialogFormVisible: false,
      dialogFormTitle: ''
    }
  },
  mounted() {
    this.scourseId = this.$route.query.scourseId;

    this.initTableData();
  },
  methods: {
    goBack(){
      this.$router.push({
        path: '/CourseManagement',
        query: {
          isReturning: true
        }
      })
    },
    initTableData() {
      getRequest("/admin/scourse/"+this.scourseId+"/time", {
        page: this.pageInfo.page,
        pageSize: this.pageInfo.pageSize,
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
        delRequest("/admin/scourse/"+this.scourseId+"/time/"+id.timeId).then(() => {
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
        week: "",
        weekday: "",
        classSequence: "",
      };
      // console.log(this.inputData);
      this.dialogFormTitle = '添加';
      this.dialogFormVisible = true;
    },
    handleShowEdit(row){
      this.dialogFormTitle = '编辑';
      Object.assign(this.inputData, row);
      // console.log(this.inputData);

      this.dialogFormVisible = true;
    },
    doAddOrUpdate(){
      if(this.dialogFormTitle === '编辑'){
        putRequest("/admin/scourse/"+this.scourseId+"/time/", this.inputData).then(() => {
          this.dialogFormVisible = false;
          this.initTableData();
        })
      }else{
        postRequest("/admin/scourse/"+this.scourseId+"/time/", this.inputData).then(() => {
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