package com.hd.imms.common.authorize.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hd.imms.common.authorize.bean.Role;
import com.hd.imms.common.authorize.dao.AuthorizeDao;
import com.hd.imms.entity.authorize.Menu;
import com.hd.imms.entity.authorize.User;
import com.hd.imms.entity.authorize.UserRole;
import com.hd.imms.entity.performance.BillDetail;
import com.hd.imms.entity.performance.ScoreDetail;
import com.hd.imms.entity.performance.UserQuery;
import com.hd.imms.mapper.AuthUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class AuthorizeService {
    @Autowired
    AuthorizeDao authorizeDao;

    @Autowired
    private MenuService menuService;

    @Resource
    @Autowired
    AuthUser authUser;

    public List<Role> queryAllRoles(){
        List<Role> roleList =authorizeDao.queryAllRole();
        return roleList;
    }

    /**
     * 创建角色
     * @return
     */
    public String createRole(Role role){
        String msg =authorizeDao.createRole(role);
        return msg;
    }

    /**
     * 更新角色
     * @return
     */
    public String updateRole(Role role){
        String msg =authorizeDao.updateRole(role);
        return msg;
    }

    /**
     * 删除角色
     * @return
     */
    public String deleteRole(String roleCode){
        return authorizeDao.deleteRole(roleCode);
    }

    /**
     * 查询用户
     * @return
     */
    public IPage<User> queryUser(UserQuery params){
        Page<User> page = new Page<>(params.getCurrent(), params.getSize());
        IPage<User> resutlPage =authUser.queryUserListByPage(page, params.getUserName());
        return page;
    }
    /**
     * 查询用户角色
     * @return
     */
    public JSONObject queryUserRoleById(String userId){
        JSONObject ret = new JSONObject();
        JSONArray arr = new JSONArray();
        try{
            List<UserRole> userRoleList = authUser.queryUserRoleById(userId);
            if(userRoleList != null){
                for(UserRole userRole : userRoleList){
                    arr.add(userRole.getRoleId());
                }
            }
        } catch (Exception e){
            log.error("queryUser err: "+e.getMessage());
        }
        ret.put("roles",arr);
        return ret;
    }
    /**
     * 查询用户角色
     * @return
     */
    public JSONArray queryMenuByRole(String roleId){
        JSONArray arr = menuService.queryMenuByRole(roleId);
        return arr;
    }
}
