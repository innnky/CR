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
              <el-form-item label="院系">
                <el-input placeholder="请输入" class="w-75" v-model="conditions.college"></el-input>
              </el-form-item>
            </div>
            <div class="col-4">
              <el-form-item label="专业">
                <el-input placeholder="请输入"  class="w-75" v-model="conditions.major"></el-input>
              </el-form-item>
            </div>
            <div class="col-4">
              <el-form-item label="年级">
                <el-input placeholder="请输入"  class="w-75" v-model="conditions.grade"></el-input>
              </el-form-item>
            </div>
            <div class="col-4">
              <el-form-item label="班级">
                <el-input placeholder="请输入" class="w-75" v-model="conditions.className"></el-input>
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
          <el-table
              :data="tableData"
              stripe
              style="width: 100%"
              height="400">
            <el-table-column
                prop="classId"
                label="班级id">
            </el-table-column>
            <el-table-column
                prop="className"
                width="100"
                label="班级名称">
            </el-table-column>
            <el-table-column
                prop="studentCount"
                label="人数">
            </el-table-column>
            <el-table-column
                prop="college"
                label="院系"
                width="180">
            </el-table-column>
            <el-table-column
                prop="major"
                label="专业"
            width="160">
            </el-table-column>
            <el-table-column
                prop="grade"
                label="年级" width="60">
            </el-table-column>
            <el-table-column
                prop="teacherName"
                label="班主任" width="80">
            </el-table-column>
            <el-table-column
                label="学生" width="80">
              <template slot-scope="scope">
                <el-tag @click="getStudentsByClassId(scope.row)">查看</el-tag>

              </template>
            </el-table-column>
            <el-table-column
                label="班级干部" width="100">
              <template>
                <el-button type="text" size="small">查看</el-button>
              </template>
            </el-table-column>
            <el-table-column
                prop="date"
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
    <el-dialog :title="dialogFormTitle" :visible.sync="dialogFormVisible" width="70%">
      <el-form :model="inputData">
        <div class="container">
          <div class="row">
            <div class="col-4">
              <el-form-item label="班级名称">
                <el-input v-model="inputData.className" class="w-75"></el-input>
              </el-form-item>
            </div>
            <div class="col-4">
              <el-form-item label="年级">
                <el-input v-model="inputData.grade" class="w-75"></el-input>
              </el-form-item>
            </div>
            <div class="col-4">
              <el-form-item label="专业">
                <el-input v-model="inputData.major" class="w-75"></el-input>
              </el-form-item>
            </div>
            <div class="col-4">
              <el-form-item label="院系">
                <el-input v-model="inputData.college" class="w-75"></el-input>
              </el-form-item>
            </div>
            <div class="col-4">
              <el-form-item label="班主任">
                <el-input v-model="inputData.headTeacherId" class="w-75"></el-input>
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
<!--    私有代码区域-->
    <el-dialog title="学生" :visible.sync="dialogFormVisibleStudent" width="70%">
      <el-table
          :data="studentsTableData"
          stripe
          style="width: 100%"
          height="200">
        <el-table-column
            prop="studentId"
            label="学生id">
        </el-table-column>
        <el-table-column
            prop="studentName"
            label="学生姓名">
        </el-table-column>
      </el-table>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisibleStudent = false">取 消</el-button>
        <el-button type="primary" @click="dialogFormVisibleStudent = false">确 定</el-button>
      </div>
    </el-dialog>
  </div>

</template>

<script>
import {delRequest, getRequest, postRequest, putRequest} from "@/api/data";
//String college, String major, String grade, String className
export default {
  name: "ClassManagement",
  data() {
    return {
      tableData: [],
      pageInfo: {page:1, pageSize: 10, total: 0},
      conditions: {
        college: '',
        major: '',
        grade: '',
        className: ''
      },
      inputData: {
        className: '',
        grade: '',
        major: '',
        college: '',
        headTeacherId: ''
      },
      dialogFormVisible: false,
      dialogFormTitle: '',
      dialogFormVisibleStudent: false,
      studentsTableData: [],
    }
  },
  mounted() {
    this.initTableData();
  },
  methods:{
    initTableData(){
      getRequest("/admin/class/", {
        page:this.pageInfo.page,
        pageSize:this.pageInfo.pageSize,
        college:this.conditions.college,
        major:this.conditions.major,
        grade:this.conditions.grade,
        className:this.conditions.className
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
    handleDelete(id){
      this.$confirm('此操作将永久删除该项, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        delRequest("/admin/class/"+id.classId).then(() => {
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
        className: '',
        grade: '',
        major: '',
        college: '',
        headTeacherId: ''
      };
      this.dialogFormTitle = '添加班级';
      this.dialogFormVisible = true;
    },
    handleShowEdit(row){
      this.dialogFormTitle = '编辑班级';
      Object.assign(this.inputData, row);
      this.dialogFormVisible = true;
    },
    doAddOrUpdate(){
      if(this.inputData.classId){
        putRequest("/admin/class/"+this.inputData.classId, this.inputData).then(() => {
          this.dialogFormVisible = false;
          this.initTableData();
        })
      }else{
        postRequest("/admin/class/", this.inputData).then(() => {
          this.dialogFormVisible = false;
          this.initTableData();
        })
      }
    },
  //  私有代码区
    getStudentsByClassId(row){
      getRequest("/admin/class/"+row.classId+"/student").then(res => {
        this.studentsTableData = res;
        this.dialogFormVisibleStudent = true;
      })
    }


  }
}
</script>

<style scoped>
.el-tag{
  cursor: pointer;
}
</style>