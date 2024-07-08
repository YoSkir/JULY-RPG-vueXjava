package ys.gme.julyrpg.util.game;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ys.gme.julyrpg.entity.Player;
import ys.gme.julyrpg.entity.collection.player.PlayerInfo;
import ys.gme.julyrpg.entity.collection.player.PlayerStatus;
import ys.gme.julyrpg.util.Constant;
import ys.gme.julyrpg.util.Enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 等級的管理類
 * @author yoskir
 */
@Component
public class LevelManager {
    private final Map<Integer,Integer> levelUpExpNeededMap=new HashMap<>();
    @Value("${app.constant.potentialWeight}")
    private Integer potentialWeight;

    public void levelUp(Player player){
        PlayerStatus playerStatus=player.getStatus();
        PlayerInfo playerInfo=player.getInfo();

        //提升等級
        playerStatus.setLevel(playerStatus.getLevel()+1);
        //能力值處理
        Map<Enums.Status, Integer> statusMap=playerStatus.getStatus();
        Integer statusUp;
        Map<Enums.Status, Integer> potential=playerInfo.getPotential();
        //遍歷能力值
        for(Enums.Status status:Enums.Status.values()){
            //計算並加上升級獲得能力值
            statusUp=getStatusUp(potential.get(status));
            statusMap.put(status,statusMap.get(status)+statusUp);
        }
    }

    /**
     * 獲得上升的能力值量
     * @param potential 潛能
     * @return 上升量
     */
    private Integer getStatusUp(Integer potential) {
        //從50%+潛能*潛能權重的機率開始 每提升一次-10% 最低10%
        Integer upRate=50+potential*potentialWeight;
        Integer statusUpped=0;
        while (upRate> Constant.getRandomInt(0,100)){
            statusUpped++;
            //最低10%
            if(upRate>10){
                upRate=Math.max(upRate-10,10);
            }
        }
        return statusUpped;
    }

    /**
     * 檢查當前經驗值可升幾級
     * @param playerStatus 角色數值
     * @return 可升幾級
     */
    public Integer checkExp(PlayerStatus playerStatus){
        Integer levelsUpped=0;
        Integer levelUpExpNeeded;
        Integer playerExp= playerStatus.getExp();
        while (true){
            //獲得現在等級所需exp
            Integer currentLevel=playerStatus.getLevel()+levelsUpped;
            //從緩存獲取資料，如無資料則計算並寫入緩存
            levelUpExpNeeded=levelUpExpNeededMap.computeIfAbsent(currentLevel,this::calculateExpNeeded);
            //判斷是否升級
            if(playerExp>=levelUpExpNeeded){
                //升級 exp回歸
                levelsUpped++;
                playerExp-=levelUpExpNeeded;
            }else {
                //沒升級 設定角色數值exp 回傳升了多少級
                playerStatus.setExp(playerExp);
                return levelsUpped;
            }
        }
    }

    /**
     * 計算升級所需exp
     * @param currentLevel 現在等級
     * @return 所需exp
     */
    private Integer calculateExpNeeded(Integer currentLevel) {
        return currentLevel*10;
    }
}
