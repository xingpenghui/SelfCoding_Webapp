package com.feri.selfcoding_webapp.config;

import com.alibaba.fastjson.JSON;
import com.feri.common.constant.SystemConst;
import com.feri.common.util.JedisUtil;
import com.feri.common.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 *@Author feri
 *@Date Created in 2019/1/16 15:50
 */

public class TokenFilter implements Filter {

    @Autowired
    private JedisUtil jedisUtil;
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request=(HttpServletRequest)servletRequest;
        String token=request.getParameter("token");
        if(token!=null && token.length()>0){
            if(jedisUtil.checkFiled(SystemConst.TOKENMAP,"user:"+token)){
                filterChain.doFilter(servletRequest,servletResponse);
            }else{
                servletResponse.getWriter().write(JSON.toJSONString(ResultUtil.execERROR()));
            }
        }
        filterChain.doFilter(servletRequest,servletResponse);
    }
}
