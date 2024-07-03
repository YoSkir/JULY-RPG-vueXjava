<script setup>
import {ref} from "vue";
import Popup from "@/components/common/Popup.vue";
const username=ref('');
const password=ref('');

const isError=ref(false);
const isChecking=ref(false);
const isMessage=ref(false);
const popupMsg=ref('');

/**
 * 創建新帳戶
 */
async function createUser(){
  //關閉確認視窗
  isChecking.value=false;
  try {
    const response=await fetch(`${import.meta.env.VITE_API_URL}/createUser`,{
      method:"POST",
      headers:{"Content-type":"application/json"},
      body:JSON.stringify({
        username:username.value,
        password:password.value
      })
    });
    if(!response.ok){
      errorPopup("失敗");
      return;
    }
    const data=await response.json();
    if(data.isCreateSucess){

    }else {
      errorPopup("創建失敗");
    }
  }catch (e){
    errorPopup(e);
  }
}

async function handleSubmit() {
  //帳號密碼輸入檢查
  if(!checkInput()){
    popupMsg.value="請輸入正確帳號密碼";
    isError.value=true;
    return;
  }
  //登入api
  try {
    const response = await fetch(`${import.meta.env.VITE_API_URL}/login`, {
      method:'POST',
      headers:{'Content-type':'application/json'},
      body:JSON.stringify({
        username:username.value,
        password:password.value
      })
    });
    if(!response.ok){
      errorPopup("失敗")
      return;
    }
    const data=await response.json();
    if(!data.isSuccess){
      //失敗後判斷是否為新使用者
      if(data.isNewUser){
        popupMsg.value=`是否創建新帳號?<br>帳號: ${username.value} 密碼: ${password.value}`;
        isChecking.value=true;
      }else {
        errorPopup(data.message);
      }
    }else {
      errorPopup("成功");
    }
  }catch (e){
    errorPopup(e);
  }
}

/**
 * 訊息彈出視窗
 * @param msg
 */
function errorPopup(msg){
  popupMsg.value=msg;
  isError.value=true;
}
function msgPopup(msg){
  popupMsg.value=msg;
  isMessage.value=true;
}

/**
 * 檢查輸入帳號密碼
 * @returns {boolean}
 */
function checkInput(){
  return !(username.value.length<1 || password.value.length<1);
}


</script>

<template>
  <div>
<!--    背景圖-->
    <div class="stars layer-0"></div>
    <div class="clouds layer-2"></div>
<!--    登入視窗-->
    <div class="login-container layer-3">
      <form class="login-form" @submit.prevent="handleSubmit">
        <input type="text" v-model="username" placeholder="帳號" class="input-box"/>
        <input type="password" v-model="password" placeholder="密碼" class="input-box"/>
        <button type="submit" class="submit-button">登入</button>
      </form>
    </div>
    <!--    彈出視窗-->
    <Popup :pop-up-msg="popupMsg" :is-checking="isChecking" :is-error="isError" :auto-hide="1000"
           @confirm="createUser" @cancel="isChecking=false" @update:is-error="isError=$event"
           @update:is-message="isMessage=$event" :is-message="isMessage"/>
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