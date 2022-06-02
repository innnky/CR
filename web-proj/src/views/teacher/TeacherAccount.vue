<template>
  <div class="container h-100">
    <div class="row  align-items-center" style="height: 100%;">
      <div class="col-12 bg-white py-2 mb-0" style="height: 90%">
        <div class="row ">
          <div class="col-3">      <i class="el-icon-search m-2"></i><span class="smalltitle">账号</span></div>
        </div>
        <div class="row px-4  py-2">
          <el-tabs v-model="activeName" @tab-click="handleClick">
            <el-tab-pane label="修改密码" name="first">
              <!--              <div class="w-100 my-2"><span class="m-3">旧密码</span><el-input v-model="formData.oldPassword" class="w-25"></el-input></div>-->
              <!--              <div class="w-100 my-2"><span class="m-3">新密码</span><el-input class="w-25"></el-input></div>-->
              <!--              <div class="w-100 my-2"><span class="m-3">新密码确认</span><el-input class="w-25"></el-input></div>-->
              <!--              <el-button class="m-2 ms-1">提交修改</el-button>-->
              <el-form
                  :model="formData"
                  :rules="rules"
                  ref="form"
                  label-width="100px"
                  class="demo-ruleForm">
                <el-form-item label="旧密码" prop="oldPassword">
                  <el-input v-model="formData.oldPassword" type="password"></el-input>
                </el-form-item>
                <el-form-item label="新密码" prop="newPassword">
                  <el-input v-model="formData.newPassword" type="password"></el-input>
                </el-form-item>
                <el-form-item label="新密码确认" prop="newPasswordConfirm">
                  <el-input v-model="formData.newPasswordConfirm" type="password"></el-input>
                </el-form-item>
                <el-form-item>
                  <el-button type="primary" @click="handleSubmit('form')">提交</el-button>
                </el-form-item>
              </el-form>
            </el-tab-pane>
            <el-tab-pane label="修改头像" name="second">
              <div class="myelupload">
                <el-upload
                    class="avatar-uploader"
                    action="https://jsonplaceholder.typicode.com/posts/"
                    :show-file-list="false"
                    :on-success="handleAvatarSuccess"
                    :before-upload="beforeAvatarUpload">
                  <img v-if="imageUrl" :src="imageUrl" class="avatar">
                  <i v-else class="el-icon-plus avatar-uploader-icon"></i>
                </el-upload>
              </div>
            </el-tab-pane>
          </el-tabs>

        </div>
      </div>
    </div>
  </div>

</template>

<script>
import {postRequest} from "@/api/data";

export default {
  name: "TeacherAccount",
  data() {
    return {
      activeName:"first",
      formData: {
        oldPassword: '',
        newPassword: '',
        newPasswordConfirm: '',
      },
      rules: {
        oldPassword: [
          { required: true, message: '请输入旧密码', trigger: 'blur' },
        ],
        newPassword: [
          { required: true, message: '请输入新密码', trigger: 'blur' },
        ],
        newPasswordConfirm: [
          { required: true, message: '请输入新密码确认', trigger: 'blur' },
          { validator: this.comparePasswords, trigger: 'blur' },
        ],
      },
    }
  },
  methods:{
    comparePasswords(rule, value, callback) {
      if (value === '') {
        callback(new Error('请再次输入密码'));
      } else if (this.formData.newPassword !== value) {
        callback(new Error('两次输入密码不一致!'));
      } else {
        callback();
      }
    },
    handleClick(tab, event) {
      console.log(tab, event);
    },
    handleSubmit(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          postRequest("/user/changePassword",this.formData).then(res=>{
            res
          })
        } else {
          console.log('error submit!');
          return false;
        }
      });
    },
  },
}
</script>

<style scoped>

</style>