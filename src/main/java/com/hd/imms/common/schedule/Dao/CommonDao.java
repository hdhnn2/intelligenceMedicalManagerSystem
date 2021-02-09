package com.hd.imms.common.schedule.Dao;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 功能：通用数据库查询
 * 日期：2021-02-07
 * 作者：hnn
 */
@Repository
public class CommonDao {
    @Autowired
    JdbcTemplate ds1JdbcTemplate;
    /**
     * 核酸检测信息
     * @return
     */
    public List<Map<String, Object>> cxhsjcxx(){
        String sql = "select t.dept_name from dept_dict t where dept_code='010101' ";
        List<Map<String, Object>> list = ds1JdbcTemplate.queryForList(sql);
        return list;
    }
}
