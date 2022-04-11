<template>
  <div class="container">
    <div class="row mt-4 mb-2">
      <span>我的课程</span>
    </div>
    <div class="row">
      <div class="col bg-white pb-4">
        <div class="row justify-content-between mt-3 px-3">
          <div class="col-2">
            <div style="height: 40px;background: #f3f3f3; border-radius: 20px;text-align: center;line-height: 40px;cursor: pointer;font-size: 12px">查看课表</div>
          </div>
          <div class="col-3">
            <div style="height: 40px;background: #f3f3f3; border-radius: 20px;text-align: left;padding-left:22px;line-height: 40px;cursor: pointer;font-size: 12px">搜索
              <i class="el-icon-search" style="float: right;font-size: 20px;line-height: 40px;margin-right: 20px"></i>
            </div>
          </div>
        </div>
        <div class="row g-4 px-3 mt-0">
          <div class="col-3" style="height: 240px" v-for="i in cards" :key="i">
            <div class="h-100" style="background:#f3f3f3;cursor: pointer" @click="showCourseDetail()">
              <div class="w-100 h-75" style="background:#e5faf9;"></div>
              <div class="px-3 mt-1">
                <span>计算机网络</span><br>
                <span>kkkkkk</span>
              </div>
            </div>
          </div>

        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "TeacherCourses",
  data(){
    return{
      activeName:"first",
      cards:[1,2,3,4,5,6,7,8,9,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,]
    }
  },
  methods:{
    showCourseDetail(){
      this.$router.push("/TeacherCourseDetail")
    }
  }
}
</script>

<style scoped>
.container{
  color: #505050;

}
span{
  font-size: 13px;
}
</style>