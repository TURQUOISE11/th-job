package cn.lhemi.demo.netty.handler;

import cn.hutool.core.date.DateUtil;
import cn.lhemi.demo.netty.NettyClient;
import cn.lhemi.demo.service.DemoServiceImpl;
import cn.lhemi.thjob.core.api.DemoService;
import com.alibaba.fastjson.JSONObject;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.nio.charset.Charset;

@Component
@ChannelHandler.Sharable
public class ClientHandler extends SimpleChannelInboundHandler<ByteBuf> {
    @Autowired
    private DemoService demoService;
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf) throws Exception {
        String s = byteBuf.readCharSequence(byteBuf.readableBytes(), Charset.forName("UTF-8")).toString();
        System.out.println(s);
        JSONObject jsonObject = JSONObject.parseObject(s);
        demoService.sayHi(s);
        System.out.println(jsonObject.toJSONString());
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.err.println("连接断开，自动重连");
//        Thread.sleep(100);
        System.err.println("重连中···");
        NettyClient.init("127.0.0.1", 4567, this).startSys();
        if (null != NettyClient.channel()) {
            NettyClient.channel().writeAndFlush(DateUtil.now());
        }
//        super.channelInactive(ctx);
    }
}
