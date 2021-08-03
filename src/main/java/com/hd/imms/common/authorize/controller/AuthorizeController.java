package com.hd.imms.common.authorize.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hd.imms.common.authorize.bean.Role;
import com.hd.imms.common.authorize.service.AuthorizeService;
import com.hd.imms.entity.authorize.Menu;
import com.hd.imms.entity.authorize.QueryBean;
import com.hd.imms.entity.authorize.User;
import com.hd.imms.entity.authorize.UserRole;
import com.hd.imms.entity.performance.BillDetailQuery;
import com.hd.imms.entity.performance.Discharge;
import com.hd.imms.entity.performance.UserQuery;
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
    /**
     * 查询用户
     */
    @PostMapping(value = "/queryUser")
    public JSONObject queryUser( @RequestBody UserQuery obj, HttpServletRequest request) {
        log.error("queryUser start: ");
        IPage<User> page = authorizeService.queryUser(obj);
        JSONObject retJSON = new JSONObject();
        retJSON.put("code", 200);
        retJSON.put("data", page);
        return retJSON;
    }
    /**
     * 查询菜单
     */
    @GetMapping(value = "/queryMenuTree")
    public JSONObject queryMenuTree(HttpServletRequest request) {
        log.error("queryMenu start: ");
        JSONObject retJSON = new JSONObject();
        retJSON.put("code", 200);
        retJSON.put("data", authorizeService.queryMenuTree());
        return retJSON;
    }
    /**
     * 查询角色菜单
     */
    @PostMapping(value = "/queryRoleLeafMenu")
    public JSONObject queryRoleLeafMenu(@RequestBody QueryBean obj, HttpServletRequest request) {
        log.error("queryRoleLeafMenu start: "+obj.toString());
        List<Menu> list = authorizeService.queryRoleLeafMenu(obj);
        JSONObject retJSON = new JSONObject();
        retJSON.put("code", 200);
        retJSON.put("data", list);
        return retJSON;
    }
    /**
     * 更新角色菜单
     */
    @PostMapping(value = "/updateRoleMenus")
    public JSONObject updateRoleMenus(@RequestBody QueryBean bean, HttpServletRequest request) throws IOException {
        log.debug("updateRoleMenus QueryBean: "+ bean.toString());
        JSONObject ret = new JSONObject();
        ret.put("code", 200);
        ret.put("data", authorizeService.updateRoleMenus(bean));
        log.error("updateRoleMenus end ");
        return ret;
    }
    /**
     * 更新用户角色
     */
    @PostMapping(value = "/updateUserRole")
    public JSONObject updateUserRole(@RequestBody QueryBean bean, HttpServletRequest request) throws IOException {
        log.debug("updateUserRole QueryBean: "+ bean.toString());
        JSONObject ret = new JSONObject();
        ret.put("code", 200);
        ret.put("data", authorizeService.updateUserRole(bean));
        log.error("updateUserRole end ");
        return ret;
    }
    /**
     * 查询角色菜单
     */
    @PostMapping(value = "/queryUserRoleByID")
    public JSONObject queryUserRoleByID(@RequestBody QueryBean obj, HttpServletRequest request) {
        log.error("queryUserRoleByID start: "+obj.toString());
        UserRole userRole = authorizeService.queryUserRoleByID(obj);
        JSONObject retJSON = new JSONObject();
        retJSON.put("code", 200);
        retJSON.put("data", userRole);
        return retJSON;
    }
}
