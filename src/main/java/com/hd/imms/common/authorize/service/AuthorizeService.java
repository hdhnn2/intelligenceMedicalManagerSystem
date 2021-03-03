package com.hd.imms.common.authorize.service;

import com.hd.imms.common.authorize.bean.Role;
import com.hd.imms.common.authorize.dao.AuthorizeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorizeService {
    @Autowired
    AuthorizeDao authorizeDao;

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
}
