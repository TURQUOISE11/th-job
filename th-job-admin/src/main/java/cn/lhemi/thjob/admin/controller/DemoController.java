package cn.lhemi.thjob.admin.controller;

import cn.lhemi.thjob.admin.dto.R;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author tutu11
 * @description
 * @date 2019-12-24 16:02:32
 */
@RestController
public class DemoController {

    @GetMapping("/demo")
    public R demo() {
        return  R.SUCCESS;
    }
}
