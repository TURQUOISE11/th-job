package cn.lhemi.demo.netty;

import cn.hutool.core.date.DateUtil;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.json.JsonObjectDecoder;
import io.netty.handler.codec.string.StringEncoder;

public class NettyClient {
    private final String host;
    private final int port;
    private final ChannelHandlerAdapter clientHandler;
    private static Channel channel;

    public static Channel channel() {
        return channel;
    }

    /*连接服务端的端口号地址和端口号*/
    public NettyClient(String host, int port, ChannelHandlerAdapter clientHandler) {
        this.host = host;
        this.port = port;
        this.clientHandler = clientHandler;
    }

    public static NettyClient init(String host, int port, ChannelHandlerAdapter clientHandler) {
        return new NettyClient(host, port, clientHandler);
    }

    public void startSys() {
        new Thread(() -> {
            try {
                start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).run();
    }

    public void start() throws Exception {
        final EventLoopGroup group = new NioEventLoopGroup();
        Bootstrap b = new Bootstrap();
        /*使用NioSocketChannel来作为连接用的channel类*/
        b.group(group).channel(NioSocketChannel.class)
                /*绑定连接初始化器*/
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    public void initChannel(SocketChannel ch) {
//                        System.out.println("正在连接中...");
                        ChannelPipeline pipeline = ch.pipeline();
                        pipeline.addLast(new StringEncoder());
                        pipeline.addLast(new JsonObjectDecoder());
                        pipeline.addLast(clientHandler);
                    }
                });
        //发起异步连接请求，绑定连接端口和host信息
        try {
            final ChannelFuture future = b.connect(host, port).sync();
            future.addListener((ChannelFutureListener) channelFuture -> {
                if (future.isSuccess()) {
                    System.out.println("连接服务器成功");
                    if (null != channel) {
                        System.err.println("删除虚悬连接");
                        channel.close();
                    }
                    channel = future.channel();
                    channel.writeAndFlush(DateUtil.now());
                } else {
                    System.out.println("连接服务器失败");
                    future.cause().printStackTrace();
                    tryAgain();
                    //关闭线程组
//                    group.shutdownGracefully();
                }
            });
        } catch (Exception e) {
            tryAgain();
            e.printStackTrace();
        }
    }

    private void tryAgain() {
        System.err.println("重连");
        /*3秒重试*/
        try {
            System.out.println("延时三秒重新连接");
            Thread.sleep(3000);
            start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
