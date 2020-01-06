package cn.lhemi.thjob.spring.boot.handler.channel;

import cn.hutool.core.util.IdUtil;
import cn.lhemi.thjob.core.dto.Message;
import cn.lhemi.thjob.core.packet.PacketUtil;
import cn.lhemi.thjob.core.packet.ThJobPacket;
import com.alibaba.fastjson.JSON;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.tio.client.intf.ClientAioHandler;
import org.tio.core.ChannelContext;
import org.tio.core.TioConfig;
import org.tio.core.exception.AioDecodeException;
import org.tio.core.intf.Packet;

import java.nio.ByteBuffer;
import java.util.HashMap;

/**
 * @author tutu11
 * @description 消息处理
 * @date 2019-12-30 09:19:04
 */
@Slf4j
@Component
public class ThJobHandler implements ClientAioHandler {

    @Override
    public Packet heartbeatPacket(ChannelContext channelContext) {
        long ts = System.currentTimeMillis();
        return new ThJobPacket(JSON.toJSONString(Message.builder()
                .ts(ts)
                .type(Message.MsgType.HEART_BEAT)
                .outId(IdUtil.simpleUUID())
                .data(new HashMap<String,Long>(1){{
                    put("ts",ts);
                }})
                .build()
        ));
    }

    @Override
    public Packet decode(ByteBuffer buffer, int limit, int position, int readableLength, ChannelContext channelContext) throws AioDecodeException {
        return PacketUtil.decode(buffer, limit, position, readableLength, channelContext);
    }

    @SneakyThrows
    @Override
    public ByteBuffer encode(Packet packet, TioConfig tioConfig, ChannelContext channelContext) {
        ThJobPacket jobPacket = (ThJobPacket) packet;
        String s = jobPacket.getBody();
        ByteBuffer buffer = ByteBuffer.allocate(s.length());
        buffer.put(s.getBytes(ThJobPacket.CHARSET));
        return buffer;
    }

    @Override
    public void handler(Packet packet, ChannelContext channelContext) throws Exception {
        System.err.println(((ThJobPacket) packet).getBody());
    }


}
