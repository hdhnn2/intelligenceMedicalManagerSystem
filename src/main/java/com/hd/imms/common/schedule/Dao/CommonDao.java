package com.hd.imms.common.schedule.Dao;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 功能：通用数据库查询
 * 日期：2021-02-18
 * 作者：hnn
 */
@Repository
public class CommonDao {
    @Autowired
    JdbcTemplate ds1JdbcTemplate;
    /**
     * 查询核酸检测未上传结果
     * @return
     */
    public List<Map<String, Object>> cxhsjcxx(){
        String sql = "select * from v_HNN_HSJC_SC t ";
        List<Map<String, Object>> list = ds1JdbcTemplate.queryForList(sql);
        return list;
    }
    /**
     *  功能: 更新核酸检测上传信息
     *  date: 2021-02-18
     */
    public void updateUploadInfo(String sqbh){
        String sql = "update HNN_HSJC_SC t set t.SCCS=nvl(t.sccs,0)+1,scjg=1,scsj=sysdate where t.remark=?";
        Object[] args = new Object[1];
        args[0] = sqbh;
        ds1JdbcTemplate.update(sql, args);
    }
}
