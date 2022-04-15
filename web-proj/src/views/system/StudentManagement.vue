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
              <el-form-item label="学生姓名">
                <el-input class="w-75" v-model="conditions.studentName" placeholder="请输入学生姓名"></el-input>
              </el-form-item>
            </div>
            <div class="col-4">
              <el-form-item label="学生id">
                <el-input class="w-75" v-model="conditions.studentId" placeholder="请输入学生id"></el-input>
              </el-form-item>
            </div>
            <div class="col-4">
              <el-form-item label="院系">
                <el-input class="w-75" v-model="conditions.college" placeholder="请输入院系"></el-input>
              </el-form-item>
            </div>
            <div class="col-4">
              <el-form-item label="年级">
                <el-input class="w-75" v-model="conditions.grade" placeholder="请输入年级"></el-input>
              </el-form-item>
            </div>
            <div class="col-4">
              <el-form-item label="专业">
                <el-input class="w-75" v-model="conditions.major" placeholder="请输入专业"></el-input>
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
          <div class="col-12">
            <div class="container">
              <el-table
                  :data="tableData"
                  stripe
                  style="width: 100%"
                  height="400">
                <el-table-column
                    prop="studentNumber" width="150"
                    label="学号">
                </el-table-column>
                <el-table-column
                    prop="studentName" width="150"
                    label="姓名">
                </el-table-column>

                <el-table-column
                    prop="classInfo.className" width="230"
                    label="班级">
                  <template slot-scope="scope">
                    <el-tag type="success">{{scope.row.classInfo.className}}</el-tag>
                    <!--                <el-button type="text" size="small" class="ms-1" @click="handelShowEdit(scope.row)">编辑</el-button>-->
                    <el-tag type="" class="ms-1" style="cursor: pointer" @click="handleHistory(scope.row)">历史</el-tag>
                    <el-popover
                        placement="right"
                        width="320"
                        trigger="click">
                      <el-select
                          v-model="classValue"
                          filterable
                          remote
                          reserve-keyword
                          placeholder="请输入关键词"
                          :remote-method="searchClasses"
                          :loading="loading">
                        <el-option
                            v-for="item in classes"
                            :key="item.classId"
                            :label="item.className"
                            :value="item.classId">
                        </el-option>
                      </el-select>
                      <el-button size="tiny" class="ms-1" @click="handleChangeClass(scope.row)">确认</el-button>
                      <el-button slot="reference" class="ms-1" size="small" type="warning" plain>变更</el-button>
                    </el-popover>

                  </template>
                </el-table-column>
                <el-table-column
                    prop="classInfo.college" width="150"
                    label="院系">
                </el-table-column>
                <el-table-column
                    prop="classInfo.major" width="150"
                    label="专业">
                </el-table-column>
                <el-table-column
                    prop="date" width="150"
                    label="班级职务">
                  <template>
                    <el-button type="text" size="small">查看</el-button>
                  </template>
                </el-table-column>
                <el-table-column
                    prop="admissiondate" width="150"
                    label="入学时间">
                </el-table-column>
                <el-table-column
                    prop="gender" width="150"
                    label="性别">
                </el-table-column>
                <el-table-column
                    prop="contact" width="150"
                    label="联系方式">
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
            </div>
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
<!--              <el-form-item label="入学日期">-->
<!--                <el-input class="w-75" v-model="inputData.admissiondate" placeholder="请输入入学日期"></el-input>-->
<!--              </el-form-item>-->
              <span class="me-2">入学日期</span>
              <el-date-picker
                  v-model="inputData.admissiondate"
                  type="date"
                  placeholder="选择日期"
                  format="yyyy 年 MM 月 dd 日"
                  value-format="yyyy-MM-dd HH:mm:ss"
                  class="w-75">
              </el-date-picker>
            </div>
            <div class="col-4">
              <el-form-item label="联系方式">
                <el-input class="w-75" v-model="inputData.contact" placeholder="请输入联系方式"></el-input>
              </el-form-item>
            </div>
            <div class="col-4">
              <el-form-item label="性别">
                <el-input class="w-75" v-model="inputData.gender" placeholder="请输入性别"></el-input>
              </el-form-item>
            </div>
            <div class="col-4">
              <el-form-item label="班级id">
                <el-input class="w-75" v-model="inputData.classId" placeholder="请输入班级id"></el-input>
              </el-form-item>
            </div>
            <div class="col-4">
              <el-form-item label="姓名">
                <el-input class="w-75" v-model="inputData.studentName" placeholder="请输入学生姓名"></el-input>
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


    <el-dialog title="历史" :visible.sync="dialogFormVisibleHistory" width="70%" height="80%">
      <el-table
          :data="historyTableData"
          stripe
          style="width: 100%"
          height="200">
        <el-table-column
            prop="classId"
            label="班级id">
        </el-table-column>
        <el-table-column
            prop="className"
            label="班级名称">
        </el-table-column>
        <el-table-column
            prop="startTime"
            label="进入班级时间">
        </el-table-column>
        <el-table-column
            prop="endTime"
            label="退出班级时间">
        </el-table-column>
      </el-table>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisibleHistory = false">取 消</el-button>
        <el-button type="primary" @click="dialogFormVisibleHistory = false">确 定</el-button>
      </div>
    </el-dialog>

  </div>

</template>

<script>
import {delRequest, getRequest, postRequest, putRequest} from "@/api/data";

export default {
  name: "StudentManagement",
  data() {
    return {
      tableData: [],
      pageInfo: {page:1, pageSize: 10, total: 0},
      conditions: {
        studentId: '',
        studentName: '',
        className: '',
        college: '',
        major: '',
        grade: '',
        gender: ''
      },
      inputData: {
        admissiondate: '',
        contact: '',
        gender: '',
        classId:'',
        studentName:''
      },
      dialogFormVisible: false,
      dialogFormTitle: '',
      dialogFormVisibleHistory: false,
      historyTableData: [],

      visible: false,
      classes: [],
      classValue: '',
      loading: false,
      changeClassVisible: false,

    }
  },
  mounted() {
    this.initTableData();
  },
  methods: {
    initTableData() {
      getRequest("/admin/student/", {
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
        delRequest("/admin/student/" + id.studentId).then(() => {
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
        admissiondate: '',
        contact: '',
        gender: '',
        classId:'',
        studentName:''
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
        putRequest("/admin/student/", this.inputData).then(() => {
          this.dialogFormVisible = false;
          this.initTableData();
        })
      }else{
        postRequest("/admin/student/", this.inputData).then(() => {
          this.dialogFormVisible = false;
          this.initTableData();
        })
      }
    },
    handleHistory(row){
      this.dialogFormVisibleHistory = true;
      getRequest("/admin/student/" + row.studentId + "/history").then(res => {
        this.historyTableData = res;
      })
    },
    handleChangeClass(row){
      putRequest("/admin/student/"+row.studentId+"/class", {classId: this.classValue}).then(() => {
        this.initTableData();
      })
    },
    searchClasses(query){
      if (query !== '') {
        this.loading = true;
        getRequest("/admin/class/", {
          page: 1,
          pageSize: -1,
          className: query
        }).then(res => {
          this.classes = res.records;
          this.loading = false;
        })
      } else {
        this.classes = [];
      }

    }
  }

}
</script>

<style scoped>

</style>