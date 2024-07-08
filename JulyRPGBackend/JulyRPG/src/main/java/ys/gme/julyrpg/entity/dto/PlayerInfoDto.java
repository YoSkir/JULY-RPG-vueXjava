package ys.gme.julyrpg.entity.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;
import ys.gme.julyrpg.util.Enums;

import java.util.Map;

/**
 * 選手資訊的傳遞容器
 * @author yoskir
 */
@Data
@ToString(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PlayerInfoDto {
    @JsonProperty("name")
    private String name;
    @JsonProperty("nickName")
    private String nickName;
    @JsonProperty("level")
    private Integer level;
    @JsonProperty("levelUpExp")
    private Integer levelUpExp;
    @JsonProperty("exp")
    private Integer exp;
    @JsonProperty("remainHp")
    private Integer remainHp;
    @JsonProperty("remainMp")
    private Integer remainMp;
    @JsonProperty("status")
    private Map<Enums.Status,Integer> status;
}
