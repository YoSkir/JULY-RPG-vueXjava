package ys.gme.julyrpg.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ys.gme.julyrpg.entity.dto.LoginDto;
import ys.gme.julyrpg.entity.dto.ResultDto;
import ys.gme.julyrpg.util.Constant;
import ys.gme.julyrpg.util.Enums;

/**
 * @author yoskir
 */
@RestController
public class LoginController {
    @PostMapping("/login")
    public ResponseEntity<ResultDto> login(@RequestBody LoginDto loginRequest){
        Enums.ApiResult result;
        if(!loginRequest.getUsername().equals("ys")) {
            result= Enums.ApiResult.user_not_exist;
        }else if(!loginRequest.getPassword().equals("0506")){
            result= Enums.ApiResult.password_wrong;
        }else {
            result= Enums.ApiResult.success;
            Constant.DebugLog("登入成功");
        }
        //回傳分別為 結果訊息 是否成功 是否為新的username
        return ResponseEntity.ok(new ResultDto(result.getMessage(),
                result.equals(Enums.ApiResult.success),
                result.equals(Enums.ApiResult.user_not_exist)));
    }
}
