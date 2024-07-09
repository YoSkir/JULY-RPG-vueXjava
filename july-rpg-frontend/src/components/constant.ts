//用於定義local storage的key
import {ref} from "vue";
import {Status} from "@/components/enums.ts";

export const USER_KEY:string="current_user";
const getCurrentUser=()=>{
    const userJson=localStorage.getItem(USER_KEY);
    if(userJson){
        return JSON.parse(userJson);
    }else {
        return {
            currentUsername: "",
            currentPassword: ""
        };
    }
}
interface CurrentUser{
    currentUsername:string,
    currentPassword:string
}
const currentUser=ref<CurrentUser>(getCurrentUser());
/**
 * 回傳物件變數名的中文
 * @param word
 */
export function getChinese(word:string){
    switch (word){
        case "UserInfo":
            return "使用者資訊";
        case "PlayerList":
            return "選手清單";
        default:
            return word;
    };
}
export function getStatChinese(stat:string){
    switch (stat){
        case Status.str:
            return "力量";
        case Status.abi:
            return "技巧";
        case Status.bod:
            return "體質";
        case Status.spd:
            return "速度";
        case Status.dex:
            return "敏捷";
        case Status.luc:
            return "幸運";
        case Status.mag:
            return "智力";
        default:
            return stat.toUpperCase();
    };
}
export const currentUsername=ref(currentUser.value.currentUsername);
export const currentPassword=ref(currentUser.value.currentPassword);
