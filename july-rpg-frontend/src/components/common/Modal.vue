<script setup lang="ts">
import {computed} from "vue";
import modalComponents from "@/components/common/modal/modal-components.ts";


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
      <div class="modal-content">
        <button class="close-button" @click="close">x</button>
        <component :is="componentType" v-if="componentType"/>
        <div v-else class="error">找不到視窗</div>
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

.modal-content {
  background: #598372;
  padding: 20px;
  border-radius: 5px;
  position: relative;
}

.close-button {
  position: absolute;
  top: 10px;
  right: 10px;
  background: none;
  border: none;
  font-size: 1.5em;
  cursor: pointer;
}
.error {
  color: #dc5a5a;
}
</style>