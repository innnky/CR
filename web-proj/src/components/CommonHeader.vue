<template>
  <header>
    <div class="l-content">
      <i class="el-icon-menu" @click="collapseMenu"></i>
      <!--    <h4>首页</h4>-->
      <el-breadcrumb separator="/">
        <el-breadcrumb-item v-for="item in tags" :key="item.path" :to="{ path: item.path }">{{ item.label }}
        </el-breadcrumb-item>
      </el-breadcrumb>
    </div>
    <div class="r-content">
      <el-dropdown trigger="hover" size="mini">
      <span>
        <img :src="userImg">
      </span>
        <el-dropdown-menu slot="dropdown">
          <el-dropdown-item>个人中心</el-dropdown-item>
          <el-dropdown-item @click.native="logout">退出</el-dropdown-item>
        </el-dropdown-menu>
      </el-dropdown>
    </div>
  </header>
</template>

<script>
import {mapState} from 'vuex'

export default {
  name: "CommonHeader",
  data() {
    return {
      userImg: require("../assets/images/User.jpg")
    }
  },
  methods: {
    collapseMenu() {
      // console.log('sss');
      this.$store.commit('collapseMenu')
    }
    ,logout(){
      this.$confirm('确定要退出登录?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$store.commit('clearToken')
        this.$router.push('/login')
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消'
        });
      });
    }
  },
  computed: {
    ...mapState({
      tags: state => state.tab.tabList
    })
  }
}
</script>

<style lang="less" scoped>
i{
  margin-right: 20px;
}
header {
  display: flex;
  height: 100%;
  justify-content: space-between;
  align-items: center;
}

.l-content {
  display: flex;
  align-items: center;

  .el-button {
    display: block;
    margin-right: 20px;

  }

  .el-breadcrumb ::v-deep .el-breadcrumb__inner {
    color: #737373 !important;
  }
}

.r-content {
  display: flex;
  align-items: center;

  img {
    height: 40px;
    width: 40px;
    border-radius: 50%;
  }
}
</style>
