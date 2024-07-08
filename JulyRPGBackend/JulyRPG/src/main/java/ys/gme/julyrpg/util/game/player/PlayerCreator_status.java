package ys.gme.julyrpg.util.game.player;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ys.gme.julyrpg.entity.Player;
import ys.gme.julyrpg.entity.collection.player.PlayerStatus;
import ys.gme.julyrpg.util.Constant;
import ys.gme.julyrpg.util.Enums;
import ys.gme.julyrpg.util.game.LevelManager;

/**
 * 選手能力值的初始化類
 * @author yoskir
 */
@Component
public class PlayerCreator_status {
    private final LevelManager levelManager;

    @Value("${app.constant.potentialWeight}")
    private Integer potentialWeight;

    public PlayerCreator_status(LevelManager levelManager){
        this.levelManager=levelManager;
    }

    public void initialStatue(Player player) {
        PlayerStatus status=player.getStatus();
        status.setExp(0);
        status.setRemainHp(status.getStatus().get(Enums.Status.hp));
        status.setRemainMp(status.getStatus().get(Enums.Status.mp));

        //初始化等級
        status.setLevel(0);
        Integer startLevel= initLevel(player.getInfo().getPotential().get(Enums.Status.luc));
        while (startLevel>0){
            levelManager.levelUp(player);
            startLevel--;
        }
    }

    /**
     * 初始化選手等級(跟運氣潛能有關
     * @param luc 選手幸運潛能
     */
    private Integer initLevel(Integer luc) {
        //依照機率設定初始等級
        //從70%+幸運潛能*權重開始，每級升級機率下降5%，最低5%
        Integer levelUpChance=70+luc* potentialWeight;
        Integer startLevel=1;
        while (levelUpChance> Constant.getRandomInt(0,100)){
            startLevel++;
            //最低機率就是5%
            if(levelUpChance>5){
                levelUpChance= Math.max(levelUpChance - 5, 5);
            }
        }
        return startLevel;
    }
}
