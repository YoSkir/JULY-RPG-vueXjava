/**
 * 頁面路由管理類
 */
import {createRouter, createWebHistory, Router} from 'vue-router';
import generatedRoutes from 'virtual:generated-pages';
import Login from "@/pages/Login.vue";

export enum Pages{login="/",game="/game"};

const routes=[
    {path:'/',component:Login},
    ...generatedRoutes
];


const router:Router=createRouter({
    history:createWebHistory(),
    routes,
});
export function changePage(pageUrl:Pages){
    router.push(pageUrl).catch(err=>{
        console.error(`${pageUrl} 換頁失敗 :`,err);
    });
}
export default router;