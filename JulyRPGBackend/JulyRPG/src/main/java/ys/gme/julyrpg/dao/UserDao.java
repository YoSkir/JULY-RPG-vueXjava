package ys.gme.julyrpg.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import ys.gme.julyrpg.entity.collection.User;

/**
 * 帳號資料的資料庫層
 * @author yoskir
 */

public interface UserDao extends MongoRepository<User,String> {
    User findByUsername(String username);
}
