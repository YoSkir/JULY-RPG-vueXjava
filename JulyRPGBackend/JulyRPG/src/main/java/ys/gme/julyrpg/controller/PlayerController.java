package ys.gme.julyrpg.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ys.gme.julyrpg.entity.dto.PlayerInfoDto;
import ys.gme.julyrpg.entity.dto.ResultDto;
import ys.gme.julyrpg.service.PlayerService;
import ys.gme.julyrpg.util.Constant;
import ys.gme.julyrpg.util.Enums;

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

    /**
     * 創建選手
     * @return 結果
     */
    @GetMapping("/generatePlayer")
    public ResponseEntity<ResultDto> generatePlayer(){
        ResultDto result=playerService.generatePlayer();
        return ResponseEntity.ok(result);
    }

    @GetMapping("/levelUp")
    public ResponseEntity<ResultDto> levelUp(){
        ResultDto result=playerService.levelUpTest();
        return ResponseEntity.ok(result);
    }
}
