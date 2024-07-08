<script setup lang="ts">
import {onMounted, ref} from "vue";
import {ApiMethod, apiRequest, ApiResponse, ApiUrl} from "@/components/api.ts";

const playerList=ref();
const isPlayerEmpty=ref(true);

/**
 * 讀取選手列表
  */
async function loadPlayerList(){
    try {
      playerList.value=await apiRequest<ApiResponse>({
        url: ApiUrl.selectPlayerList,
        method: ApiMethod.get
      });
      isPlayerEmpty.value=playerList.value.isEmpty();
    }catch (e:any){
      console.log("選手列表讀取失敗 "+e.message);
    }
  }
function generatePlayer(){

}


onMounted(()=>{
    loadPlayerList();
  });
</script>

<template>
  <div v-if="isPlayerEmpty">
    <button @click="generatePlayer" class="submit-button">生成選手</button>
  </div>
  <div v-else class="player-list-container">
    <div class="player-container" v-for="player in playerList" key="playerId">
      <div class="player-name">{{player.name}}</div>
      <div class="player-status-container">
        <span class="level">等級:<span class="level-value">{{player.status.get("level")}}</span></span>
        <span>
          <div class="status">HP:<span class="status-value">{{player.status.get("hp")}}</span></div>
          <div class="status">MP:<span class="status-value">{{player.status.get("mp")}}</span> </div>
        </span>
      </div>
    </div>
  </div>
</template>

<style scoped>
.player-list-container {
}

.player-container {
}

.status-value {
}

.status {
}

.level {
}

.level-value {
}

.player-status-container {
}

.player-name {
}
</style>