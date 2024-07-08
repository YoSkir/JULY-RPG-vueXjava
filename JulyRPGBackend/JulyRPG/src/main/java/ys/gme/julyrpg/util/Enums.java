package ys.gme.julyrpg.util;

import lombok.Getter;

/**
 * 列舉類別
 * @author yoskir
 */
public class Enums {
    @Getter
    public enum ApiResult{success("成功"),fail("失敗"),password_wrong("密碼錯誤"),user_not_exist("使用者不存在");
        private final String value;
        ApiResult(String value){
            this.value=value;
        }
    }
    @Getter
    public enum Nation {Western("西方"), Chinese("東方"), Japanese("日本"), Mix("混血");
        private final String value;
        Nation(String value){
            this.value=value;
        }
    }

    public enum DataFileTypeEnum {save,name}
    //能力值(潛能、現在) 生命 魔力 力量 技巧 體質 速度 敏捷 幸運 智力
    public enum Status {hp,mp,str,abi,bod,spd,dex,luc,mag};
}
