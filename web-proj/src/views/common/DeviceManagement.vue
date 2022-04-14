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
              <el-form-item label="设备id">
                <el-input class="w-75" v-model="conditions.deviceId" placeholder="请输入设备id"></el-input>
              </el-form-item>
            </div>
        <div class="col-4">
          <el-form-item label="设备名称">
            <el-input class="w-75" v-model="conditions.deviceName" placeholder="请输入设备名称"></el-input>
          </el-form-item>
        </div>
        <div class="col-4">
          <el-form-item label="镜像类型">
            <el-input class="w-75" v-model="conditions.imageType" placeholder="请输入镜像类型"></el-input>
          </el-form-item>
        </div>
        <div class="col-4">
          <el-form-item label="状态">
            <el-input class="w-75" v-model="conditions.status" placeholder="请输入状态"></el-input>
          </el-form-item>
        </div>

        </div>
        </el-form>

      </div>
      <div class="col-12 bg-white py-2 mt-3" >
        <div class="row justify-content-between">
          <div class="col-3">      <i class="el-icon-search m-2"></i><span class="smalltitle">数据列表</span></div>
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
                prop="deviceId"
                label="设备id">
            </el-table-column>
            <el-table-column
                prop="deviceName"
                label="设备名称">
            </el-table-column>
            <el-table-column
                prop="image.imageType"
                label="设备类型">
            </el-table-column>
<!--            uuid-->
            <el-table-column
                prop="uuid"
                label="uuid">
            </el-table-column>
            <el-table-column
                label="当前状态">
              <template slot-scope="scope">
                <el-tag type="success" v-if="scope.row.status === 1">运行</el-tag>
                <el-tag type="danger" v-if="scope.row.status === 0">关机</el-tag>
              </template>
            </el-table-column>
            <el-table-column
                label="分配预定">
              <template>
                <el-button type="text" size="small">查看</el-button>
              </template>
            </el-table-column>
            <el-table-column
                fixed="right"
                label="操作" width="350">
              <template slot-scope="scope">

                <el-button type="info" size="small" @click="handleShowEdit(scope.row)">编辑</el-button>
                <el-dropdown @command="handleCommand">
                  <el-button type="info" size="small" class="mx-2">
                    命令
                  </el-button>
                  <el-dropdown-menu slot="dropdown">
                    <el-dropdown-item :command="'a'+scope.row.uuid">开机</el-dropdown-item>
                    <el-dropdown-item :command="'b'+scope.row.uuid">关机</el-dropdown-item>
                    <el-dropdown-item :command="'c'+scope.row.uuid">远程连接</el-dropdown-item>
                  </el-dropdown-menu>
                </el-dropdown>
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
              <el-form-item label="设备名称">
                <el-input class="w-75" v-model="inputData.deviceName" placeholder="请输入设备名称"></el-input>
              </el-form-item>
            </div>
            <div class="col-4">
              <el-form-item label="镜像id">
                <el-input class="w-75" v-model="inputData.imageId" placeholder="请输入镜像id"></el-input>
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
import config from "@/config";

export default {
  name: "DeviceManagement",
  data() {
    return {
      tableData: [],
      pageInfo: {page:1, pageSize: 10, total: 0},
      conditions: {
        deviceId: '',
        deviceName: '',
        imageType: '',
        status: '',
      },
      inputData: {
        deviceName: '',
        imageType: '',
        // status: '',
      },
      dialogFormVisible: false,
      dialogFormTitle: '',
      openstackUrl: config.openstackUrl

    }
  },
  mounted() {
    this.initTableData();
  },
  methods: {
    initTableData() {
      getRequest("/common/device/", {
        page: this.pageInfo.page,
        pageSize: this.pageInfo.pageSize,
        deviceId: this.conditions.deviceId,
        deviceName: this.conditions.deviceName,
        imageType: this.conditions.imageType,
        status: this.conditions.status
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
        delRequest("/common/device/" + id.deviceId).then(() => {
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
        deviceName: '',
        imageType: '',
        // status: '',
      };
      this.dialogFormTitle = '添加';
      this.dialogFormVisible = true;
    },
    handleShowEdit(row){
      window.open(this.openstackUrl+"dashboard/project/instances/"+row.uuid, '_blank');
      // this.dialogFormTitle = '编辑';
      // Object.assign(this.inputData, row);
      // this.dialogFormVisible = true;
    },
    doAddOrUpdate(){
      if(this.dialogFormTitle === '编辑'){
        putRequest("/common/device/", this.inputData).then(() => {
          this.dialogFormVisible = false;
          this.initTableData();
        })
      }else{
        postRequest("/common/device/", this.inputData).then(() => {
          this.dialogFormVisible = false;
          this.initTableData();
        })
      }
    },
    handleCommand(command){
      // if(command === 'edit'){
      //   this.handleShowEdit(row);
      // }else if(command === 'delete'){
      //   this.handleDelete(row);
      // }
      const uuid = command.substr(1)
      if (command[0] ==="a"){
        postRequest("/common/device/"+ uuid+"/start").then(() => {
          this.initTableData();
        })
      }
      if (command[0] ==="b"){
        postRequest("/common/device/"+ uuid+"/stop").then(() => {
          this.initTableData();
        })
      }
      if(command[0] === "c"){
        getRequest("/common/device/"+ uuid+"/vnc/").then(res => {
          window.open(res, '_blank');
        })
      }
      // console.log(command);
    },
    getVnc(row){
      getRequest("/common/device/"+ row.uuid+"/vnc/").then(res => {
        window.open(res.vncUrl, '_blank');
      })
      // window.open(this.openstackUrl+"dashboard/project/instances/"+row.uuid, '_blank');
    }

  }

}
</script>

<style scoped>
.el-dropdown-link {
  cursor: pointer;
  color: #409EFF;
}
.el-icon-arrow-down {
  font-size: 12px;
}
</style>