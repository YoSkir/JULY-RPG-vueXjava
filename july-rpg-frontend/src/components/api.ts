import axios,{AxiosInstance,AxiosRequestConfig,AxiosResponse} from 'axios';

/**
 * 將api請求封裝成一個類別
 */

//定義回傳結果
export enum ApiResult{success="success",fail="fail",password_wrong="password_wrong",user_not_exist="user_not_exist"}
//定義api方法
export enum ApiMethod{post="POST",get="GET"}
//整理api路由
export enum ApiUrl{
    //login
    login="/login",createUser="/createUser",
    //player
    selectPlayerList="/selectPlayerList", generatePlayer="/generatePlayer",levelUp="/levelUp"
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