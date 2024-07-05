package ys.gme.julyrpg.entity.name;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;
import ys.gme.julyrpg.util.Enums;

import java.util.List;

/**
 * @author yoskir
 */
@Data
@ToString(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Nation {
    @JsonProperty(value = "first_names")
    private List<String> firstNames;
    @JsonProperty(value = "last_names")
    private List<String> lastNames;
    @JsonProperty(value = "nation")
    private Enums.Nation nation;
}
