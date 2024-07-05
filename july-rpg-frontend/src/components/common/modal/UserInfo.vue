<script setup lang="ts">
  import {ref} from "vue";
  import {currentPassword, currentUsername} from "@/components/constant.ts";

  const username=ref(currentUsername);
  const password=ref(currentPassword);
</script>

<template>
  <div class="main-container">
    <div class="user-info-container">
      <div class="key-text">帳號: <span class="value-text">{{username}}</span></div>
      <div class="key-text">密碼: <span class="value-text">{{password}}</span></div>
    </div>
    <div class="button-container">
      <button class="submit-button" @click="">修改帳號</button>
      <button class="submit-button" @click="">修改密碼</button>
      <button class="delete-button" @click="">刪除帳號</button>
    </div>
  </div>
</template>

<style scoped>
.user-info-container {
  padding: 10px;
}

.main-container {
  width: 300px;
}
.button-container{
  margin: 5px;
  justify-content:space-between;
  display: flex;
}

.delete-button {
  padding: 10px;
  border: none;
  border-radius: 5px;
  background-color: #f38585;
  color: #fff;
  cursor: pointer;
  transition: background-color 0.2s;
}
.delete-button:hover{
  background-color: #b03636;
  color: #fff;
  transition: background-color 0.2s;
}

.key-text {
  margin: 10px;
  color: #03261e;
}

.value-text {
  margin: 10px;
  color: #a8ceab;
}
</style>