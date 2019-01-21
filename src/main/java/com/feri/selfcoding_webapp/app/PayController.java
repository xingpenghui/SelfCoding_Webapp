package com.feri.selfcoding_webapp.app;

import com.feri.common.vo.ResultVO;
import com.feri.service.pay.PayService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *@Author feri
 *@Date Created in 2019/1/21 16:47
 */
@RestController
public class PayController {
    @Autowired
    private PayService payService;

    //

    @ApiOperation(value = "查询学贝变动记录",notes = "查询用户的学贝变动记录")
    @GetMapping("payshelllist.do")
    public ResultVO queryList(String token){
        return payService.queryShell(token);
    }
}
