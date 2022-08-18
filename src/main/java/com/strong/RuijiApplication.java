package com.strong;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@Slf4j
@SpringBootApplication
@ServletComponentScan("com/strong/Filter")
public class RuijiApplication {

    public static void main(String[] args) {
        SpringApplication.run(RuijiApplication.class, args);
        log.info("xaingmu");
    }

}
