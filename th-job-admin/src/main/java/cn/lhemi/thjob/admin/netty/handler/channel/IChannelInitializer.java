package cn.lhemi.thjob.admin.netty.handler.channel;

import cn.lhemi.jax.channel.JaxChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.json.JsonObjectDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @author: TURQUOISE
 * @create: 2019-08-06 11:30
 */
@Slf4j
@Component
@AllArgsConstructor
public class IChannelInitializer extends JaxChannelInitializer {
    private final ThJobHandler handler;

    @Override
    protected void initChannel(SocketChannel socketChannel) {
        socketChannel.pipeline().addLast(new JsonObjectDecoder()).addLast(new StringEncoder()).addLast(handler);
    }
}
