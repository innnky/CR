import Vue from "vue";
import VueRouter from 'vue-router'
import Main from "@/components/Main";
// import login from "@/views/login";
import DepartmentManagement from "@/views/system/DepartmentManagement";
import AccountManagement from "@/views/system/AccountManagement";
import StudentCourses from "@/views/student/StudentCourses";
import StudentCourseDetail from "@/views/student/StudentCourseDetail";
import StudentExercise from "@/views/student/StudentExercise";
import StudentExerciseDetail from "@/views/student/StudentExerciseDetail";
import StudentDevices from "@/views/student/StudentDevices";
import StudentMaterial from "@/views/student/StudentMaterial";
import StudentAccount from "@/views/student/StudentAccount";
import TeacherCourses from "@/views/teacher/TeacherCourses";
import TeacherCourseDetail from "@/views/teacher/TeacherCourseDetail";
import TeacherExercise from "@/views/teacher/TeacherExercise";
import DeviceManagement from "@/views/common/DeviceManagement";
import SceneManagement from "@/views/common/SceneManagement";
import TeacherAccount from "@/views/teacher/TeacherAccount";
import ClassManagement from "@/views/system/ClassManagement";
import ImageManagement from "@/views/common/ImageManagement";
import StudentManagement from "@/views/system/StudentManagement";
import TeacherManagement from "@/views/system/TeacherManagement";
import CourseManagement from "@/views/system/CourseManagement";
import CourseTablePanel from "@/components/system/CourseTablePanel";
import TeacherClasses from "@/views/teacher/TeacherClasses";
import StudentClass from "@/views/student/StudentClass";
import Registration from "@/views/common/Registration";
import Login from "@/views/common/Login";
Vue.use(VueRouter)
const routes = [
    {
        path:'/',
        name:'Main',
        component: Main,
        redirect:'/homePage',
        children:[
            {
                path: '/departmentManagement',
                component: DepartmentManagement
            },
            {
                path: '/accountManagement',
                component: AccountManagement
            },
            {
                path: "/StudentCourses",
                component: StudentCourses
            },
            {
                path: "/StudentCourseDetail",
                component: StudentCourseDetail
            },
            {
                path: '/StudentExercise',
                component: StudentExercise
            },
            {
                path: '/StudentExerciseDetail',
                component: StudentExerciseDetail
            },
            {
                path: '/StudentDevices',
                component: StudentDevices
            },
            {
                path: '/StudentMaterial',
                component: StudentMaterial
            },
            {
                path: '/StudentAccount',
                component: StudentAccount
            },
            {
                path: '/TeacherCourses',
                component: TeacherCourses
            },
            {
                path: '/TeacherCourseDetail',
                component: TeacherCourseDetail
            },
            {
                path: '/TeacherExercise',
                component: TeacherExercise
            },
            {
                path: "/DeviceManagement",
                component: DeviceManagement
            },
            {
                path: '/SceneManagement',
                component: SceneManagement
            },{
                path: '/ImageManagement',
                component: ImageManagement
            },
            {
                path: '/TeacherAccount',
                component: TeacherAccount
            },
            {
                path: "/ClassManagement",
                component: ClassManagement
            },
            {
                path: '/DepartmentManagement',
                component: DepartmentManagement
            },
            {
                path: '/StudentManagement',
                component: StudentManagement
            },
            {
                path: '/TeacherManagement',
                component: TeacherManagement
            },
            {
                path: '/CourseManagement',
                component: CourseManagement

            },
            {
                path: "/CourseTablePanel",
                component: CourseTablePanel
            },
            {
                path: "/TeacherClasses",
                component: TeacherClasses
            },
            {
                path: "/StudentClass",
                component: StudentClass
            }

        ]

    },
    {
        path: '/login',
        name:'login',
        component: Login
    },
    {
        path: "/Registration",
        name: 'Registration',
        component: Registration
    }
]
const router = new VueRouter({
    mode:"history",
    routes
})

export default router
