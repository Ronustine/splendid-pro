package com.ronustine.splendidpro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan("com.ronustine.splendidpro.**.dao")
public class SplendidProApplication {

    public static void main(String[] args) {
        SpringApplication.run(SplendidProApplication.class, args);
    }

}
