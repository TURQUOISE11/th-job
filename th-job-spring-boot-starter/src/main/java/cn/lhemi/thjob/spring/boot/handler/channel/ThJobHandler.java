package cn.lhemi.thjob.spring.boot.handler.channel;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import cn.lhemi.thjob.core.dto.Message;
import cn.lhemi.thjob.core.dto.RegisterDto;
import cn.lhemi.thjob.spring.boot.NettyClient;
import com.alibaba.fastjson.JSON;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;

/**
 * @author tutu11
 * @date 2019-12-27 09:14:10
 */
@Component
@ChannelHandler.Sharable
public class ThJobHandler extends SimpleChannelInboundHandler<ByteBuf> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf byteBuf) throws Exception {
        String s = byteBuf.readCharSequence(byteBuf.readableBytes(), StandardCharsets.UTF_8).toString();
        System.out.println(s);
        NettyClient.channel().writeAndFlush(s + "\n");
    }


    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        RegisterDto registerDto = new RegisterDto();
        registerDto.setAppKey(IdUtil.fastSimpleUUID());
        registerDto.setAppName(IdUtil.fastSimpleUUID());
        registerDto.setClientId(IdUtil.fastSimpleUUID());
        registerDto.setGroup(IdUtil.fastSimpleUUID());
        Message<RegisterDto> msg=new Message<>();
        msg.setType(Message.MsgType.REGISTER);
        msg.setOutId(IdUtil.objectId());
        msg.setData(registerDto);
        msg.setTs(System.currentTimeMillis());
        String s = JSON.toJSONString(msg);
        System.out.println("s:" + s);
        ctx.writeAndFlush(s);
        super.channelActive(ctx);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        super.channelInactive(ctx);
    }
}
