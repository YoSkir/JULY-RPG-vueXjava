package ys.gme.julyrpg.entity.collection.player;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 選手能力值的容器
 * @author yoskir
 */
@Data
@Document(collection = "player_status")
public class PlayerStatus {
    @Id
    private Integer id;
    private Integer level;
    private Integer exp;
    private Integer maxHp;
    private Integer Hp;
    private Integer maxMp;
    private Integer mp;
    private Integer str;
    private Integer spd;
    private Integer luc;
    private Integer mag;
}
