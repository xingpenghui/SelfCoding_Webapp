package com.feri.selfcoding_webapp.app;

import com.feri.common.vo.ResultVO;
import com.feri.service.user.UserSigninService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *@Author feri
 *@Date Created in 2019/1/16 15:47
 */
@RestController
public class UserSigninController {
    @Autowired
    private UserSigninService userSigninService;

    //查询签到记录
    @GetMapping("usersigninlist.do")
    public ResultVO list(String token,int count){
        return userSigninService.queryList(token,count);
    }
    //查询是否可以签到
    @GetMapping("usersignincheck.do")
    public ResultVO check(String token){
        return userSigninService.checkSign(token);
    }
    //签到
    @GetMapping("usersignincheck.do")
    public ResultVO sign(String token){
        return userSigninService.signin(token);
    }
}
