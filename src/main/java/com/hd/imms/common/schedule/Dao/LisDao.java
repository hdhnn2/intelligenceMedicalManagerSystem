package com.hd.imms.common.schedule.Dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class LisDao {
       /* @Qualifier("ds2JdbcTemplate")
        @Autowired
        JdbcTemplate ds2JdbcTemplate;
        *//**
         * 核酸检测信息
         * @return
         *//*
        public List<Map<String, Object>> cxhsjcxx(){
        String sql = "select t.* from lis_up_report t where t.remark='992102045712'";
        List<Map<String, Object>> list = ds2JdbcTemplate.queryForList(sql);
        return list;
    }*/
}
