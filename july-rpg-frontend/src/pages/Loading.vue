<script setup lang="ts">
import {ref,onMounted,onUnmounted} from "vue";

const dots=ref("");
let intervalId:number;

onMounted(()=>{
  let dotCount=0;
  intervalId=window.setInterval(()=>{
    dotCount=(dotCount+1)%7;
    dots.value=".".repeat(dotCount);
  },100);
});
onUnmounted(()=>{
  clearInterval(intervalId);
})
</script>

<template>
  <div class="loading">
    <span class="loading-text">讀取中<span class="dots">{{dots}}</span></span>
  </div>
</template>

<style scoped>
@keyframes rainbow {
  0% { color: #dc5c5c; }
  20% { color: #e08452; }
  40% { color: #d2c153; }
  60% { color: #5cbb5c; }
  80% { color: #5cb6b6; }
  100% { color: #ad76d5; }
}
.loading {
  position: fixed;
  left: 45%;
  padding: 10px;
  border-radius: 5px;
  font-size: 2em;
  z-index: 9999;
}

.dots,.loading-text {
  display: inline-block;
  animation: rainbow 1s linear infinite;
}
</style>