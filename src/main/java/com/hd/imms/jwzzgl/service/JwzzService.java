package com.hd.imms.jwzzgl.service;

import com.hd.imms.jwzzgl.dao.JwzzDao;
import com.hd.imms.jwzzgl.entity.PatientBaseInfo;
import org.apache.commons.lang.StringUtils;
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
            //生命体征只要一条记录2020-09-30
            map.put("respData",list != null && list.size() >0 ? list.get(0) : "");
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

    /**
     * 获取院内救治信息
     * @param medical_record_id
     * @param emergency_id
     * @return
     */
    public Map<String, Object> queryInHospitalInfo(String medical_record_id, String emergency_id){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("msg",201);
        try{
            List<Map<String, Object>> list = jwzzDao.queryInHospitalInfo(medical_record_id, emergency_id);
            map.put("respData",list);
        } catch (Exception e){
            map.put("msg",302);     //302数据异常
            map.put("content",e.getMessage());
        }
        return map;
    }

    /**
     * 快速挂号
     * @param csMap
     * @return
     */
    public Map<String, Object> createPatient(Map<String,Object> csMap){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("msg",201);
        try{
            Map<String, String> retMap = jwzzDao.createPatient(csMap);
            if(!StringUtils.equals(retMap.get("result"),"1")){
                //1正常其它为异常
                map.put("msg",302);     //302数据异常
            }
            map.put("respData",retMap);
        } catch (Exception e){
            map.put("msg",302);     //302数据异常
            map.put("content",e.getMessage());
        }
        return map;
    }
    private Map<String, String> convertCreateCardName(Map<String, String> retMap){
        Map<String, String> map = new HashMap<String, String>();
        map.put("medical_record_id", retMap.get("medicalRecordId"));
        map.put("emergency_id", retMap.get("emergencyId"));
        return map;
    }
}
