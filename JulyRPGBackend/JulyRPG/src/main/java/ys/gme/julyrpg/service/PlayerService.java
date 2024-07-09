package ys.gme.julyrpg.service;

import org.springframework.stereotype.Service;
import ys.gme.julyrpg.dao.PlayerInfoDao;
import ys.gme.julyrpg.dao.PlayerStatusDao;
import ys.gme.julyrpg.entity.Player;
import ys.gme.julyrpg.entity.collection.player.PlayerInfo;
import ys.gme.julyrpg.entity.collection.player.PlayerStatus;
import ys.gme.julyrpg.entity.dto.PlayerInfoDto;
import ys.gme.julyrpg.entity.dto.ResultDto;
import ys.gme.julyrpg.util.Constant;
import ys.gme.julyrpg.util.Enums;
import ys.gme.julyrpg.util.game.LevelManager;
import ys.gme.julyrpg.util.game.player.PlayerCreator;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 管理選手的服務層
 * @author yoskir
 */
@Service
public class PlayerService {
    private final PlayerInfoDao playerInfoDao;
    private final PlayerStatusDao playerStatusDao;

    private final Map<Integer, Player> id_player=new ConcurrentHashMap<>();
    private final PlayerCreator playerCreator;
    private final LevelManager levelManager;

    public PlayerService(PlayerInfoDao playerInfoDao, PlayerStatusDao playerStatusDao, PlayerCreator playerCreator,
                         LevelManager levelManager){
        this.playerStatusDao=playerStatusDao;
        this.levelManager=levelManager;
        this.playerInfoDao=playerInfoDao;
        this.playerCreator=playerCreator;
    }

    /**
     * 查詢選手列表
     * @return 選手列表
     */
    public List<PlayerInfoDto> selectPlayerList() {
        List<PlayerInfoDto> playerList=new LinkedList<>();
        for(Player player:id_player.values()){
            PlayerInfoDto playerDto =new PlayerInfoDto();
            PlayerInfo playerInfo=player.getInfo();
            PlayerStatus playerStatus=player.getStatus();
            playerDto.setName(playerInfo.getName());
            playerDto.setLevel(playerStatus.getLevel());
            playerDto.setRemainHp(playerStatus.getRemainHp());
            playerDto.setRemainMp(playerStatus.getRemainMp());
            playerDto.setStatus(playerStatus.getStatus());

            playerDto.setPotential(playerInfo.getPotential());

            playerList.add(playerDto);
        }
        return playerList;
    }

    /**
     * 創建選手
     * @return 結果
     */
    public ResultDto generatePlayer() {
        id_player.clear();
        for(int i=0;i<10;i++){
            Player player=playerCreator.createPlayer();
            id_player.put(player.getId(),player);
        }
        if(id_player.isEmpty()){
            return new ResultDto(Enums.ApiResult.fail,false);
        }
        return new ResultDto(Enums.ApiResult.success,true);
    }

    /**
     * 升級測試
     * @return 結果
     */
    public ResultDto levelUpTest() {
        int count=0;
        while (count<50){
            count++;
            for(Player player:id_player.values()){
                levelManager.levelUp(player);
            }
        }
        return new ResultDto(Enums.ApiResult.success,true);
    }
}
