import axios,{AxiosInstance,AxiosRequestConfig,AxiosResponse} from 'axios';

/**
 * 將api請求封裝成一個類別
 */

//定義回傳結果
export enum ApiResult{success="成功",fail="失敗",password_wrong="密碼錯誤",user_not_exist="使用者不存在"}
//定義api方法
export enum ApiMethod{post="POST",get="GET"}
//整理api路由
export enum ApiUrl{
    //login
    login="/login",createUser="/createUser",
    //player
    selectPlayerList="/selectPlayerList"
}

//回傳結果的容器
export interface ApiResponse{
    isSuccess:boolean,
    message:ApiResult
}

//定義通用API請求函數
interface ApiRequestConfig extends AxiosRequestConfig{
    url:string;
    method?:ApiMethod;
    data?:any;
}

//axios實例
const apiClient:AxiosInstance=axios.create({
    baseURL:import.meta.env.VITE_API_URL,
    headers:{
        'Content-Type':'application/json',
    },
});

export async function apiRequest<T>(config:ApiRequestConfig):Promise<T>{
    const response:AxiosResponse<T>=await apiClient.request<T>(config);
    return response.data;
}