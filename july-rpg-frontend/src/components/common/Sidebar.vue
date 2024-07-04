<script setup lang="ts">
import {onMounted, onUnmounted, ref} from "vue";
import {changePage, Pages} from "@/components/router.ts";
import Modal from "@/components/common/Modal.vue";

//欄位表
enum Option{player_list="選手清單"}
const modalMap=new Map<Option,string>([
    [Option.player_list,"PlayerList"]
]);
//欄寬
const SIDEBAR_WIDTH:number=window.innerWidth/5<250? 250:window.innerWidth/5;

//控制側邊欄位開關
const isOpen=ref(false);
//側邊欄位選項arr
const options=ref([Option.player_list]);
//控制小視窗開關
const isModalOpen=ref(false);
//小視窗檔案名稱
const modalType=ref<string|null>(null);


//登出
function logout(){
  changePage(Pages.login);
}
//控制選項點擊
function handleOptionClick(option:Option){
  logout();
  const modalComponent=modalMap.get(option);
  if(modalComponent){
    openModal(modalComponent);
  }
}
//打開小視窗
function openModal(type:string){
  modalType.value=type;
  isModalOpen.value=true;
}
//關閉小視窗
function closeModal(){
  isModalOpen.value=false;
  modalType.value=null;
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
        {{option}}
      </div>
    </div>
<!--    登出-->
    <div class="logout" @click="logout">
      登出
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
}

.option:hover {
  background-color: #336b46;
  color: white;
}

.logout {
  padding: 10px;
  cursor: pointer;
  background-color: #444;
  text-align: center;
}

.logout:hover {
  background-color: #336b46;
  color: white;
}
</style>