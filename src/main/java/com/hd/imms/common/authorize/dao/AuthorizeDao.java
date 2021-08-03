package com.hd.imms.common.authorize.dao;

import com.hd.imms.common.authorize.bean.Role;
import com.hd.imms.entity.authorize.QueryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.beans.Transient;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class AuthorizeDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    /**
     * 查询所有角色
     * @return
     */
    public List<Role> queryAllRole(){
        String sql = "select * from AUTH_ROLE t ";
        List<Role> list = jdbcTemplate.query(sql,
                new RowMapper<Role>() {
                    @Override
                    public Role mapRow(ResultSet resultSet, int i) throws SQLException {
                        Role role = new Role();
                        role.setRoleCode(resultSet.getString("role_code"));
                        role.setRoleName(resultSet.getString("role_name"));
                        return role;
                    }
                });
        return list;
    }
    /**
     * 新建角色
     * @return
     */
    public String createRole(Role role){
        String sql = "insert into AUTH_ROLE (role_code, role_name) " +
                "values (lpad(auth_role_seq.nextval,3,'0'),?) ";
        Object[ ] args = {role.getRoleName()};
        int cnt = jdbcTemplate.update(sql, args);
        if (cnt == 1){
            return "";
        }else{
            return "err";
        }
    }
    /**
     * 更新角色
     * @return
     */
    public String updateRole(Role role){
        String sql = "update AUTH_ROLE t set role_name=? where role_code=? ";
        Object[ ] args = {role.getRoleName(), role.getRoleCode()};
        int cnt = jdbcTemplate.update(sql, args);
        if (cnt == 1){
            return "";
        }else{
            return "err";
        }
    }

    /**
     * 删除角色
     * @return
     */
    public String deleteRole(String roleCode){
        String sql = "delete from AUTH_ROLE t where role_code=? ";
        Object[ ] args = {roleCode};
        int cnt = jdbcTemplate.update(sql, args);
        sql = "delete from auth_role_menu t where role_id=? ";
        cnt = jdbcTemplate.update(sql, args);
        return "";
    }
    /**
     * 更新角色菜单
     * @return
     */
    @Transactional
    public String updateRoleMenus(QueryBean bean){
        String sql = "delete from auth_role_menu t where role_id=? ";
        Object[ ] args = {bean.getRoleId()};
        int cnt = jdbcTemplate.update(sql, args);
        String insertSQL = "insert into auth_role_menu(wid, menu_id, role_id) values(sys_guid(),?,?) ";
        String[] menus = bean.getMenus();
        for(int i=0; i<menus.length; i++){
            Object[] insertArgs = {menus[i], bean.getRoleId()};
            jdbcTemplate.update(insertSQL, insertArgs);
        }
        return "1";
    }
    /**
     * 更新用户角色
     * @return
     */
    @Transactional
    public String updateUserRole(QueryBean bean){
        String sql = "delete from auth_user_role t where user_id=? ";
        Object[ ] args = {bean.getUserId()};
        int cnt = jdbcTemplate.update(sql, args);
        String insertSQL = "insert into auth_user_role(wid, user_id, role_id) values(sys_guid(),?,?) ";
        Object[] insertArgs = {bean.getUserId(), bean.getRoleId()};
        jdbcTemplate.update(insertSQL, insertArgs);
        return "1";
    }
}
