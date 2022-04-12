<template>
  <div class="container h-100">
    <div class="row  align-items-center" style="height: 100%;">
      <div class="col-12 bg-white py-2 mb-0" style="height: 90%">
        <div class="row ">
          <div class="col-3">      <i class="el-icon-search m-2"></i><span class="smalltitle">筛选查询</span></div>
        </div>
        <div class="row px-4  py-2">
          <el-tabs v-model="activeName" @tab-click="handleClick">
            <el-tab-pane label="课程管理" name="first">
              <course-panel></course-panel>
            </el-tab-pane>
            <el-tab-pane label="课程安排" name="second">
              <course-plan-panel/>
            </el-tab-pane>

          </el-tabs>

        </div>
      </div>
    </div>
  </div>

</template>

<script>
import CoursePanel from "@/components/system/CoursePanel";
import CoursePlanPanel from "@/components/system/CoursePlanPanel";
export default {
  name: "CourseManagement",
  components: { CoursePlanPanel, CoursePanel},
  data() {
    return {
      activeName:"first",
    }
  },
  mounted() {
    if (this.$route.query.isReturning){
      this.activeName = 'second'
    }
  }
}
</script>

<style scoped>

</style>