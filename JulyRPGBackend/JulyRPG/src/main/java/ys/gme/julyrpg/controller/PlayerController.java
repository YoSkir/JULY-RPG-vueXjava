package ys.gme.julyrpg.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ys.gme.julyrpg.entity.dto.PlayerInfoDto;
import ys.gme.julyrpg.service.PlayerService;

import java.util.List;

/**
 * 資訊的控制層
 * @author yoskir
 */
@RestController
public class PlayerController {
    private final PlayerService playerService;

    public PlayerController(PlayerService playerService){
        this.playerService=playerService;
    }

    /**
     * 查詢玩家列表
     * @return 玩家列表
     */
    @GetMapping("/selectPlayerList")
    public ResponseEntity<List<PlayerInfoDto>> selectPlayerList(){
        List<PlayerInfoDto> playerList=playerService.selectPlayerList();
        return ResponseEntity.ok(playerList);
    }
}
