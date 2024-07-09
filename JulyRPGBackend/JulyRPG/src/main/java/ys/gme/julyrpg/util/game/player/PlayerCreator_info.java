package ys.gme.julyrpg.util.game.player;

import org.springframework.stereotype.Component;
import ys.gme.julyrpg.entity.collection.player.PlayerInfo;
import ys.gme.julyrpg.util.Constant;
import ys.gme.julyrpg.util.Enums;
import ys.gme.julyrpg.util.game.NameGenerator;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 選手創造 負責資訊的類
 * @author yoskir
 */
@Component
public class PlayerCreator_info {
    private final NameGenerator nameGenerator;

    private AtomicInteger id=new AtomicInteger(0);
    public PlayerCreator_info(NameGenerator nameGenerator){
        this.nameGenerator=nameGenerator;
    }
    public PlayerInfo createInfo() {
        //創建隨機國籍 名字
        Enums.Nation nation=nameGenerator.randomNation();
        String name=nameGenerator.getRandomName(nation);
        PlayerInfo playerInfo=new PlayerInfo(name,nation);
        //初始化隨機潛能
        setPotential(playerInfo);
        playerInfo.setId(id.incrementAndGet());
        return playerInfo;
    }

    /**
     * 設定選手的潛能
     * @param playerInfo 選手資訊
     */
    private void setPotential(PlayerInfo playerInfo) {
        specialNameAward(playerInfo);
        Map<Enums.Status, Integer> potential=playerInfo.getPotential();
        for(Enums.Status status:potential.keySet()){
            if(potential.get(status)==null){
                potential.put(status,randomPotential());
            }
        }
    }
    /**
     * 若傳入為null(檢查是否有特殊獎勵)則回傳隨機0~10潛能值
     * @return 潛能值
     */
    private Integer randomPotential(){
        //10 1%
        //9 2%
        //8 4%
        //7 8%
        //6 16%
        //3~5 25%
        //0~2 44%
        int potentialLottery=Constant.getRandomInt(0,100);
        if(potentialLottery==100){
            return 10;
        }
        if(potentialLottery>97){
            return 9;
        }
        if(potentialLottery>93){
            return 8;
        }
        if(potentialLottery>85){
            return 7;
        }
        if(potentialLottery>69){
            return 6;
        }
        if(potentialLottery>44){
            return Constant.getRandomInt(3,5);
        }
        return Constant.getRandomInt(0,2);
    }

    /**
     * 特殊名稱獎勵
     * @param playerInfo 傳入資訊容器
     */
    private void specialNameAward(PlayerInfo playerInfo){
        //特殊名稱獎勵
        Map<Enums.Status, Integer> potential=playerInfo.getPotential();
        switch (playerInfo.getName()){
            case "宮城良田":
                potential.put(Enums.Status.dex,15);
                break;
            case "犬與 羅霸特":
                potential.put(Enums.Status.hp,15);
                break;
            case "厄林．哈蘭德":
                potential.put(Enums.Status.str,13);
                potential.put(Enums.Status.spd,13);
                potential.put(Enums.Status.bod,13);
                break;
            case "基利安．姆巴佩":
                potential.put(Enums.Status.spd,15);
                break;
            case "里奧．梅西":
                potential.put(Enums.Status.abi,15);
                break;
            case "三笘薰":
                potential.put(Enums.Status.mag,15);
                break;
            case "伍佑群","吳玟頤":
                potential.put(Enums.Status.luc,30);
                break;
        }
    }
}
