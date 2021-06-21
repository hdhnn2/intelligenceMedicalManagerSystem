package com.hd.imms.mapper;

import com.hd.imms.entity.authorize.User;
import com.hd.imms.entity.authorize.UserRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AuthUser {
    List<User> getUserList();
    User getUserById(@Param("userId") String userName);
    /**
     *  功能：查询用户角色
     */
    List<UserRole> queryUserRoleById(@Param("userId") String userName);
}
