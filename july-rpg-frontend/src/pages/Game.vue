<script setup lang="ts">
import Sidebar from "@/components/common/Sidebar.vue";
import {onMounted} from "vue";
import {currentUsername} from "@/components/constant.ts";
import {changePage, Pages} from "@/components/router.ts";

onMounted(()=>{
  if(currentUsername.value==""){
    changePage(Pages.login);
  }
})
</script>

<template>
  <div>
    <div></div>
    <Sidebar/>
  </div>
</template>

<style scoped>

</style>