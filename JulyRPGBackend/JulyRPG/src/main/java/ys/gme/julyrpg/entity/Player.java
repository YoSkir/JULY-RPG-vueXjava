package ys.gme.julyrpg.entity;

import lombok.Data;
import ys.gme.julyrpg.entity.collection.player.PlayerInfo;
import ys.gme.julyrpg.entity.collection.player.PlayerStatus;

/**
 * 選手的容器
 * @author yoskir
 */
@Data
public class Player {
    private PlayerInfo info;
    private PlayerStatus status;
    private Integer id;
}
