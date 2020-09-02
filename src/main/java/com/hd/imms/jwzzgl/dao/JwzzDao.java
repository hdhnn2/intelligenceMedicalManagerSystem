package com.hd.imms.jwzzgl.dao;

import com.hd.imms.jwzzgl.entity.PatientBaseInfo;
import com.hd.imms.jwzzgl.entity.PatientBaseInfoRowMap;
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
    public Map<String, Object> queryPatientPACSinfo(String medical_record_id, String emergency_id){
        Map<String, Object> map = new HashMap<String, Object>();
        String sql = "select * from v_jwzzgl_hzyxbg t where t.medical_record_id=?";
        Object[] args = new Object[1];
        args[0] = medical_record_id;
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql, args);
        return map;
    }
}
