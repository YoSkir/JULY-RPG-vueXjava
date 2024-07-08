package ys.gme.julyrpg.entity.collection.player;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import ys.gme.julyrpg.util.Enums;

import java.util.Map;

/**
 * 選手能力值的容器
 * @author yoskir
 */
@Data
@Document(collection = "player_status")
public class PlayerStatus {
    public PlayerStatus(Integer id){
        this.id=id;
        for(Enums.Status s:Enums.Status.values()){
            if(s== Enums.Status.hp){
                status.put(s,5);
            }else {
                status.put(s,0);
            }
        }
    }

    @Id
    private Integer id;
    private Map<Enums.Status,Integer> status;
    private String nickName;
    private Integer level;
    private Integer exp;
    private Integer remainHp;
    private Integer remainMp;
}
