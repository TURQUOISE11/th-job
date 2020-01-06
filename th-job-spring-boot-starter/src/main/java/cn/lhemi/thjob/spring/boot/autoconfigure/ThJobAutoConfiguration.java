package cn.lhemi.thjob.spring.boot.autoconfigure;

import cn.lhemi.thjob.spring.boot.ThJobTioClient;
import cn.lhemi.thjob.spring.boot.handler.channel.ThJobHandler;
import cn.lhemi.thjob.spring.boot.handler.listener.ThJobClientAioListener;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.SmartLifecycle;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import javax.annotation.Resource;

/**
 * @author tutu11
 * @date 2019-12-27 08:58:23
 */
@Configuration
@Import({ThJobHandler.class, ThJobClientAioListener.class})
@EnableConfigurationProperties(ThJobProperties.class)
public class ThJobAutoConfiguration implements SmartLifecycle {
    private boolean isRunning = false;
    @Autowired
    private ThJobProperties thJobProperties;
    @Autowired
    private ThJobHandler thJobHandler;
    @Autowired
    private ThJobClientAioListener thJobClientAioListener;


    @Bean
    public ThJobProperties thJobProperties() {
        return new ThJobProperties();
    }


    @Bean(name = "group", destroyMethod = "shutdownGracefully")
    public EventLoopGroup group() {
        return new NioEventLoopGroup();
    }

    @SneakyThrows
    @Override
    public void start() {
        System.err.println("连接服务器中···");
        ThJobTioClient client = ThJobTioClient.newInstant(
                thJobProperties.getHost(),
                thJobProperties.getPort(),
                thJobProperties.getTimeOut(),
                thJobHandler,
                thJobClientAioListener
        );
        client.start();
        isRunning = true;
    }

    @SneakyThrows
    @Override
    public void stop() {
        System.err.println("stop");
    }

    @Override
    public boolean isRunning() {
        return isRunning;
    }
}
