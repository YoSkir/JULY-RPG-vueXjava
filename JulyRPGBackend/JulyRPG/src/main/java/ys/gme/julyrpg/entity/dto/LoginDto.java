package ys.gme.julyrpg.entity.dto;

import lombok.Data;

/**
 * 登入的傳遞容器
 * @author yoskir
 */
@Data
public class LoginDto {
    private String username;
    private String password;
}
