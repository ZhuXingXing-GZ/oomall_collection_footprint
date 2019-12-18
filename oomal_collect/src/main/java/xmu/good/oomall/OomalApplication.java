package xmu.good.oomall;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@MapperScan(basePackages = "xmu.good.oomall.mapper")
@SpringBootApplication
public class OomalApplication {

    public static void main(String[] args) {
        SpringApplication.run(OomalApplication.class, args);
    }

}
