package pri.zhy.gmc.web;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("pri.zhy.gmc.web.mapper")
public class GoodManCardWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(GoodManCardWebApplication.class, args);
    }

}
