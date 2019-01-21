package com.feri.selfcoding_webapp.app;

import com.feri.common.vo.ResultVO;
import com.feri.domain.user.User;
import com.feri.domain.user.Userdetial;
import com.feri.service.user.UserService;
import com.feri.service.user.UserdetailService;
import com.sun.deploy.net.proxy.UserDefinedProxyConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *@Author feri
 *@Date Created in 2019/1/15 14:36
 */
@RestController
public class UserController {
   @Autowired
    private UserService userService;

    @Autowired
    private UserdetailService userdetailService;
    //查询手机号是否可用
    @GetMapping("checkphone.do")
    public ResultVO check(String phone){
        return userService.checkPhone(phone);
    }
    //注册用户
    @PostMapping("usersave.do")
    public ResultVO save(User user){
        return userService.save(user);
    }

    //登录
    @PostMapping("userlogin.do")
    public ResultVO login(String phone,String password){
        return userService.login(phone,password);
    }
    //注销
    @GetMapping("userloginout.do")
    public ResultVO loginout(String token){
        return userService.loginout(token);
    }

    //查询用户详情
    @GetMapping("userdetail.do")
    public ResultVO get(String token){
        return userdetailService.queryById(token);
    }
    @PostMapping("userdetailupdate.do")
    public ResultVO update(String token, Userdetial userdetial){
        return userdetailService.update(token,userdetial);
    }

}

