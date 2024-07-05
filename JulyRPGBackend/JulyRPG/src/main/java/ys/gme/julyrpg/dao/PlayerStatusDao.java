package ys.gme.julyrpg.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import ys.gme.julyrpg.entity.collection.player.PlayerStatus;

/**
 * @author yoskir
 */
public interface PlayerStatusDao extends MongoRepository<String, PlayerStatus> {
}
