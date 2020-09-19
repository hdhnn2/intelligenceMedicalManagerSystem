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
}
