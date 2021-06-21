package com.hd.imms.common.security;

import com.hd.imms.entity.authorize.User;
import com.hd.imms.mapper.AuthUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.Set;

@Service
@Slf4j
public class SysUserServiceImpl {
    @Resource
    @Autowired
    AuthUser authUser;
    public User findByUsername(String username) {
        com.hd.imms.entity.authorize.User user = null;
        try {
            user = authUser.getUserById(username);
        }catch (Exception e){
            log.error("findByUsername error: "+e.getCause());
        }
        if(user != null){
            String password = new BCryptPasswordEncoder().encode(user.getPassword());
            user.setPassword(password);
        }
        return user;
    }

    public Set<String> findPermissions(String username) {
        Set<String> permissions = new HashSet<>();
        permissions.add("sys:user:view");
        permissions.add("sys:user:add");
        permissions.add("sys:user:edit");
        permissions.add("sys:user:delete");
        return permissions;
    }
}
