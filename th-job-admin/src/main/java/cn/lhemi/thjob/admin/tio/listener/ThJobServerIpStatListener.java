package cn.lhemi.thjob.admin.tio.listener;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.tio.common.starter.annotation.TioServerIpStatListener;
import org.tio.core.ChannelContext;
import org.tio.core.TioConfig;
import org.tio.core.intf.Packet;
import org.tio.core.stat.IpStat;
import org.tio.core.stat.IpStatListener;

/**
 * @author tutu11
 * @description
 * @date 2019-12-30 09:24:07
 */
@Slf4j
@TioServerIpStatListener
public class ThJobServerIpStatListener implements IpStatListener {
    @Override
    public void onExpired(TioConfig tioConfig, IpStat ipStat) {
//        System.err.println("统计时间段到期后");
    }

    @Override
    public void onAfterConnected(ChannelContext channelContext, boolean isConnected, boolean isReconnect, IpStat ipStat) throws Exception {
        System.err.println("建链后触发");
        log.info("ipStat:{}", JSON.toJSONString(ipStat));
    }

    @Override
    public void onDecodeError(ChannelContext channelContext, IpStat ipStat) {
        System.err.println("解码异常时");
    }

    @Override
    public void onAfterSent(ChannelContext channelContext, Packet packet, boolean isSentSuccess, IpStat ipStat) throws Exception {
//        System.err.println("发送后");
    }

    @Override
    public void onAfterDecoded(ChannelContext channelContext, Packet packet, int packetSize, IpStat ipStat) throws Exception {

    }

    @Override
    public void onAfterReceivedBytes(ChannelContext channelContext, int receivedBytes, IpStat ipStat) throws Exception {

    }

    @Override
    public void onAfterHandled(ChannelContext channelContext, Packet packet, IpStat ipStat, long cost) throws Exception {

    }
}
