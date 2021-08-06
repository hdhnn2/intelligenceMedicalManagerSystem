package com.hd.imms.common.authorize.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hd.imms.common.authorize.bean.Role;
import com.hd.imms.common.authorize.dao.AuthorizeDao;
import com.hd.imms.common.utils.RedisUtils;
import com.hd.imms.entity.authorize.Menu;
import com.hd.imms.entity.authorize.QueryBean;
import com.hd.imms.entity.authorize.User;
import com.hd.imms.entity.authorize.UserRole;
import com.hd.imms.entity.performance.BillDetail;
import com.hd.imms.entity.performance.DeptVsClinic;
import com.hd.imms.entity.performance.ScoreDetail;
import com.hd.imms.entity.performance.UserQuery;
import com.hd.imms.mapper.AuthUser;
import com.hd.imms.performance.service.PerformanceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Autowired
    private PerformanceService performanceService;

    @Autowired
    private RedisUtils redisUtils;

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
        IPage<User> resutlPage =authUser.queryUserListByPage(page, params.getUserName(), params.getSex());
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
            QueryBean obj = new QueryBean();
            obj.setUserId(userId);
            List<UserRole> userRoleList = authUser.queryUserRoleById(obj);
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
     * 查询角色菜单
     * @return
     */
    public JSONArray queryMenuByRole(String roleId){
        JSONArray arr = menuService.queryMenuByRole(roleId);
        return arr;
    }
    /**
     * 查询菜单树
     * @date 2021-07-22
     */
    public JSONArray queryMenuTree(){
        JSONArray arr = menuService.queryMenuTree();
        return arr;
    }
    /**
     * 查询菜单树
     * @date 2021-07-22
     */
    public List<Menu> queryRoleLeafMenu(QueryBean obj){
        List<Menu> list = authUser.queryRoleLeafMenu(obj);
        return list;
    }
    /**
     * 更新角色菜单
     * @return
     */
    public String updateRoleMenus(QueryBean bean){
        String msg =authorizeDao.updateRoleMenus(bean);
        return msg;
    }
    /**
     * 更新用户角色
     * @return
     */
    public String updateUserRole(QueryBean bean){
        String msg =authorizeDao.updateUserRole(bean);
        return msg;
    }
    /**
     * 查询用户角色
     * @return
     */
    public UserRole queryUserRoleByID(QueryBean obj){
        UserRole userRole = null;
        List<UserRole> userRoleList = authUser.queryUserRoleById(obj);
        if(userRoleList != null && userRoleList.size()>0){
            userRole = userRoleList.get(0);
        }
        return userRole;
    }

    /**
     * 缓存存贮用户科室信息存贮
     * @param deptCode
     */
    public void saveUserDeptRedis(String userName, String deptCode){
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("ksdm", deptCode);
        List<DeptVsClinic> list = performanceService.queryDeptVsClinicList(params);
        redisUtils.setUserDept(userName, list);
    }
}
