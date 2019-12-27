package cn.lhemi.thjob.admin.netty.handler.channel;

import cn.lhemi.jax.channel.JaxChannelInboundHandler;
import com.alibaba.fastjson.JSON;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

/**
 * @description:
 * @author: TURQUOISE
 * @create: 2019-08-06 11:29
 */
@Slf4j
@Component
@ChannelHandler.Sharable
@AllArgsConstructor
public class ThJobHandler extends SimpleChannelInboundHandler<ByteBuf> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {
        String s = msg.readCharSequence(msg.readableBytes(), StandardCharsets.UTF_8).toString();
        System.out.println(s);
        ctx.writeAndFlush(JSON.toJSONString(new HashMap<String, Object>(1) {{
            put("data", System.currentTimeMillis() + ":" + s);
        }}) + "\n");
    }
}
