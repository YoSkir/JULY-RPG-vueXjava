package ys.gme.julyrpg.entity.name;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * 名字的容器
 * @author yoskir
 */
@Data
@ToString(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class NameEntity {
    @JsonProperty(value = "national_name_list")
    private List<Nation> nationalNameList;
}
