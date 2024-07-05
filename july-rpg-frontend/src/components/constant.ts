//用於定義local storage的key
import {ref} from "vue";

export const USER_KEY:string="current_user";
const getCurrentUser=()=>{
    const userJson=localStorage.getItem(USER_KEY);
    if(userJson){
        return  JSON.parse(userJson);
    }
}
const currentUser=ref(getCurrentUser());
/**
 * 回傳物件變數名的中文
 * @param word
 */
export function getChinese(word:string){
    console.log(word);
    switch (word){
        case "UserInfo":
            return "使用者資訊";
        case "PlayerList":
            return "選手清單";
        default:
            return word;
    };
}
export const currentUsername=ref(currentUser.value.currentUsername);
export const currentPassword=ref(currentUser.value.currentPassword);
