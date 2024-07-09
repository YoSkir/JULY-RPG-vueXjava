<script setup lang="ts">
import {onMounted, ref} from "vue";
import {ApiMethod, apiRequest, ApiUrl} from "@/components/api.ts";
import Popup from "@/components/common/Popup.vue";
import {PlayerInfoDto} from "@/dto/PlayerInfoDto.ts";
import {PopupType, Status} from "@/components/enums.ts";
import {ResultDto} from "@/dto/ResultDto.ts";
import StatusBlock from "@/components/StatusBlock.vue";
import {getStatChinese} from "@/components/constant.ts";

//玩家列表用
const playerList=ref<PlayerInfoDto[]>([]);
const isPlayerEmpty=ref(true);

//彈出視窗用
const popupType=ref(PopupType.none);
const popupMsg=ref('');

//排列能力值用
const statusGroups=ref([2,2,2,2,1]);

/**
 * 讀取選手列表
  */
async function loadPlayerList(){
    try {
      playerList.value=await apiRequest<any>({
        url: ApiUrl.selectPlayerList,
        method: ApiMethod.get
      });
      if(playerList.value.length<1){
        isPlayerEmpty.value=true;
        return;
      }
      isPlayerEmpty.value=false;
    }catch (e:any){
      console.log("選手列表讀取失敗 "+e.message);
    }
  }

/**
 * 將能力值依照 statusGroups變數所訂數量分組
 */
function groupedStatus(): string[][] {
  // 要回傳的分組
  const groups: string[][] = [];
  // 計算每一組的大小
  let groupCount = 0;
  // 目前哪個組
  let groupIndex = 0;

  // 初始化分組數組
  statusGroups.value.forEach(() => groups.push([]));

  // 照 enum 的順序一個一個塞入
  for (const stat in Status) {
    // 塞入
    groups[groupIndex].push(stat);
    // 組大小增加
    groupCount++;
    // 如果組大小到達分組數量，則往下一組
    if (groupCount >= statusGroups.value[groupIndex]) {
      groupCount = 0;  // 重置組大小計數器
      groupIndex++;
    }
  }
  return groups;
}

/**
 * 創建選手(測試用
 */
async function generatePlayer(){
  try {
    const response=await apiRequest<ResultDto>({
      url:ApiUrl.generatePlayer,
      method:ApiMethod.get
    });
    if(response.isSuccess){
      await loadPlayerList();
      return;
    }else {
      popupType.value=PopupType.error;
      popupMsg.value="創建選手失敗";
    }
  }catch (e:any){
    console.log("創建選手失敗"+e.message);
  }
}

async function levelUp(){
  try {
    const response=await apiRequest<ResultDto>({
      url:ApiUrl.levelUp,
      method:ApiMethod.get
    });
    if(!response.isSuccess){
      popupMsg.value="升級失敗";
      popupType.value=PopupType.error;
    }
    await loadPlayerList();
  }catch (e:any){
    console.log("升級失敗"+e.message)
  }
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
    <div class="player-container" v-for="player in playerList" key="player.id">
      <div class="player-left">
        <div class="player-name">{{player.name}}</div>
        <div class="level"> 等級: <span class="level-value">{{player.level}}</span></div>
      </div>
      <div class="player-right" >
        <div v-for="(row,rowIndex) in groupedStatus()" key="rowIndex" class="status-row">
          <StatusBlock v-for="stat in row" key="stat" :title="getStatChinese(stat)"
                       :value="player.status[stat]" :potential="player.potential[stat]"></StatusBlock>
        </div>
      </div>
    </div>
    <button @click="generatePlayer" class="submit-button" >重新創建</button>
    <button @click="levelUp" class="submit-button">提升等級</button>
  </div>
  <Popup :type="popupType" :pop-up-msg="popupMsg" @update:isPopup="popupType=$event"></Popup>
</template>

<style scoped>
.player-list-container {
  width: 900px;
  height: 500px;
  overflow-y:auto;
  color: #9ab793;
}

.player-container {
  border: 1px solid #00b894;
  margin-bottom: 5px;
  display: flex;
  height: auto;
  border-radius: 10px;
}

.level {
  color: #1b6956;
}

.level-value {
  font-size: 1.1em;
  color: #ded07f;
}

.player-left {
  padding: 10px;
  background: #00b894;
  align-content: center;
  width: 150px;
  border-top-left-radius: 10px;
  border-bottom-left-radius: 10px;
}

.player-right {
  display: flex;
  margin-left: 30px;
  flex-direction: row;
  align-items: center;
  padding-top: 5px;
  padding-bottom: 5px;
}

.player-name {
  color: #1f0410;
  font-family: "Microsoft JhengHei UI",monospace;
  font-weight: bolder;
  font-size: 1em;
}


.status-row {
  display: flex;
  flex-direction: column;
  margin-bottom: 0px;
}
.status-row>*{
  margin-right: 10px;
}
</style>