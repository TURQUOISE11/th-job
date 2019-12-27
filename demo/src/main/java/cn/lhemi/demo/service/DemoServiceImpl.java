//package cn.lhemi.demo.service;
//
//import cn.lhemi.demo.netty.NettyClient;
//import cn.lhemi.thjob.core.api.DemoService;
//import org.springframework.stereotype.Service;
//
///**
// * @author tutu11
// * @description
// * @date 2019-12-24 16:00:35
// */
//@Service
//public class DemoServiceImpl implements DemoService {
//    @Override
//    public String sayHi(String name) {
//        System.out.println("sayHi:" + name);
//        NettyClient.channel().writeAndFlush(name + System.currentTimeMillis());
//        return name + System.currentTimeMillis();
//    }
//}
