<template>
  <div>
    <el-form :model="conditions">
      <div class="row px-3">
        <div class="col-3">
          <el-form-item label="班级名">

            <el-input v-model="conditions.className" class="w-75" placeholder="请输入班级名"></el-input>
          </el-form-item>
        </div>
        <div class="col-3">
          <el-form-item label="课程名">

            <el-input v-model="conditions.courseName" class="w-75" placeholder="请输入课程名"></el-input>
          </el-form-item>
        </div>
        <div class="col-3">
          <el-form-item label="教师名">

            <el-input v-model="conditions.teacherName" class="w-75" placeholder="请输入教师名"></el-input>
          </el-form-item>
        </div>
        <div class="col-1 offset-1">      <el-button  class="" size="small"  @click="initTableData">查询</el-button></div>
        <div class="col-1 ">      <el-button  class="" size="small"  @click="handelShowAdd">新增</el-button></div>

      </div>

    </el-form>
    <el-table
        :data="tableData"
        stripe
        style="width: 100%"
        height="420">
      <el-table-column
          prop="scourseId"
          label="计划id">
      </el-table-column>

      <el-table-column
          prop="courseName"
          label="课程名称">
      </el-table-column>
      <el-table-column
          prop="teacherName"
          label="授课教师">
      </el-table-column>
      <el-table-column
          label="授课班级">
        <template slot-scope="scope">
          <el-button size="small" type="text" @click="showClasses(scope.row)">查看</el-button>
        </template>
      </el-table-column>
      <el-table-column
          prop="term"
          label="学期">
      </el-table-column>
      <el-table-column
          prop="date"
          label="课表">
        <template slot-scope="scope">
          <el-button type="text" size="small" @click="handleClassTable(scope.row)">查看</el-button>
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
    <el-dialog title="班级" :visible.sync="dialogClassVisible" width="70%">
      <el-table
          :data="classessssss"
          stripe
          style="width: 100%"
          height="200">
        <el-table-column
            prop="classId"
            label="班级id">
        </el-table-column>
        <el-table-column
            prop="className"
            label="班级名">
        </el-table-column>
      </el-table>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogClassVisible = false">取 消</el-button>
        <el-button type="primary" @click="dialogClassVisible = false">确 定</el-button>
      </div>
    </el-dialog>
    <el-dialog :title="dialogFormTitle" :visible.sync="dialogFormVisible" width="70%">
      <el-form :model="inputData">
        <div class="container">
          <div class="row">
            <div class="col-4">
              <span class="me-2">课程</span>
                <el-select v-model="inputData.courseId" placeholder="请选择课程">
                  <el-option
                      v-for="item in courseList"
                      :key="item.courseId"
                      :label="item.courseName"
                      :value="item.courseId">
                  </el-option>
                </el-select>

            </div>
            <div class="col-4">
<!--              <el-form-item label="教师id">-->
<!--                <el-input v-model="inputData.teacherId" class="w-75" placeholder="请输入教师id"></el-input>-->
<!--              </el-form-item>-->
              <span class="me-2">教师</span>
              <el-select v-model="inputData.teacherId" placeholder="请选择教师">
                <el-option
                    v-for="item in teacherList"
                    :key="item.teacherId"
                    :label="item.teacherName"
                    :value="item.teacherId">
                </el-option>
              </el-select>
            </div>
            <div class="col-4">
<!--              <el-form-item label="班级id">-->
<!--                <el-input class="w-75" v-model="tclassId" placeholder="请输入班级id" @keyup.enter.native="addClass"></el-input>-->
<!--                &lt;!&ndash;                <span v-for="cl in inputData.classInfos" :key="cl">{{cl.classId}}</span>&ndash;&gt;-->
<!--                <el-tag-->
<!--                    :key="cl"-->
<!--                    v-for="cl in inputData.classInfos"-->
<!--                    closable-->
<!--                    :disable-transitions="false"-->
<!--                    @close="delClass(cl)" style="margin-left: 10px">-->
<!--                  {{cl.classId}}-->
<!--                </el-tag>-->
<!--              </el-form-item>-->
              <span class="me-2">班级</span>
              <el-select
                  v-model="inputData.classIds"
                  filterable
                  remote
                  multiple
                  reserve-keyword
                  placeholder="班级名"
                  :remote-method="searchClass"
                  :loading="loading">
                <el-option
                    v-for="item in classOptions"
                    :key="item.classId"
                    :label="item.className"
                    :value="item.classId">
                </el-option>
              </el-select>
            </div>
            <div class="col-4">
              <el-form-item label="学期">
                <el-input placeholder="请输入学期" class="w-75" v-model="inputData.term"></el-input>
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
  name: "CoursePlanPanel",
  data(){
    return{
      tableData: [],
      pageInfo: {page:1, pageSize: 10, total: 0},
      conditions: {
        courseName: "",
        teacherName: "",
        className: "",
      },
      classessssss:  [],
      dialogClassVisible: false,
      inputData: {
        classInfos: [],
        classIds: [],
        courseId: "",
        teacherId: "",
        term: "",
      },
      tclassId: "",
      dialogFormVisible: false,
      dialogFormTitle: '',
      //下拉列表相关数据
      courseList: [],
      teacherList: [],
      classOptions: [],
      courseValue: "",
      teacherValue: "",
      classValue: "",
      loading: false,
    }
  },
  mounted() {
    this.initTableData();

  },
  methods:{
    delClass(cl){
      this.inputData.classInfos.splice(this.inputData.classInfos.indexOf(cl), 1);
    },
    addClass(){
      this.inputData.classInfos.push({classId: this.tclassId});
      this.tclassId = "";
    },
    initTableData() {
      getRequest("/admin/scourse/", {
        page: this.pageInfo.page,
        pageSize: this.pageInfo.pageSize,
        conditions:this.conditions
      }).then(res => {
        this.tableData = res.records;
        this.pageInfo.total = res.total;
      })
      this.searchCourse();
      this.searchTeacher();
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
        delRequest("/admin/scourse/" + id.scourseId).then(() => {
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
        classInfos: [],
        courseId: "",
        teacherId: "",
        term: "",
      };
      this.courseOptions = [];
      this.teacherOptions = [];
      this.classOptions = [];

      this.dialogFormTitle = '添加';
      this.dialogFormVisible = true;
    },
    handleShowEdit(row){
      this.initTableData()
      this.dialogFormTitle = '编辑';
      this.searchCourse(row.courseName);
      this.searchTeacher(row.teacherName);
      this.searchClass(row.className);
      Object.assign(this.inputData, row);
      this.inputData.classIds = this.inputData.classInfos.map(cl => cl.classId);
      this.dialogFormVisible = true;
    },
    doAddOrUpdate(){
      if(this.dialogFormTitle === '编辑'){
        putRequest("/admin/scourse/", this.inputData).then(() => {
          this.dialogFormVisible = false;
          this.initTableData();
        })
      }else{
        postRequest("/admin/scourse/", this.inputData).then(() => {
          this.dialogFormVisible = false;
          this.initTableData();
        })
      }
    },

    handleClassTable(row){
      this.$router.push({
        path: "/CourseTablePanel",
        query: {
          scourseId: row.scourseId
        }
      })
    },
    showClasses(id){
      getRequest("/admin/scourse/" + id.scourseId+"/class").then(res => {
        this.dialogClassVisible = true;
        this.classessssss = res;


      })
    },
    searchCourse(){
      getRequest("/admin/course/list").then(res => {
        this.courseList = res
      })

    },
    searchTeacher(){
      getRequest("/admin/teacher/list").then(res => {
        this.teacherList = res
      })
    },
    searchClass(query){
      if (query !== '') {
        this.loading = true;
        getRequest("/admin/class/",{
          page: 1,
          pageSize: -1,
          className: query
        }).then(res => {
          this.classOptions = res.records;
          this.loading = false;
        })
      } else {
        this.classOptions = [];
      }
    },
  },


}
</script>

<style scoped>

</style>