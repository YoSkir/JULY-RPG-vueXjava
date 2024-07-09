<script setup lang="ts">
import {onMounted, onUnmounted, ref} from "vue";
import Modal from "@/components/common/Modal.vue";
import {successLogout} from "@/pages/Login.vue";
import {currentUsername, getChinese} from "@/components/constant.ts";

//欄位表
enum Option{PlayerList="PlayerList"}
//欄寬
const SIDEBAR_WIDTH:number=window.innerWidth/5<250? 250:window.innerWidth/5;

//控制側邊欄位開關
const isOpen=ref(false);
//側邊欄位選項arr
const options=ref(Option);
//控制小視窗開關
const isModalOpen=ref(false);
//小視窗檔案名稱
const modalType=ref<string>("");
//目前登入者資訊
const username=ref(currentUsername);



//登出
function logout(){
  successLogout();
}
//控制選項點擊
function handleOptionClick(option:Option){
  openModal(option);
}
//打開小視窗
function openModal(type:string){
  modalType.value=type;
  isModalOpen.value=true;
}
//關閉小視窗
function closeModal(){
  isModalOpen.value=false;
  modalType.value="";
}

//以下為滑鼠偵測
function handleMouseMove(event:MouseEvent){
  if(event.clientX<=SIDEBAR_WIDTH/2){
    handleMouseEnter();
  }
  if(event.clientX>SIDEBAR_WIDTH){
    handleMouseLeave();
  }
}
function handleMouseEnter(){
  isOpen.value=true;
}
function handleMouseLeave(){
  isOpen.value=false;
}
onMounted(()=>{
  document.documentElement.style.setProperty('--sidebar-width', `${SIDEBAR_WIDTH}px`);
  window.addEventListener('mousemove',handleMouseMove);
});
onUnmounted(()=>{
  window.removeEventListener('mousemove',handleMouseMove);
});

</script>

<template>
  <div class="sidebar" :class="{open:isOpen}" @mouseleave="handleMouseLeave" >
<!--    選項-->
    <div class="options">
      <div class="option" v-for="option in options" :key="option" @click="handleOptionClick(option)">
        {{getChinese(option)}}
      </div>
    </div>
<!--    登出-->
    <div class="logout-container">
      <div class="user-info" @click="openModal('UserInfo')">{{username}}</div>
      <div class="logout" @click="logout">
        登出
      </div>
    </div>
  </div>
  <Modal :is-open="isModalOpen" :type="modalType" @close="closeModal"></Modal>
</template>

<style scoped>
.sidebar {
  position: fixed;
  top: 0;
  left: calc(var(--sidebar-width)*-1); /* 初始状态下隐藏 */
  width: var(--sidebar-width);
  height: 100%;
  background-color: #333;
  color: #94b29e;
  transition: left 0.3s;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  border-top-right-radius: 20px; /* 设置右上角为圆角 */
  border-bottom-right-radius: 20px; /* 设置右下角为圆角 */
}

.sidebar.open {
  left: 0; /* 显示侧边栏 */
}

.options {
  padding: 10px;
}

.option {
  padding: 10px;
  cursor: pointer;
  position: relative; /* 为了让子元素绝对定位 */
  transition: all 0.3s; /* 过渡效果 */
  border-top-right-radius: 20px; /* 设置右上角为圆角 */
  border-bottom-right-radius: 20px; /* 设置右下角为圆角 */
  text-align: center;
}

.option::before {
  content: '';
  position: absolute;
  top: -1px; /* 浮出效果的外边距 */
  bottom: -1px;
  left: -50px;
  right: -1px;
  border: 2px solid transparent; /* 边框初始透明 */
  border-top-right-radius: 20px; /* 设置右上角为圆角 */
  border-bottom-right-radius: 20px; /* 设置右下角为圆角 */
  transition: all 0.2s; /* 过渡效果 */
}

.option:hover {
  background-color: #336b46;
  color: white;
  animation: slide-right 0.2s linear forwards;
  z-index: 2;
}
.option:not(:hover){
  animation: slide-back 0.1s linear forwards;
}
@keyframes slide-right {
  0%{transform:translate(0,0);}
  100%{transform:translate(20px,3px);}
}
@keyframes slide-back {
  0%{transform:translate(20px,3px);}
  100%{transform:translate(0,0);}
}

.option:hover::before {
  border-color: #00b894; /* 设置边框颜色 */
  z-index: -1;
  background-color: #336b46;
  transition: all 0.2s; /* 过渡效果 */
}

.logout-container{
  padding: 10px;
  background: #37423c;
}
.logout {
  padding: 10px;
  cursor: pointer;
  background-color: #444;
  text-align: center;
  border-radius: 20px;
}

.logout:hover {
  background-color: #336b46;
  color: white;
  transition: all 0.3s; /* 过渡效果 */
}
.user-info{
  color: #15d559;
  text-align: center;
  padding: 5px;
  margin-bottom: 5px;
  margin-left: 20px;
  margin-right: 20px;
}
.user-info:hover{
  background-color: #f8efc1;
  color: #46390f;
  transition: all 0.3s; /* 过渡效果 */
  border-radius: 20px;
}
</style>