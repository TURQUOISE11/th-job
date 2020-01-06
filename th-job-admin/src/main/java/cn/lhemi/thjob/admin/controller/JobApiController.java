package cn.lhemi.thjob.admin.controller;

import cn.lhemi.thjob.core.dto.R;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author tutu11
 * @description api
 * @date 2019-12-24 14:22:00
 */
@Controller
@RequestMapping("/api")
public class JobApiController {
    public final static String XXL_RPC_ACCESS_TOKEN = "XXL-RPC-ACCESS-TOKEN";

    @RequestMapping("/registry")
    @ResponseBody
    public R registry(@RequestHeader(name = XXL_RPC_ACCESS_TOKEN, required = false) String accessToken, @RequestBody(required = false) String data) {
        System.out.println("accessToken:" + accessToken);
        System.out.println("data:" + data);
        return new R<>();
    }

    /**
     * registry remove
     *
     * @param data
     * @return
     */
    @RequestMapping("/registryRemove")
    @ResponseBody
    public R registryRemove(@RequestHeader(name = XXL_RPC_ACCESS_TOKEN, required = false) String accessToken, @RequestBody(required = false) String data) {
        System.out.println("accessToken:" + accessToken);
        System.out.println("data:" + data);
        // invoke
        return new R<>();
    }

}
