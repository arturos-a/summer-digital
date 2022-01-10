import axios from 'axios'
import {getTokenName} from "@/constants/constants";
import {getTimeOut} from "@/constants/constants";

function apiClient() {
    let token = localStorage.getItem(getTokenName());
    return axios.create({
        baseURL: 'http://localhost:8081',
        timeout: getTimeOut(),
        headers: {'X-AUTH-TOKEN': token}
    });
}

export default apiClient();
