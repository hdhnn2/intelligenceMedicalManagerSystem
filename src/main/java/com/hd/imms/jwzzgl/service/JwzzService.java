package com.hd.imms.jwzzgl.service;

import com.hd.imms.jwzzgl.dao.JwzzDao;
import com.hd.imms.jwzzgl.entity.PatientBaseInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class JwzzService {
    @Resource
    private JwzzDao jwzzDao;
    public Map<String, Object> getPatientInfo(String certificate_type, String id_no){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("msg",201);
        try{
            map.put("respData", jwzzDao.getPatientInfo(certificate_type, id_no));
        } catch (Exception e){
            map.put("msg",302);     //302数据异常
            map.put("content",e.getMessage());
        }
        return map;
    }

    /**
     * 查询病患PACS影像报告
     * @param certificate_type
     * @param id_no
     * @return
     */
    public Map<String, Object> queryPatientPACSinfo(String certificate_type, String id_no){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("msg",201);
        try{
            List<Map<String, Object>> list = jwzzDao.queryPatientPACSinfo(certificate_type, id_no);
            map.put("respData",list);
        } catch (Exception e){
            map.put("msg",302);     //302数据异常
            map.put("content",e.getMessage());
        }
        return map;
    }

    /**
     * 查询病患生命体征信息
     * @param medical_record_id
     * @param emergency_id
     * @return
     */
    public Map<String, Object> queryPatientVitalSigns(String medical_record_id, String emergency_id){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("msg",201);
        try{
            List<Map<String, Object>> list = jwzzDao.queryPatientVitalSigns(medical_record_id, emergency_id);
            map.put("respData",list);
        } catch (Exception e){
            map.put("msg",302);     //302数据异常
            map.put("content",e.getMessage());
        }
        return map;
    }

    /**
     * 获取检验检查结果
     * @param medical_record_id
     * @param emergency_id
     * @return
     */
    public Map<String, Object> queryLisInfo(String medical_record_id, String emergency_id){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("msg",201);
        try{
            List<Map<String, Object>> list = jwzzDao.queryLisInfo(medical_record_id, emergency_id);
            map.put("respData",list);
        } catch (Exception e){
            map.put("msg",302);     //302数据异常
            map.put("content",e.getMessage());
        }
        return map;
    }
}
