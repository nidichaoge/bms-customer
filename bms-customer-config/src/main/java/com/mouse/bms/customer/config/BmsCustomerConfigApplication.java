package com.mouse.bms.customer.config;


import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.Duration;
import java.time.Instant;

@SpringBootApplication(scanBasePackages = {"com.mouse.bms.customer"})
@EnableDubboConfiguration
@MapperScan("com.mouse.bms.customer.dal.dao")
public class BmsCustomerConfigApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(BmsCustomerConfigApplication.class);

    public static void main(String[] args) {
        Instant startTime = Instant.now();

        SpringApplication.run(BmsCustomerConfigApplication.class, args);

        Instant endTime = Instant.now();
        LOGGER.info("启动成功，耗时:" + Duration.between(startTime, endTime).getSeconds() + "S");
    }

}
