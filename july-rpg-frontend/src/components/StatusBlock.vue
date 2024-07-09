<script setup lang="ts">

import {Status} from "@/components/enums.ts";

const props=defineProps({
  title:String as ()=>Status,
  value:Number,
  potential:Number
});

</script>

<template>
  <div class="status-container">
    <div class="status">{{props.title}}: <span class="status-value">{{props.value}}</span></div>
    <div class="status-potential">潛能: {{props.potential}}</div>
  </div>
</template>

<style scoped>
.status-potential {
  font-size: 0.7em;
  color: #c98eac;
  margin-top: -8px;
}
.status-value {
  color: #ded07f;
}

.status {

}

.status-container {
  width: auto;
  margin: 1px;
  background: #415450;
  border-radius: 5px;
  border: 5px solid #415450;
}
</style>