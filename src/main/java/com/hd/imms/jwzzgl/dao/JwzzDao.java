package com.hd.imms.jwzzgl.dao;

import com.hd.imms.jwzzgl.entity.PatientBaseInfo;
import com.hd.imms.jwzzgl.entity.PatientBaseInfoRowMap;
import oracle.jdbc.OracleTypes;
import oracle.jdbc.oracore.OracleType;
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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class JwzzDao {
    @Autowired
    JdbcTemplate jdbcTemplate;
    public Map<String, Object> getPatientInfo(String certificate_type, String id_no){
        Map<String, Object> map = new HashMap<String, Object>();
        String sql = "select * from v_jwzzgl_hzjbxx t where t.card_no=?";
        Object[] args = new Object[1];
        args[0] = id_no;
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql, args);
        if(list != null){
            Map<String, Object> tmap = list.get(0);
            map.put("name",tmap.get("NAME"));
            map.put("gender",tmap.get("GENDER"));
            map.put("ageVaue",tmap.get("AGEVALUE"));
            map.put("patient_birthday",tmap.get("PATIENT_BIRTHDAY"));
            map.put("contact_phone",tmap.get("CONTACT_PHONE"));
            map.put("card_no",tmap.get("CARD_NO"));
            map.put("family_address",tmap.get("FAMILY_ADDRESS"));
        }
        return map;
    }

    /**
     * 查询病患PACS影像报告
     * @param medical_record_id
     * @param emergency_id
     * @return
     */
    public List<Map<String, Object>> queryPatientPACSinfo(String medical_record_id, String emergency_id){
        String sql = "select * from v_jwzzgl_hzyxbg t where t.medical_record_id=? ";
        Object[] args = new Object[1];
        args[0] = StringUtils.isNotEmpty(medical_record_id) ? medical_record_id : emergency_id;
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql, args);
        return list;
    }

    /**
     * 查询病患生命体征信息
     * @param medical_record_id
     * @param emergency_id
     * @return
     */
    public List<Map<String, Object>> queryPatientVitalSigns(String medical_record_id, String emergency_id){
        String sql = "select * from v_jwzzgl_smtzxx t where t.medical_record_id=? ";
        Object[] args = new Object[1];
        args[0] = StringUtils.isNotEmpty(medical_record_id) ? medical_record_id : emergency_id;
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql, args);
        return list;
    }

    /**
     * 获取检验检查结果
     * @param medical_record_id
     * @param emergency_id
     * @return
     */
    public List<Map<String, Object>> queryLisInfo(String medical_record_id, String emergency_id){
        String sql = "select * from v_jwzzgl_lis t where t.medical_record_id=? ";
        Object[] args = new Object[1];
        args[0] = StringUtils.isNotEmpty(medical_record_id) ? medical_record_id : emergency_id;
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql, args);
        return list;
    }

    /**
     * 获取院内救治信息
     * @param medical_record_id
     * @param emergency_id
     * @return
     */
    public List<Map<String, Object>> queryInHospitalInfo(String medical_record_id, String emergency_id){
        String sql = "select * from v_jwzzgl_zgxxtb t where t.medical_record_id=? ";
        Object[] args = new Object[1];
        args[0] = StringUtils.isNotEmpty(medical_record_id) ? medical_record_id : emergency_id;
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql, args);
        return list;
    }

    /**
     * 快速挂号
     * @param csMap
     * @return
     */
    public Map<String, String> createPatient(Map<String,Object> csMap){
        Map<String, String> retMap = new HashMap<String, String>();
        String name = csMap.get("name").toString();
        String gender = csMap.get("gender").toString();
        String age = csMap.get("ageValue").toString();
        String certificateType = csMap.get("certificateType").toString();
        String idNo = csMap.get("idNo").toString();
        jdbcTemplate.execute(new CallableStatementCreator() {
            @Override
            public CallableStatement createCallableStatement(Connection connection) throws SQLException {
                String sql = "{call p_jwzzgl_createPatient(?,?,?,?,?,?,?)}";
                CallableStatement cs = connection.prepareCall(sql);
                cs.setString(1, name);
                cs.setString(2, gender);
                cs.setString(3, age);
                cs.setString(4, certificateType);
                cs.setString(5, idNo);
                cs.registerOutParameter(6, OracleTypes.VARCHAR);
                cs.registerOutParameter(7, OracleTypes.VARCHAR);
                return null;
            }
        }, new CallableStatementCallback() {

            @Override
            public Object doInCallableStatement(CallableStatement callableStatement) throws SQLException, DataAccessException {
                callableStatement.execute();
                String flag = callableStatement.getString(6);
                String medicalRecordId = callableStatement.getString(7);
                retMap.put("result", flag);
                retMap.put("medicalRecordId", medicalRecordId);
                retMap.put("emergencyId", medicalRecordId);
                return null;
            }
        });
        return retMap;
    }
}
