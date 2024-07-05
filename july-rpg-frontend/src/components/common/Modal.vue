<script setup lang="ts">
import {computed, ref} from "vue";
import modalComponents from "@/components/common/modal/modal-components.ts";
import {getChinese} from "@/components/constant.ts";


const props=defineProps<{
  isOpen:Boolean;
  type:string;
}>();

const emit=defineEmits(["close"]);

const componentType=computed(()=>modalComponents[props.type]||null);

function close(){
  emit("close");
}
</script>

<template>
  <teleport to="body">
    <div v-if="isOpen" class="modal-overlay" @click.self="close">
      <div class="modal-container">
<!--        頂層標題-->
        <div class="title-content">
          <span class="title-text">{{getChinese(props.type)}}</span>
          <button class="close-button" @click="close">x</button>
        </div>
<!--        視窗內容-->
        <div class="modal-content">
          <component :is="componentType" v-if="componentType"/>
          <div v-else class="error">找不到視窗</div>
        </div>
      </div>
    </div>
  </teleport>
</template>

<style scoped>
.modal-overlay {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.5);
  display: flex;
  justify-content: center;
  align-items: center;
  z-index: 9999;
}
.title-content {
  height: 35px;
}
.modal-content {
  background: #598372;
  padding: 20px;
  border-bottom-left-radius: 20px;
  border-bottom-right-radius: 20px;
  position: relative;
}

.close-button {
  position: absolute;
  right: 10px;
  background: none;
  color: #bcf5ba;
  border: none;
  font-size: 1.5em;
  cursor: pointer;
}
.error {
  color: #dc5a5a;
}


.modal-container {
  background: #303f39;
  border-bottom-right-radius: 20px;
  border-bottom-left-radius: 20px;
  border-top-right-radius: 20px;
  position: relative;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
}

.title-text {
  margin-left: 20px;
  font-size: 1.4em;
}
</style>