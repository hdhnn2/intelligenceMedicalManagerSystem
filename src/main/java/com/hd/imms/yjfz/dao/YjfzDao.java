package com.hd.imms.yjfz.dao;

import com.alibaba.fastjson.JSONObject;
import com.hd.imms.performance.bean.DeptCoefficient;
import com.hd.imms.yjfz.bean.Yjfz;
import oracle.jdbc.OracleTypes;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.CallableStatementCreator;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class YjfzDao {

    @Autowired
    JdbcTemplate ds1JdbcTemplate;
    /**
     * 查询类型所有科室系数
     * @return
     */
    public List<DeptCoefficient> queryYjfzByDate(String type){
        StringBuffer sql = new StringBuffer("select * from v_outp_yjfz t where t.lx=?");
        List<DeptCoefficient> list = ds1JdbcTemplate.query(sql.toString(),
                new BeanPropertyRowMapper<DeptCoefficient>(DeptCoefficient.class));
        return list;
    }
    /**
     * 功能：保存预检分诊
     * @date 2021-03-18
     * @return
     */
    public String saveYjfz(Yjfz obj){
        String sql = "insert into outp_yjfz (djrq, xm, sfzh, dh) values(sysdate,?,?,?)";
        Object[ ] args = {obj.getXm(), obj.getSfzh(), obj.getDh()};
        int cnt = ds1JdbcTemplate.update(sql, args);
        return String.valueOf(cnt);
    }
    /**
     * 复诊报道
     * @param patientId
     * @return
     */
    public JSONObject fzbd(String patientId){
        JSONObject ret = new JSONObject();
        ds1JdbcTemplate.execute(new CallableStatementCreator() {
            @Override
            public CallableStatement createCallableStatement(Connection connection) throws SQLException {
                String sql = "{call outpdoct.p_outp_fzbd(?,?)}";
                CallableStatement cs = connection.prepareCall(sql);
                cs.setString(1, patientId);
                cs.registerOutParameter(2, OracleTypes.VARCHAR);
                return cs;
            }
        }, new CallableStatementCallback() {
            @Override
            public Object doInCallableStatement(CallableStatement callableStatement) throws SQLException, DataAccessException {
                callableStatement.execute();
                String fzh = callableStatement.getString(2);
                ret.put("code", 200);
                ret.put("fzh", fzh);
                return null;
            }
        });
        return ret;
    }
}
