package ys.gme.julyrpg.entity.collection;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 使用者帳密資料的資料庫
 * @author yoskir
 */
@Document(collection = "user")
@Data
public class User {
    @Id
    private String id;
    private String username;
    private String password;
}
