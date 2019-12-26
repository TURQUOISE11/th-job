package cn.lhemi.demo.service;

import cn.lhemi.thjob.core.api.DemoService;
import com.xxl.rpc.remoting.provider.annotation.XxlRpcService;
import org.springframework.stereotype.Service;

/**
 * @author tutu11
 * @description
 * @date 2019-12-24 16:00:35
 */
@Service
@XxlRpcService
public class DemoServiceImpl implements DemoService {
    @Override
    public String sayHi(String name) {
        return name + System.currentTimeMillis();
    }
}
