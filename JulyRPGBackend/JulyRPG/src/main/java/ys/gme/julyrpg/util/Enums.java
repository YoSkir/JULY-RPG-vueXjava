package ys.gme.julyrpg.util;

import jakarta.annotation.PreDestroy;

/**
 * 列舉類別
 * @author yoskir
 */
public class Enums {
    public enum ApiResult{success("成功"),fail("失敗"),password_wrong("密碼錯誤"),user_not_exist("使用者不存在");
        private final String value;
        ApiResult(String value){
            this.value=value;
        }
        public String getMessage(){
            return value;
        }
    }
}
