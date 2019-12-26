package cn.lhemi.demo.netty;

import cn.hutool.core.date.DateUtil;
import cn.lhemi.demo.netty.handler.ClientHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

@Configuration
@Order
public class InitData implements ApplicationRunner {
    @Autowired
    private ClientHandler clientHandler;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        NettyClient.init("127.0.0.1", 4567, clientHandler).startSys();

    }
}
