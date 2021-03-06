package com.hd.imms.jwzzgl.controller;

import com.hd.imms.jwzzgl.entity.PatientBaseInfo;
import com.hd.imms.jwzzgl.service.JwzzService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
public class JwzzController {

    @Autowired
    JwzzService jwzzService;
    @RequestMapping(value="/QueryPatientBaseInfo", method = RequestMethod.POST)
    public Object queryPatientBaseInfo(@RequestParam(value="certificate_type",required = true) String certificate_type,
                                       @RequestParam(value="id_no",required = true) String id_no){
        log.error("log queryPatientBaseInfo id_no:"+id_no);
        Map<String, Object> map = jwzzService.getPatientInfo(certificate_type, id_no);
        return map;
    }

    @RequestMapping(value="/CreatPatient", method = RequestMethod.POST)
    public Object creatPatient(@RequestParam(value="name",required = true) String name,
                                       @RequestParam(value="gender", required = true) String gender,
                               @RequestParam(value="ageValue",required = true) String ageValue,
                               @RequestParam(value="certificate_type",required = true) String certificateType,
                               @RequestParam(value="id_no",required = true) String idNo){
        //调用his系统进行挂号
        log.error("creatPatient name:"+name+" gender:"+gender+" ageValue:"+ageValue
                +" certificate_type:"+certificateType+" id_no="+idNo);
        Map<String, Object> map = new HashMap<>();
        map.put("name", name);
        map.put("gender", gender);
        map.put("ageValue", ageValue);
        map.put("certificateType", certificateType);
        map.put("idNo", idNo);
        Map<String, Object> retMap = jwzzService.createPatient(map);
        log.error("creatPatient over name:"+name+", medical_record_id:"+retMap.get("medical_record_id"));
        return retMap;
    }
    @RequestMapping(value="/QueryPatientVitalSigns", method = RequestMethod.POST)
    public Object queryPatientVitalSigns(@RequestParam(value="medical_record_id", required = false) String medical_record_id,
                                         @RequestParam(value="emergency_id", required = false) String emergency_id){
        //查询同步病患生命体征信息
        log.error("queryPatientBaseInfo medical_record_id:"+medical_record_id+" emergency_id:"+emergency_id);
        Map<String, Object> map = jwzzService.queryPatientVitalSigns(medical_record_id, emergency_id);
        return map;
    }
    @RequestMapping(value="/QueryPatientPACSinfo", method = RequestMethod.POST)
    public Object queryPatientPACSinfo(@RequestParam(value="medical_record_id", required = false) String medical_record_id,
                                         @RequestParam(value="emergency_id", required = false) String emergency_id){
        //查询病患PACS影像报告
        log.error("QueryPatientPACSinfo medical_record_id:"+medical_record_id+" emergency_id:"+emergency_id);
        Map<String, Object> map = jwzzService.queryPatientPACSinfo(medical_record_id, emergency_id);
        return map;
    }


    @RequestMapping(value="/QueryLisInfo", method = RequestMethod.POST)
    public Object queryLisInfo(@RequestParam(value="medical_record_id", required = false) String medical_record_id,
                                       @RequestParam(value="emergency_id", required = false) String emergency_id){
        //检验检查结果信息
        log.error("queryLisInfo medical_record_id:"+medical_record_id+" emergency_id:"+emergency_id);
        Map<String, Object> map = jwzzService.queryLisInfo(medical_record_id, emergency_id);
        return map;
    }
    @RequestMapping(value="/CreatPatientCpc", method = RequestMethod.POST)
    public Object queryInHospitalInfo(@RequestParam(value="medical_record_id", required = false) String medical_record_id,
                               @RequestParam(value="emergency_id", required = false) String emergency_id){
        //获取胸痛中心需要的院内救治信息，用于胸痛中心数据上报
        log.error("queryInHospitalInfo medical_record_id:"+medical_record_id+" emergency_id:"+emergency_id);
        Map<String, Object> map = jwzzService.queryInHospitalInfo(medical_record_id, emergency_id);
        log.debug("queryInHospitalInfo result:"+map.toString());
        return map;
    }
}
