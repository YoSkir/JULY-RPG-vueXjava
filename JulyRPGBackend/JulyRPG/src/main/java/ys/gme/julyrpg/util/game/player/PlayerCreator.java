package ys.gme.julyrpg.util.game.player;

import org.springframework.stereotype.Component;
import ys.gme.julyrpg.entity.Player;
import ys.gme.julyrpg.entity.collection.player.PlayerInfo;
import ys.gme.julyrpg.entity.collection.player.PlayerStatus;

/**
 * 創建選手的類
 * @author yoskir
 */
@Component
public class PlayerCreator {
    private final PlayerCreator_info playerCreator_info;
    private final PlayerCreator_status playerCreator_status;

    public PlayerCreator(PlayerCreator_info playerCreator_info,PlayerCreator_status playerCreator_status){
        this.playerCreator_info=playerCreator_info;
        this.playerCreator_status=playerCreator_status;
    }

    /**
     * 創建選手 給予隨機名、國籍、潛力
     * 並初始化數據
     * @return 選手資訊
     */
    public Player createPlayer(){
        Player player=new Player();

        //創建選手資訊
        PlayerInfo playerInfo=playerCreator_info.createInfo();
        player.setInfo(playerInfo);

        //初始化選手數據
        PlayerStatus playerStatus=new PlayerStatus(playerInfo.getId());
        player.setStatus(playerStatus);
        playerCreator_status.initialStatue(player);

        //回傳選手容器
        return player;
    }
}
