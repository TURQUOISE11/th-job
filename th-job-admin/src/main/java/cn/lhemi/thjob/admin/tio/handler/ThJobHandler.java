package cn.lhemi.thjob.admin.tio.handler;

import cn.lhemi.thjob.core.dto.Message;
import cn.lhemi.thjob.core.dto.R;
import cn.lhemi.thjob.core.dto.RegisterDto;
import cn.lhemi.thjob.core.packet.PacketUtil;
import cn.lhemi.thjob.core.packet.ThJobPacket;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.tio.common.starter.annotation.TioServerMsgHandler;
import org.tio.core.ChannelContext;
import org.tio.core.Tio;
import org.tio.core.TioConfig;
import org.tio.core.exception.AioDecodeException;
import org.tio.core.intf.Packet;
import org.tio.server.intf.ServerAioHandler;

import java.nio.ByteBuffer;

/**
 * @author tutu11
 * @description 消息处理
 * @date 2019-12-30 09:19:04
 */
@Slf4j
@TioServerMsgHandler
public class ThJobHandler implements ServerAioHandler {

    @Override
    public Packet decode(ByteBuffer buffer, int limit, int position, int readableLength, ChannelContext channelContext) throws AioDecodeException {
        return PacketUtil.decode(buffer, limit, position, readableLength, channelContext);
    }

    @SneakyThrows
    @Override
    public ByteBuffer encode(Packet packet, TioConfig tioConfig, ChannelContext channelContext) {
        return PacketUtil.encode(packet, tioConfig, channelContext);
    }

    @Override
    public void handler(Packet packet, ChannelContext ctx) throws Exception {
        String body = ((ThJobPacket) packet).getBody();
        log.info("接收到:" + body);
        Message<JSONObject> message = JSON.parseObject(body, new TypeReference<Message<JSONObject>>() {
        });
        switch (message.getType()) {
            case REGISTER:
                log.info("\n设备注册:" + body);
                RegisterDto register = JSONObject.toJavaObject(message.getData(), RegisterDto.class);
                log.info("\nclientId:" + register.getClientId());
                Tio.bindBsId(ctx, register.getClientId());
                Tio.bindGroup(ctx, register.getGroup());
                Tio.bindUser(ctx, register.getAppName());
                break;
            case HEART_BEAT:
                log.info("\n" +
                        "************************************************\n" +
                        "接收到心跳:\n" +
                        "    clientId:  {}\n" +
                        "    Group:     {}\n" +
                        "    App name:  {}\n" +
                        "************************************************", ctx.getBsId(), JSON.toJSONString(ctx.getGroups().getObj()), ctx.userid);
                break;
            default:
                break;
        }

        reply(ctx, message.getOutId());
    }

    private static void reply(ChannelContext channelContext, String outId) {
        Message<Object> msg = Message.builder()
                .type(Message.MsgType.REPLY)
                .outId(outId).data(new R<>())
                .ts(System.currentTimeMillis())
                .build();
        Tio.send(channelContext, new ThJobPacket(JSON.toJSONString(msg)));
    }
}
