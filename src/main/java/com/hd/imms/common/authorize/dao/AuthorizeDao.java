package com.hd.imms.common.authorize.dao;

import com.hd.imms.common.authorize.bean.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class AuthorizeDao {

    @Autowired
    JdbcTemplate ds1JdbcTemplate;

    /**
     * 查询所有角色
     * @return
     */
    public List<Role> queryAllRole(){
        String sql = "select * from AUTH_ROLE t ";
        List<Role> list = ds1JdbcTemplate.query(sql,
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
        int cnt = ds1JdbcTemplate.update(sql, args);
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
        int cnt = ds1JdbcTemplate.update(sql, args);
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
        int cnt = ds1JdbcTemplate.update(sql, args);
        if (cnt == 1){
            return "";
        }else{
            return "err";
        }
    }
}
