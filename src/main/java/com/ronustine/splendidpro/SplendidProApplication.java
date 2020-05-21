package com.ronustine.splendidpro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@EnableSwagger2
@MapperScan({"com.ronustine.splendidpro.**.dao", "com.ronustine.splendidpro.**.repository"})
public class SplendidProApplication {

    public static void main(String[] args) {
        SpringApplication.run(SplendidProApplication.class, args);
    }

}
