package ys.gme.julyrpg.service;

import org.springframework.stereotype.Service;
import ys.gme.julyrpg.dao.UserDao;
import ys.gme.julyrpg.entity.collection.User;
import ys.gme.julyrpg.entity.dto.LoginDto;
import ys.gme.julyrpg.entity.dto.ResultDto;
import ys.gme.julyrpg.util.Constant;
import ys.gme.julyrpg.util.Enums;

/**
 * 帳號資料的服務層
 * @author yoskir
 */
@Service
public class LoginService {
    private final UserDao userDao;

    public LoginService(UserDao userDao){
        this.userDao=userDao;
    }

    /**
     * 寫入帳號資料
     * @param createRequest 帳號容器
     */
    public ResultDto createUser(LoginDto createRequest){
        User user=new User();
        user.setUsername(createRequest.getUsername());
        user.setPassword(createRequest.getPassword());
        try {
            userDao.save(user);
        }catch (Exception e){
            Constant.DebugLog("創建使用者失敗",e.getMessage());
            return new ResultDto(Enums.ApiResult.fail.getValue(), false);
        }
        return new ResultDto(Enums.ApiResult.success.getValue(), true);
    }

    /**
     * 嘗試登入
     * @param loginRequest 帳號 密碼
     * @return 結果
     */
    public ResultDto login(LoginDto loginRequest) {
        User userData=userDao.findByUsername(loginRequest.getUsername());
        if(userData==null){
            return new ResultDto(Enums.ApiResult.user_not_exist.getValue(), false);
        }
        if(!userData.getPassword().equals(loginRequest.getPassword())){
            return new ResultDto(Enums.ApiResult.password_wrong.getValue(), false);
        }
        return new ResultDto(Enums.ApiResult.success.getValue(), true);
    }
}
