package cn.lhemi.thjob.spring.boot.autoconfigure;

import cn.lhemi.thjob.spring.boot.NettyClient;
import cn.lhemi.thjob.spring.boot.handler.channel.ThJobHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.json.JsonObjectDecoder;
import io.netty.handler.codec.string.StringEncoder;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.SmartLifecycle;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author tutu11
 * @date 2019-12-27 08:58:23
 */
@Configuration
@Import(ThJobHandler.class)
@EnableConfigurationProperties(ThJobProperties.class)
public class ThJobAutoConfiguration implements SmartLifecycle {
    private static final Logger logger = LoggerFactory.getLogger(ThJobAutoConfiguration.class);
    private boolean isRunning = false;
    @Autowired
    private ThJobProperties thJobProperties;
    @Autowired
    private EventLoopGroup group;
    @Autowired
    private ThJobHandler handler;

    @Bean
    public ThJobProperties thJobProperties() {
        return new ThJobProperties();
    }

    public Bootstrap bootstrap() {
        Bootstrap b = new Bootstrap();
        /*使用NioSocketChannel来作为连接用的channel类*/
        b.group(group).channel(NioSocketChannel.class)
                /*绑定连接初始化器*/
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    public void initChannel(SocketChannel ch) {
                        ChannelPipeline pipeline = ch.pipeline();
                        pipeline.addLast(new StringEncoder());
                        pipeline.addLast(new JsonObjectDecoder());
                        pipeline.addLast(handler);
                    }
                });
        return b;
    }

    @Bean(name = "group", destroyMethod = "shutdownGracefully")
    public EventLoopGroup group() {
        return new NioEventLoopGroup();
    }

    @SneakyThrows
    @Override
    public void start() {
        System.err.println("连接服务器中···");
        isRunning = true;
        NettyClient.init(thJobProperties.getHost(), thJobProperties.getPort(), handler).start();
    }

    @SneakyThrows
    @Override
    public void stop() {
        System.err.println("stop");

    }

    @Override
    public boolean isRunning() {
        return isRunning && NettyClient.isRunning();
    }
}
