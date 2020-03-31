package com.sxf.cps.customer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.util.StringUtils;

@Slf4j
@SpringBootApplication
@EnableTransactionManagement
public class CustomerApp {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(CustomerApp.class, args);
        String[] activeProfiles = context.getEnvironment().getActiveProfiles();
        log.info("=CustomerApp=>ActiveProfiles = {}", StringUtils.arrayToCommaDelimitedString(activeProfiles));
    }
}
