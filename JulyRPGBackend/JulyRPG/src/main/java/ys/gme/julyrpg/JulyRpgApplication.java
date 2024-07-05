package ys.gme.julyrpg;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ys.gme.julyrpg.util.Constant;
import ys.gme.julyrpg.util.NameGenerator;

@SpringBootApplication
public class JulyRpgApplication {

    public static void main(String[] args) {
        SpringApplication.run(JulyRpgApplication.class, args);
        Constant.MoreRainBowPrint("------系統已執行------");
    }

}
