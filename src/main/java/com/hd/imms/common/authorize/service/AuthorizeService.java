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

}
