package ys.gme.julyrpg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ys.gme.julyrpg.util.Constant;

@SpringBootApplication
public class JulyRpgApplication {

    public static void main(String[] args) {
        SpringApplication.run(JulyRpgApplication.class, args);
        Constant.MoreRainBowPrint("------系統已執行------");
    }

}
