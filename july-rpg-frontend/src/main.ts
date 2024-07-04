import './assets/main.css'

import { createApp } from 'vue'
import App from './App.vue'
import './assets/global.css';
import router from "@/components/router.ts";

createApp(App)
    .use(router)
    .mount('#app')
