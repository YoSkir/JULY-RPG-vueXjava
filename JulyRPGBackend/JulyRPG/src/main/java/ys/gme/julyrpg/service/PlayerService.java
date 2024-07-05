package ys.gme.julyrpg.service;

import org.springframework.stereotype.Service;
import ys.gme.julyrpg.dao.PlayerInfoDao;
import ys.gme.julyrpg.dao.PlayerStatusDao;

/**
 * 管理選手的服務層
 * @author yoskir
 */
@Service
public class PlayerService {
    private final PlayerInfoDao playerInfoDao;
    private final PlayerStatusDao playerStatusDao;

    public PlayerService(PlayerInfoDao playerInfoDao, PlayerStatusDao playerStatusDao){
        this.playerStatusDao=playerStatusDao;
        this.playerInfoDao=playerInfoDao;
    }
}
