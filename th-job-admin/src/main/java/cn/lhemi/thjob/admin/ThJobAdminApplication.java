package cn.lhemi.thjob.admin;

import cn.lhemi.jax.JaxServer;
import cn.lhemi.jax.annotation.EnableJaxServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableJaxServer
@SpringBootApplication
public class ThJobAdminApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(ThJobAdminApplication.class, args);
        JaxServer.start();
    }

}
