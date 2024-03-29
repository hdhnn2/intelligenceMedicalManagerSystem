package com.hd.imms.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hd.imms.entity.authorize.Menu;
import com.hd.imms.entity.authorize.QueryBean;
import com.hd.imms.entity.authorize.User;
import com.hd.imms.entity.authorize.UserRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface AuthUser {
    IPage<User> queryUserListByPage(IPage page, @Param("userName")String userName, @Param("sex")String sex);
    User getUserById(@Param("userId") String userName);
    /**
     *  功能：查询用户角色
     */
    List<UserRole> queryUserRoleById(QueryBean obj);
    List<Menu> queryTopMenuByRole(@Param("roleId") String roleId);
    List<Menu> queryChildrenMenuByRole(@Param("roleId") String roleId, @Param("menuId") String menuId);
    List<Menu> queryTopMenu();
    List<Menu> queryChildrenMenuByID(@Param("menuId") String menuId);
    List<Menu> queryRoleLeafMenu(QueryBean obj);
}
