package com.hd.imms.performance.dao;

import com.hd.imms.common.authorize.bean.Role;
import com.hd.imms.performance.bean.DeptCoefficient;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class PerformanceDao {
    @Autowired
    JdbcTemplate ds1JdbcTemplate;

    /**
     * 查询类型所有科室系数
     * @return
     */
    public List<DeptCoefficient> queryAllDeptCoefficientByType(String type, String ksmc){
        StringBuffer sql = new StringBuffer("select * from V_XJX_FZ_ZD t where t.lx=?");
        Object[] args = null;
        if(StringUtils.isNotEmpty(ksmc)){
            sql.append(" and t.ksmc like ?");
            args = new Object[2];
            args[0] = type;
            args[1] = "%"+ksmc+"%";
        }else {
            args = new Object[1];
            args[0] = type;
        }
        List<DeptCoefficient> list = ds1JdbcTemplate.query(sql.toString(), args,
                new BeanPropertyRowMapper<DeptCoefficient>(DeptCoefficient.class));
        return list;
    }
    /**
     * 功能：更新科室系数
     * @date 2021-03-08
     * @return
     */
    public String updateDeptCoefficient(DeptCoefficient obj){
        String lx = obj.getLx();
        int cnt = 0;
        if(StringUtils.equals("ys", lx)){
            String sql = "update xjx_fz_zd_ptcs t set mzlfz=?,zyrrfz=?,fhxfz=?,hxjf=?,ssfz=?,kfxmfz=? where ksdm=? and lx=?";
            Object[ ] args = {obj.getMzlfz(), obj.getZyrrfz(), obj.getFhxfz(), obj.getHxjf(),
                    obj.getSsfz(), obj.getKfxmfz(), obj.getKsdm(), lx};
            cnt = ds1JdbcTemplate.update(sql, args);
        }
        if (cnt == 1){
            return "";
        }else{
            return "err";
        }
    }
}
