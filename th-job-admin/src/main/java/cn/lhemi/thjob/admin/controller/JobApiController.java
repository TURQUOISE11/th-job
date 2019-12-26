package cn.lhemi.thjob.admin.controller;

import cn.lhemi.thjob.admin.dto.R;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

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
    public R<String> registry(@RequestHeader(name = XXL_RPC_ACCESS_TOKEN, required = false) String accessToken, @RequestBody(required = false) String data) {
        System.out.println("accessToken:" + accessToken);
        System.out.println("data:" + data);
        return R.SUCCESS;
    }

    /**
     * registry remove
     *
     * @param data
     * @return
     */
    @RequestMapping("/registryRemove")
    @ResponseBody
    public R<String> registryRemove(@RequestHeader(name = XXL_RPC_ACCESS_TOKEN, required = false) String accessToken, @RequestBody(required = false) String data) {
        System.out.println("accessToken:" + accessToken);
        System.out.println("data:" + data);
        // invoke
        return R.SUCCESS;
    }

}
