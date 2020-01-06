package cn.lhemi.thjob.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.tio.core.starter.annotation.EnableTioServerServer;

@SpringBootApplication
@EnableTioServerServer
public class ThJobAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(ThJobAdminApplication.class, args);
    }
}
