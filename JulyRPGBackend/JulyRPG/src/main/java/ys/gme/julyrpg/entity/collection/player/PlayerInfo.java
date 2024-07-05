package ys.gme.julyrpg.entity.collection.player;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import ys.gme.julyrpg.util.Enums;

/**
 * 選手資訊的容器
 * @author yoskir
 */
@Document(collection = "player_info")
@Data
public class PlayerInfo {
    @Id
    private Integer id;
    //info
    private String name;
    private Enums.Nation nation;
    //potential
    private Integer hpPotential;
    private Integer mpPotential;
    private Integer strPotential;
    private Integer spdPotential;
    private Integer lucPotential;
    private Integer magPotential;

}
