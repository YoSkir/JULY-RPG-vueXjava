package ys.gme.julyrpg.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import ys.gme.julyrpg.entity.collection.player.PlayerInfo;

/**
 * @author yoskir
 */
public interface PlayerInfoDao extends MongoRepository<PlayerInfo,String> {

}
