package com.hd.imms.common.authorize.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hd.imms.common.authorize.bean.LoginBean;
import com.hd.imms.common.authorize.bean.Role;
import com.hd.imms.common.authorize.service.AuthorizeService;
import com.hd.imms.common.security.JwtAuthenticatioToken;
import com.hd.imms.common.utils.HttpResult;
import com.hd.imms.common.utils.SecurityUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    /**
     * 创建角色
     */
    @PostMapping(value = "/createRole")
    public JSONObject createRole(@RequestBody Role role, HttpServletRequest request) throws IOException {
        log.debug("createRole roleCode: "+role.getRoleCode()+", roleName: "+role.getRoleName());
        JSONObject ret = new JSONObject();
        ret.put("code", 200);
        ret.put("msg", authorizeService.createRole(role));
        log.error("createRole roleCode end ");
        return ret;
    }
    /**
     * 更新角色
     */
    @PostMapping(value = "/updateRole")
    public JSONObject updateRole(@RequestBody Role role, HttpServletRequest request) throws IOException {
        log.debug("updateRole roleCode: "+role.getRoleCode()+", roleName: "+role.getRoleName());
        JSONObject ret = new JSONObject();
        ret.put("code", 200);
        ret.put("msg", authorizeService.updateRole(role));
        log.error("updateRole roleCode end ");
        return ret;
    }
    /**
     * 删除角色
     */
    @PostMapping(value = "/deleteRole")
    public JSONObject deleteRole(@RequestParam(value="roleCode") String roleCode, HttpServletRequest request) throws IOException {
        log.info("deleteRole roleCode: "+roleCode);
        JSONObject ret = new JSONObject();
        ret.put("code", 200);
        ret.put("msg", authorizeService.deleteRole(roleCode));
        log.info("deleteRole roleCode end ");
        return ret;
    }
}
