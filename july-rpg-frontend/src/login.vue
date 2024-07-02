<script setup>
import {ref} from "vue";
const username=ref('');
const password=ref('');
const isLoginFail=ref(false);
const isNewUser=ref(false);
const errorMsg=ref('');

async function handleSubmit() {
  //帳號密碼輸入檢查
  if(!checkInput()){
    errorPopup("請輸入正確帳號密碼");
    return;
  }

  //登入api
  try {
    const response = await fetch(`${import.meta.env.VITE_API_URL}/login`, {
      method:'POST',
      headers:{
        'Content-type':'application/json'
      },
      body:JSON.stringify({
        username:username.value,
        password:password.value
      })
    });
    if(!response.ok){
      console.log('api錯誤')
      return;
    }
    const data=await response.json();
    if(!data.isSuccess){
      //失敗後判斷是否為新使用者
      if(data.isNewUser){
        isNewUser.value=true;
      }else {
        errorPopup(data.message);
      }
    }else {
      errorPopup("成功");
    }
  }catch (e){
    errorPopup("登入異常 "+e);
  }

}

/**
 * 檢查輸入帳號密碼
 * @returns {boolean}
 */
function checkInput(){
  return !(username.value.length<1 || password.value.length<1);
}

/**
 * 登入異常時彈出視窗
 * @param msg
 */
function errorPopup(msg){
  isLoginFail.value=true;
  errorMsg.value=msg;
  setTimeout(()=>{
    isLoginFail.value=false;
    errorMsg.value='';
  },1000);
}

/**
 * 創建新帳戶
 */
function createUser(){
  errorPopup("幹");
  isNewUser.value=false;
}
</script>

<template>
  <div class="login-container">
    <form class="login-form" @submit.prevent="handleSubmit">
      <input type="text" v-model="username" placeholder="帳號" class="input-box"/>
      <input type="password" v-model="password" placeholder="密碼" class="input-box"/>
      <button type="submit" class="submit-button">登入</button>
    </form>
    <transition name="fade">
      <div v-if="isLoginFail" class="error-popup">
        {{errorMsg}}
      </div>
    </transition>
    <div v-if="isNewUser" class="new-user-popup">
      <p>是否創建新帳號?</p>
      <p>帳號:{{username}} 密碼:{{password}}</p>
      <div class="button-container">
        <button @click="createUser" class="submit-button">確定</button>
        <button @click="isNewUser=false" class="submit-button">取消</button>
      </div>
    </div>
  </div>
</template>

<style scoped>
.login-container {
  position: fixed;
  left: 38%;
  bottom: 0;
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
  background-color: #1e1e1e;
}

.login-form {
  display: flex;
  flex-direction: column;
  width: 300px;
}

.input-box {
  margin-bottom: 20px;
  padding: 10px;
  border: none;
  border-radius: 5px;
  background-color: #2d2d2d;
  color: #fff;
}

.input-box::placeholder {
  color: #999;
}

.submit-button {
  padding: 10px;
  border: none;
  border-radius: 5px;
  background-color: #00b894;
  color: #fff;
  cursor: pointer;
  transition: background-color 0.3s;
}

.submit-button:hover {
  background-color: #009874;
}
.error-popup {
  position: fixed;
  bottom: 50%;
  left: 50%;
  transform: translateX(-50%);
  padding: 10px 20px;
  background-color: #e74c3c;
  color: white;
  border-radius: 5px;
  box-shadow: 0 2px 10px rgba(0, 0, 0, 0.2);
}
.new-user-popup {
  position: fixed;
  bottom: 50%;
  left: 50%;
  transform: translateX(-50%);
  padding: 10px 20px;
  background-color: #32ad5c;
  color: #000000;
  border-radius: 5px;
  box-shadow: 0 2px 10px rgba(0, 0, 0, 0.2);
}
.fade-enter-active, .fade-leave-active {
  transition: opacity 0.5s;
}

.fade-enter, .fade-leave-to /* .fade-leave-active in <2.1.8 */ {
  opacity: 0;
}

.button-container {
  display: flex;
  justify-content: space-between;
  margin-top: 10px;
}
</style>