package com.hd.imms.common.authorize.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hd.imms.common.authorize.bean.Role;
import com.hd.imms.common.authorize.service.AuthorizeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/auth")
public class AuthorizeController {
    @Autowired
    AuthorizeService authorizeService;
    /**
     * 查询所有角色
     */
    @GetMapping(value = "/roles")
    public JSONObject queryAllRoles( HttpServletRequest request) {
        log.error("queryAllRoles start: ");
        // 系统登录认证
        List<Role> roleList = authorizeService.queryAllRoles();
        JSONArray ret = (JSONArray) JSON.toJSON(roleList);
        JSONObject retJSON = new JSONObject();
        retJSON.put("code", 200);
        retJSON.put("data", ret);
        return retJSON;
    }
}
