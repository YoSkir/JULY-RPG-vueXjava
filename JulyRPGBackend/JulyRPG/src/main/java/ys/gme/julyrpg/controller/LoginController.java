package ys.gme.julyrpg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ys.gme.julyrpg.entity.dto.LoginDto;
import ys.gme.julyrpg.entity.dto.ResultDto;
import ys.gme.julyrpg.service.LoginService;
import ys.gme.julyrpg.util.Constant;
import ys.gme.julyrpg.util.game.NameGenerator;

/**
 * @author yoskir
 */
@RestController
public class LoginController {
    private final LoginService loginService;

    public LoginController(LoginService loginService){
        this.loginService=loginService;
    }
    @Autowired
    NameGenerator nameGenerator;
    @PostMapping("/login")
    public ResponseEntity<ResultDto> login(@RequestBody LoginDto loginRequest){
        //回傳分別為 結果訊息 是否成功 是否為新的username
        return ResponseEntity.ok(loginService.login(loginRequest));
    }
    @PostMapping("/createUser")
    public ResponseEntity<ResultDto> createUser(@RequestBody LoginDto createRequest){
        return ResponseEntity.ok(loginService.createUser(createRequest));
    }
}
