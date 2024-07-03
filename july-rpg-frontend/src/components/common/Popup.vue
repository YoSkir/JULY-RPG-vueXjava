<script setup>
import {defineProps,defineEmits,watch} from "vue";

const props=defineProps({
  popUpMsg:String,
  isError:Boolean,
  isChecking:Boolean,
  autoHide:Number
})
const emit=defineEmits(["update:isChecking","update:isError","confirm","cancel"]);

/**
 * 彈出視窗自動消失
 */
watch(()=>props.isError,(newVal)=>{
  if(newVal&&props.autoHide>0){
    setTimeout(()=>{
      emit("update:isError",false);
    },props.autoHide);
  }
});
function confirmAction(){
  props.isChecking=false;
  emit("confirm");
}
function cancelAction(){
  props.isChecking=false;
  emit("cancel");
}
</script>

<template>
  <!--      錯誤視窗-->
  <transition name="fade">
    <div v-if="isError" class="error-popup">
      <p>{{popUpMsg}}</p>
    </div>
  </transition>
  <!--      確認視窗-->
  <div v-if="isChecking" class="new-user-popup">
    <p>{{popUpMsg}}</p>
    <div class="button-container">
      <button @click="confirmAction" class="submit-button">確定</button>
      <button @click="cancelAction" class="submit-button">取消</button>
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