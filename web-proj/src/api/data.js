import axios from "./axios";
export const getMenu = (param) =>{
    return axios.request({
        url:'/permission/getMenu',
        method: 'post',
        data:param
    })
}
export const getData = () =>{
    return axios.request({
        url:'/home/getData'
    })
}
export const editUser = (param) => {
    return axios.request({
        url:'/user/editUser',
        method: 'post',
        data:param
    })
}
export const addUser = (param) => {
    return axios.request({
        url:'/user/addUser',
        method: 'post',
        data:param
    })
}
export const getUser = (param) => {
    return axios.request({
        url:'/user/getUser',
        method: 'get',
        params:param
    })
}
export const delUser = (param) => {
    return axios.request({
        url:'/user/delUser',
        method: 'post',
        data:param
    })
}
export const getMall = (param) => {
    return axios.request({
        url:'/mall/getMall',
        method: 'get',
        params:param
    })
}
export const getRequest = (url, param) => {
    return axios.request({
        url,
        method: 'get',
        params:param
    })
}
export const getRequestWithData = (url, param, data) => {
    return axios.request({
        url,
        method: 'get',
        params:param,
        data:data
    })
}

export const postRequest = (url, param) => {
    return axios.request({
        url,
        method: 'post',
        data:param
    })
}
export const putRequest = (url, param) => {
    return axios.request({
        url,
        method: 'put',
        data:param
    })
}
export const delRequest = (url, param) => {
    return axios.request({
        url,
        method: 'delete',
        data:param
    })
}

export const doLogin = (param) => {
    return axios.request({
        url:'/login',
        method: 'post',
        params: param
    })
}