package cn.lhemi.thjob.admin.controller;

import cn.hutool.core.util.IdUtil;
import cn.lhemi.thjob.core.dto.AddDto;
import cn.lhemi.thjob.core.dto.R;
import cn.lhemi.thjob.core.packet.ThJobPacket;
import com.alibaba.fastjson.JSON;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.tio.core.Tio;
import org.tio.core.starter.TioServerBootstrap;
import org.tio.server.ServerTioConfig;

import java.util.HashMap;

/**
 * @author tutu11
 * @description
 * @date 2019-12-24 16:02:32
 */
@RestController
@AllArgsConstructor
public class DemoController {
    private final TioServerBootstrap tioServerBootstrap;

    @GetMapping("/demo/{id}")
    public R demo(@PathVariable String id) {
        ThJobPacket packet = new ThJobPacket();
        ServerTioConfig serverTioConfig = tioServerBootstrap.getServerTioConfig();
        packet.setBody(JSON.toJSONString(new HashMap<String, String>(1) {{
            put(IdUtil.objectId(), IdUtil.fastSimpleUUID());
        }}));
        return new R<>(Tio.bSendToBsId(serverTioConfig, id, packet));
    }
}
