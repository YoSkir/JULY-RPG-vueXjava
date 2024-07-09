package ys.gme.julyrpg.entity.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;
import ys.gme.julyrpg.util.Enums;

/**
 * 後端回覆前端用傳遞容器
 * @author yoskir
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@ToString(callSuper = true)
public class ResultDto {
    public ResultDto(Enums.ApiResult message, Boolean isSuccess){
        this.isSuccess=isSuccess;
        this.message=message;
    }
    @JsonProperty("isSuccess")
    private Boolean isSuccess;
    @JsonProperty("message")
    private Enums.ApiResult message;
}
