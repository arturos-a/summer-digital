import axios from 'axios'
import {getTimeOut, getTokenName} from "@/constants/constants";
import router from "@/router/router";
import 'dotenv/config'

function apiClient() {
    const tokenName = getTokenName()
    let axiosInstance = axios.create({
        baseURL: process.env.VUE_APP_BASE_URL,
        timeout: getTimeOut(),
        headers: {tokenName: localStorage.getItem(getTokenName())},
    });

    axiosInstance.interceptors.request.use(config => {
        config.headers[tokenName] = localStorage.getItem(getTokenName());
        return config;
    }, function (error) {
        return Promise.reject(error);
    });

    axiosInstance.interceptors.response.use(function (response) {
        return response;
    }, error => {
        if (error.response.status == 401) {
            localStorage.removeItem(getTokenName());
            router.push("/")
        }
        return Promise.reject(error);
    });

    return axiosInstance;
}

export default apiClient();
