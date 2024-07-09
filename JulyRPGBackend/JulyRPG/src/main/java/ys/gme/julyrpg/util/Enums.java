package ys.gme.julyrpg.util;

import lombok.Getter;

/**
 * 列舉類別
 * @author yoskir
 */
public class Enums {
    public enum ApiResult{success,fail,password_wrong,user_not_exist}
    public enum Nation {Western, Chinese, Japanese, Mix}

    public enum DataFileTypeEnum {save,name}
    //能力值(潛能、現在) 生命 魔力 力量 技巧 體質 速度 敏捷 幸運 智力
    public enum Status {hp,mp,str,abi,bod,spd,dex,luc,mag};
}
