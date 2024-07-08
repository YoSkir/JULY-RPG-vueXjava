package ys.gme.julyrpg.entity.collection.player;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import ys.gme.julyrpg.util.Enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 選手資訊的容器
 * @author yoskir
 */
@Document(collection = "player_info")
@Getter
public class PlayerInfo {
    public PlayerInfo(String name, Enums.Nation nation){
        for(Enums.Status status:Enums.Status.values()){
            potential.put(status,null);
        }
        this.name=name;
        this.nation=nation;
    }

    @Id @Setter
    private Integer id;
    //info
    private final String name;
    private final Enums.Nation nation;
    //potential
    private final Map<Enums.Status,Integer> potential=new HashMap<>();
}
