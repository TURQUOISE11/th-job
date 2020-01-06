package cn.lhemi.thjob.spring.boot.handler.listener;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import cn.lhemi.thjob.core.dto.Message;
import cn.lhemi.thjob.core.dto.RegisterDto;
import cn.lhemi.thjob.core.packet.ThJobPacket;
import cn.lhemi.thjob.spring.boot.autoconfigure.ThJobProperties;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tio.client.intf.ClientAioListener;
import org.tio.core.ChannelContext;
import org.tio.core.Tio;
import org.tio.core.intf.Packet;

/**
 * @author tutu11
 * @description
 * @date 2019-12-30 14:15:58
 */
@Slf4j
@Component
public class ThJobClientAioListener implements ClientAioListener {
    @Autowired
    private ThJobProperties thJobProperties;

    @Override
    public void onAfterConnected(ChannelContext channelContext, boolean isConnected, boolean isReconnect) throws Exception {
        if (isConnected) {
            String clientId = StrUtil.isBlank(thJobProperties.getClientId()) ? IdUtil.simpleUUID() : thJobProperties.getClientId();
            log.info("\n****************** Register client *******************\n" +
                            "    Client id:  {}\n" +
                            "    Group    :  {}\n" +
                            "    App name :  {}\n" +
                            "******************************************************"
                    , clientId,thJobProperties.getGroup(),thJobProperties.getAppName());
            RegisterDto registerDto = new RegisterDto()
                    .setClientId(clientId)
                    .setGroup(thJobProperties.getGroup())
                    .setAppName(thJobProperties.getAppName())
                    .setAppKey(thJobProperties.getAppKey())
                    .setSecretKey(thJobProperties.getSecretKey())
                    .setGroup(IdUtil.objectId());
            ThJobPacket registerPacket = new ThJobPacket(JSON.toJSONString(Message.builder()
                    .type(Message.MsgType.REGISTER)
                    .ts(System.currentTimeMillis())
                    .outId(IdUtil.simpleUUID())
                    .data(registerDto)
                    .build()));
            Tio.send(channelContext, registerPacket);
        }

    }

    @Override
    public void onAfterDecoded(ChannelContext channelContext, Packet packet, int packetSize) throws Exception {

    }

    @Override
    public void onAfterReceivedBytes(ChannelContext channelContext, int receivedBytes) throws Exception {

    }

    @Override
    public void onAfterSent(ChannelContext channelContext, Packet packet, boolean isSentSuccess) throws Exception {

    }

    @Override
    public void onAfterHandled(ChannelContext channelContext, Packet packet, long cost) throws Exception {

    }

    @Override
    public void onBeforeClose(ChannelContext channelContext, Throwable throwable, String remark, boolean isRemove) throws Exception {

    }

    public static void main(String[] args) {
        System.out.println("****************** Register client *******************");
        System.out.println("    Client id:  " + IdUtil.fastSimpleUUID());
        System.out.println("    Group    :  " + IdUtil.fastSimpleUUID());
        System.out.println("    App name :  " + IdUtil.fastSimpleUUID());
        System.out.println("******************************************************");

    }
}
