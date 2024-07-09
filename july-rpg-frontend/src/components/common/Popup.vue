<script setup lang="ts">
import {ref, watch} from "vue";
import {PopupType} from "@/components/enums.ts";

const autoHide:number=2000;


  
const props=defineProps({
  popUpMsg:String,
  type:String as ()=>PopupType,
})
const emit=defineEmits(["update:isPopup","response:checking"]);

/**
 * 彈出視窗自動消失
 */
watch(()=>props.type,(newVal)=>{
  if((newVal===PopupType.error||newVal===PopupType.message) && autoHide && autoHide>0){
    setTimeout(()=>{
      emit("update:isPopup",PopupType.none);
    },autoHide);
  }
});
function confirmAction(){
  emit("update:isPopup",PopupType.none);
  emit("response:checking",true);
}
function cancelAction(){
  emit("update:isPopup",PopupType.none);
  emit("response:checking",false);
}
</script>

<template>
  <div class="layer-5 popup">
    <!--  警告視窗-->
    <transition name="fade">
      <div v-if="type===PopupType.error" class="error-popup">
        <div v-html="popUpMsg"></div>
      </div>
    </transition>
    <!--  訊息視窗-->
    <transition name="fade">
      <div v-if="type===PopupType.message" class="message-popup">
        <div v-html="popUpMsg"></div>
      </div>
    </transition>
    <!--      確認視窗-->
    <div v-if="type===PopupType.checking" class="check-popup">
      <div v-html="popUpMsg"></div>
      <div class="button-container">
        <button @click="confirmAction" class="submit-button">確定</button>
        <button @click="cancelAction" class="submit-button">取消</button>
      </div>
    </div>
  </div>
</template>

<style scoped>
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
.check-popup {
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
.message-popup{
  position: fixed;
  bottom: 50%;
  left: 50%;
  transform: translateX(-50%);
  padding: 10px 20px;
  background-color: #1c7966;
  color: white;
  border-radius: 5px;
  box-shadow: 0 2px 10px rgba(129, 211, 181, 0.2);
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

.popup {
  position: fixed;
}
</style>