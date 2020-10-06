package com.hd.imms.jwzzgl.dao;

import com.hd.imms.jwzzgl.entity.PatientBaseInfo;
import com.hd.imms.jwzzgl.entity.PatientBaseInfoRowMap;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

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
}
