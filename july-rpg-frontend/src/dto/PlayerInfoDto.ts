import {Status} from "@/components/enums.ts";

export interface PlayerInfoDto{
    name:string,
    nickName:string,
    level:number,
    levelUpExp:number,
    exp:number,
    remainHp:number,
    remainMp:number,
    status:{[key in Status]:number},
    potential:{[key in Status]:number}
}