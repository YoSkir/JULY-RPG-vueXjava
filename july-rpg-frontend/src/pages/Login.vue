<script setup lang="ts">
import {ref} from "vue";
import Popup from "@/components/common/Popup.vue";
import {ApiMethod, apiRequest, ApiResult, ApiUrl} from "@/components/api.ts";
import {changePage, Pages} from "@/components/router.ts";
import {USER_KEY} from "@/components/constant.ts";
import {PopupType} from "@/components/enums.ts";
import {ResultDto} from "@/dto/ResultDto.ts";


const username=ref('');
const password=ref('');

const popupType=ref(PopupType.none);
const popupMsg=ref('');
let popupResponse:((value:boolean)=>void)|null=null;

const apiResult=ref<ResultDto>();



/**
 * 創建新帳戶
 */
async function createUser(){
  try {
    apiResult.value=await apiRequest<ResultDto>({
      url:ApiUrl.createUser,
      method:ApiMethod.post,
      data:{username:username.value,password:password.value}
    });
    if(apiResult.value.isSuccess){
      msgPopup("創建成功")
      setTimeout(()=>{
        successLogin();
      },1000);
    }else {
      errorPopup("創建失敗");
    }
  }catch (e:any){
    errorPopup(e.message);
  }
}

async function loginSubmit() {
  //帳號密碼輸入檢查
  if(!checkInput()){
    popupMsg.value="請輸入正確帳號密碼";
    popupType.value=PopupType.error;
    return;
  }
  //登入api
  try {
    apiResult.value=await apiRequest<ResultDto>({
      url:ApiUrl.login,
      method:ApiMethod.post,
      data:{username:username.value,password:password.value}
  });
    if(!apiResult.value.isSuccess){
      //失敗後判斷是否為新使用者
      switch (apiResult.value?.message){
        case ApiResult.user_not_exist:
          await createUserCheck();
          break;
        case ApiResult.password_wrong:
          errorPopup("密碼錯誤")
          break;
        default:
          errorPopup("未知錯誤")
          break;
      }
      //登入成功
    }else {
      successLogin();
    }
  }catch (e:any){
    errorPopup(e.message);
  }
}

/**
 * 彈出是否創建新帳號的確認視窗並等待結果
 */
async function createUserCheck(){
  const isConfirmed=await checkingPopup(`是否創建新帳號?<br>帳號: ${username.value} 密碼: ${password.value}`);
  if(isConfirmed){
    await createUser();
  }
}

function handleCheckingResponse(isConfirmed:boolean){
  if(popupResponse){
    popupResponse(isConfirmed);
    popupResponse=null;
  }
}


function successLogin(){
  const currentUser={
    currentUsername:username.value,
    currentPassword:password.value
  };
  localStorage.setItem(USER_KEY,JSON.stringify(currentUser));
  changePage(Pages.game);
}

/**
 * 訊息彈出視窗
 * @param msg
 */
function errorPopup(msg:string){
  popupMsg.value=msg;
  popupType.value=PopupType.error;
}
function msgPopup(msg:string){
  popupMsg.value=msg;
  popupType.value=PopupType.message;
}
function checkingPopup(msg:string):Promise<boolean>{
  return new Promise((resolve)=>{
    popupResponse=resolve;
    popupMsg.value=msg;
    popupType.value=PopupType.checking;
  });
}

/**
 * 檢查輸入帳號密碼
 * @returns {boolean}
 */
function checkInput(){
  return !(username.value.length<1 || password.value.length<1);
}
</script>
<!--導出登出function-->
<script lang="ts">
  import {USER_KEY} from "@/components/constant.ts";
  import {changePage, Pages} from "@/components/router.ts";

  export function successLogout(){
    localStorage.removeItem(USER_KEY);
    changePage(Pages.login);
  }
</script>

<template>
  <div>
<!--    背景圖-->
    <div class="stars layer-0"></div>
    <div class="clouds layer-2"></div>
<!--    登入視窗-->
    <div class="login-container layer-3">
      <form class="login-form" @submit.prevent="loginSubmit">
        <input type="text" v-model="username" placeholder="帳號" class="input-box"/>
        <input type="password" v-model="password" placeholder="密碼" class="input-box"/>
        <button type="submit" class="submit-button">登入</button>
      </form>
    </div>
<!--    彈出視窗-->
    <Popup :pop-up-msg="popupMsg" :type="popupType" @update:is-popup="popupType=$event" @response:checking="handleCheckingResponse"/>
  </div>
</template>

<style scoped>
.login-form {
  display: flex;
  flex-direction: column;
  width: 300px;
}
.login-container {
  position: fixed;
  left: 38%;
  bottom: 0;
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
  background-color: rgba(30, 30, 30, 0.7); /* 使背景透明 */
  border-radius: 10px;
  padding: 20px;
}

.input-box {
  margin-bottom: 20px;
  padding: 10px;
  border: none;
  border-radius: 5px;
  background-color: rgba(45, 45, 45, 0.8); /* 使输入框背景透明 */
  color: #fff;
}

.input-box::placeholder {
  color: #999;
}

.input-box::placeholder {
  color: #999;
}


@keyframes slide-left {
  0% { transform: translateX(0); }
  100% { transform: translateX(-50%); }
}

.stars, .clouds {
  position: fixed;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  height: 100%;
  display: block;
}

.stars {
  //background:n #000 url('https://cdn.pixabay.com/photo/2019/10/20/11/51/mountains-4563464_1280.jpg') center;
  background: #000 url('https://images.pexels.com/photos/355465/pexels-photo-355465.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1') center;
  background-size: cover;
}
.stars::after {
  content: '';
  position: absolute;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  background-color: rgba(0, 0, 0, 0.5); /* 调暗背景 */
  z-index: 1;
}

.clouds {
  background: transparent url('https://www.script-tutorials.com/demos/360/images/clouds3.png') repeat top center;
  background-size: 50% 100%;
  animation: slide-left 20s linear infinite;
  width: 200%;
}
</style>