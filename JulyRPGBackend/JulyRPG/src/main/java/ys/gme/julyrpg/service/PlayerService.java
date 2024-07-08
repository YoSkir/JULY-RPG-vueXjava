package ys.gme.julyrpg.service;

import org.springframework.stereotype.Service;
import ys.gme.julyrpg.dao.PlayerInfoDao;
import ys.gme.julyrpg.dao.PlayerStatusDao;
import ys.gme.julyrpg.entity.Player;
import ys.gme.julyrpg.entity.dto.PlayerInfoDto;

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

    public PlayerService(PlayerInfoDao playerInfoDao, PlayerStatusDao playerStatusDao){
        this.playerStatusDao=playerStatusDao;
        this.playerInfoDao=playerInfoDao;
    }

    /**
     * 查詢選手列表
     * @return 選手列表
     */
    public List<PlayerInfoDto> selectPlayerList() {
    }
}
