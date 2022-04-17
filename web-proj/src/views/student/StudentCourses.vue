<template>
<div class="container">
  <div class="row mt-4 mb-2">
    <span>我的课程</span>
  </div>
  <div class="row" style="box-shadow: #d7d7d7 0px 0px 10px 1px;min-height:650px;">
    <div class="col bg-white pb-4">
      <div class="row justify-content-between mt-3 px-3">
        <div class="col-2">
          <div style="height: 40px;background: #f3f3f3; border-radius: 20px;text-align: center;line-height: 40px;cursor: pointer;font-size: 12px;box-shadow: #d7d7d7 0px 0px 10px 1px">查看课表</div>
        </div>
        <div class="col-3">
          <div  style="box-shadow: #dcdcdc 0px 0px 10px 1px;height: 40px;background: #f3f3f3; border-radius: 20px;text-align: left;padding-left:22px;line-height: 40px;cursor: pointer;font-size: 12px">
            <input v-model="inputData"/>
            <i @click="handleSearch" class="el-icon-search" style="float: right;font-size: 20px;line-height: 40px;margin-right: 20px"></i>
          </div>
        </div>
      </div>
      <div class="row g-4 px-3 mt-0">
        <div class="col-3" style="height: 260px" v-for="i in course" :key="i">
          <div class="h-100" style="background:#f3f3f3;cursor: pointer;
          box-shadow: #cecece 0px 0px 10px 1px" @click="showCourseDetail(i)">
            <div class="w-100" style="height: 65%;"><el-image
                style="width: 100%; height: 100%"
                :src="i.courseImg"
                fit="fill"></el-image></div>
            <div class="px-3 mt-1">
              <span>{{ i.courseName }}</span><br>
              <span>{{ i.courseDescription }}</span>
            </div>
          </div>
        </div>

      </div>
    </div>
  </div>
</div>
</template>

<script>
import {getRequest} from "@/api/data";

export default {
  name: "StudentCourses",
  data(){
    return{
      activeName:"first",
      course:[],
      inputData:""
    }
  },
  methods:{
    showCourseDetail(row){
      // {
      //   path: "/CourseTablePanel",
      //       query: {
      //   scourseId: row.scourseId
      // }
      // }"/StudentCourseDetail"
      this.$router.push({
        path: "/StudentCourseDetail",
        query: {
          scourseId: row.scourseId
        }
      })
    },
    initCourses(){
      getRequest("/student/course/",{
        courseName:this.inputData
      }).then(res=>{
        this.course = res;
      })
    },
    handleSearch(){
      this.initCourses();
    }
  },
  mounted() {
    this.initCourses();
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
input {
  outline: none;
  background: transparent;
  border: none;
  outline: medium;
  /*display: block;*/
}
/*   input:focus {*/
/*     outline: none;*/
/*     background-color: transparent;*/
/*   }*/
/*   ::selection {*/
/*     background: transparent;*/
/*   }*/
/*::-moz-selection {*/
/*  background: rgb(241, 241, 241);*/
/*}*/
</style>