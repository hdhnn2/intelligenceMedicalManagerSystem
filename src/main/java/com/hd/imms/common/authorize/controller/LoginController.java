package com.hd.imms.common.authorize.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hd.imms.common.authorize.bean.LoginBean;
import com.hd.imms.common.security.JwtAuthenticatioToken;
import com.hd.imms.common.utils.HttpResult;
import com.hd.imms.common.utils.SecurityUtils;
import lombok.extern.slf4j.Slf4j;
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

    /**
     * 登录接口
     */
    @PostMapping(value = "/login")
    public HttpResult login(@RequestBody LoginBean loginBean, HttpServletRequest request) throws IOException {
        String username = loginBean.getUsername();
        String password = loginBean.getPassword();
        log.error("login username: "+username+", password: "+password);
        // 系统登录认证
        JwtAuthenticatioToken token = SecurityUtils.login(request, username, password, authenticationManager);

        log.error("login token: "+token.toString());
        return HttpResult.ok(token);
    }
    /**
     * 登录接口
     */
    @GetMapping(value = "/info")
    public JSONObject info(@RequestParam("token") String token, HttpServletRequest request) throws IOException {
        log.error("login info token: "+token);
        // 系统登录认证
        JSONObject ret = JSON.parseObject("{\"code\":200,\"data\":{\"roles\":[\"admin\"],\"introduction\":\"I am a super administrator\",\"avatar\":\"https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif\",\"name\":\"Super Admin\"}}");
        log.error("login token: "+ret.toString());
        return ret;
    }
}
