package exampleboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(value=("exampleboot.mapper"))
public class BootexampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(BootexampleApplication.class, args);
    }
    //application
}
