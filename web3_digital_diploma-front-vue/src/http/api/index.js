import axios from "axios";
import { error } from "jquery";

//创建axios实例
const serviceAxios = axios.create({
    baseURL: '',
    timeout: 10000,
})

//请求拦截
serviceAxios.interceptors.request.use(
    (config) => {
        console.log('请求配置', config)
        //业务逻辑
    },
    (error) => {
        console.log('error', error)
    }
)