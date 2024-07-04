<script setup lang="ts">
import {ref, onMounted, onUnmounted, computed} from "vue";

const isOpen=ref(false);
const options=ref(["選手清單"]);

const SIDEBAR_WIDTH:number=window.innerWidth/5<250? 250:window.innerWidth/5;
const sidebarStyle=computed(()=>({
  '--sidebar-width':`${SIDEBAR_WIDTH}px`
}));


function logout(){

}
function handleOptionClick(option:string){

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