package com.hd.imms.common.authorize.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hd.imms.common.authorize.bean.LoginBean;
import com.hd.imms.common.authorize.service.AuthorizeService;
import com.hd.imms.common.security.JwtAuthenticatioToken;
import com.hd.imms.common.utils.HttpResult;
import com.hd.imms.common.utils.JwtTokenUtils;
import com.hd.imms.common.utils.SecurityUtils;
import io.netty.util.internal.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RestController
@Slf4j
public class LoginController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private AuthorizeService authorizeService;

    /**
     * 登录接口
     */
    @PostMapping(value = "/login")
    public HttpResult login(@RequestBody LoginBean loginBean, HttpServletRequest request) throws IOException {
        String username = loginBean.getUsername();
        String password = loginBean.getPassword();
        log.error("login username: "+username+", password: "+password);
        // 系统登录认证
        JwtAuthenticatioToken token = null;
        try{
            token = SecurityUtils.login(request, username, password, authenticationManager);
            HttpResult.ok(token);
            return HttpResult.ok(token);
        }catch (Exception e){
            HttpResult.error(e.getMessage());
            return  HttpResult.error(e.getMessage());
        }
    }
    /**
     * 登录接口
     */
    @GetMapping(value = "/info")
    public HttpResult info(@RequestParam("token") String token, HttpServletRequest request) throws IOException {
        log.error("login info token: "+token);
        // 系统登录认证
        String userId = JwtTokenUtils.getUsernameFromToken(token);
        log.error("login info userId: "+userId);
        if(StringUtils.isEmpty(userId)){
            return HttpResult.error(50008,"非法的token");
        }
        //JSONObject ret = JSON.parseObject("{\"data\":{\"roles\":[\"admin\"],\"introduction\":\"I am a super administrator\",\"avatar\":\"https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif\",\"name\":\"Super Admin\"}}");
        JSONObject ret = authorizeService.queryUserRoleById(userId);
        log.error("login info roles: "+ret.toString());
        return HttpResult.ok(ret);
    }
}
