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
          <div class="col-1 p-2"><el-link  href="http://192.168.50.53/dashboard/project/network_topology/">编辑拓扑</el-link>    <el-button  class="mt-2" size="small" @click="handelShowAdd">新增</el-button></div>
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
    <el-dialog :title="dialogFormTitle" :visible.sync="dialogFormVisible" width="70%">
      <el-form :model="inputData">
        <div class="container">
          <div class="row">
            <div class="col-4">
              <el-form-item label="场景名称">
                <el-input class="w-75" v-model="inputData.sceneName" placeholder="请输入场景名称"></el-input>
              </el-form-item>
            </div>
            <el-transfer @change="handleDeviceChange" :titles="['可用设备', '已选择']" v-model="inputData.deviceIds" :data="availableData"></el-transfer>
            <el-table :data="rolesData" stripe>
              <el-table-column
                  label="设备">
                  <template slot-scope="scope">
                    <el-tag type="primary">{{getDeviceName(scope.row.deviceId)}}</el-tag>
                  </template>
              </el-table-column>
              <el-table-column
                  prop="roles"
                  label="角色">
              </el-table-column>
            </el-table>
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
  name: "SceneManagement",
  data() {
    return {
      tableData: [],
      pageInfo: {page:1, pageSize: 10, total: 0},
      conditions: {
        sceneId: '',
        sceneName: '',
      },
      inputData: {
        sceneName: '',
        deviceIds: [],
        roles: [],
      },
      dialogFormVisible: false,
      dialogFormTitle: '',
      availableData: [],
      rolesData:[],
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
        conditions:this.conditions
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
    handelShowAdd(){
      this.getAvailableData()
      this.inputData = {
        sceneName: '',
        deviceIds: [],
      };
      this.rolesData = [];
      this.dialogFormTitle = '添加';
      this.dialogFormVisible = true;
    },
    handleShowEdit(row){
      this.getAvailableData()
      this.getDevicesInSceneData(row)
      this.dialogFormTitle = '编辑';
      Object.assign(this.inputData, row);
      this.dialogFormVisible = true;
    },
    doAddOrUpdate(){
      this.inputData.deviceIds = this.rolesData.map(item => item.deviceId)
      this.inputData.roles = this.rolesData.map(item => item.roles)
      if(this.dialogFormTitle === '编辑'){
        putRequest("/common/scene/", this.inputData).then(() => {
          this.dialogFormVisible = false;
          this.initTableData();
        })
      }else{
        console.log(this.fileList);
        postRequest("/common/scene/", this.inputData).then(() => {
          this.dialogFormVisible = false;
          this.initTableData();
        })
      }
    },
    getAvailableData(){
      getRequest("/common/device/unused").then(res => {
        // this.availableData = res;
        // [
        //   {
        //     "deviceId": 46,
        //     "status": 0,
        //     "deviceName": "哈哈哈",
        //     "imageId": "73",
        //     "uuid": "67456214-7603-483b-a7fd-765153326989"
        //   }
        // ]

        // [
        //   {
        //     key: '46',
        //     label: '哈哈哈',
        //   }
        // ]
        this.availableData = res.map(item => {
          return {
            key: item.deviceId,
            label: item.deviceName,
          }
        })
      })
    },
    getDevicesInSceneData(row){
      getRequest("/common/scene/" + row.sceneId + "/devices").then(res => {
        this.inputData.deviceIds = res.map(item => {
          return item.deviceId;
        })
        this.rolesData = res.map(item => {
          return {
            deviceId: item.deviceId,
            roles: item.role
          }
        })
        var temp = res.map(item => {
          return {
            key: item.deviceId,
            label: item.deviceName,
          }
        })
        this.availableData.push(...temp);
      })
    },
    handleDeviceChange(val, dirction, movedKeys){
      console.log(movedKeys);
      if (dirction === 'right') {
        this.$prompt('填写角色', '角色', {
          confirmButtonText: '确定',
        }).then(({value}) => {
          movedKeys.forEach(item => {
            this.rolesData.push({
              deviceId: item,
              roles: value
            })
          })
          console.log(this.rolesData);
        });
      }else if (dirction === 'left') {
        movedKeys.forEach(item => {
            //从this.rolesData中删除
            this.rolesData = this.rolesData.filter(item2 => {
              return item2.deviceId !== item;
            })
            console.log(this.rolesData);
        })
      }

    },
    getDeviceName(deviceId){
      var device = this.availableData.find(item => {
        return item.key === deviceId;
      })
      return device.label;
    }
  }

}
</script>

<style scoped>

</style>