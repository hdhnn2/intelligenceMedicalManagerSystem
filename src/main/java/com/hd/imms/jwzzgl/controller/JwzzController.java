package com.hd.imms.jwzzgl.controller;

import com.hd.imms.jwzzgl.entity.PatientBaseInfo;
import com.hd.imms.jwzzgl.service.JwzzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class JwzzController {
    @Autowired
    JwzzService jwzzService;
    @RequestMapping(value="/QueryPatientBaseInfo", method = RequestMethod.POST)
    public Object queryPatientBaseInfo(@RequestParam(value="certificate_type",required = true) String certificate_type,
                                       @RequestParam(value="id_no",required = true) String id_no){
        System.out.println("queryPatientBaseInfo id_no:"+id_no);
        Map<String, Object> map = jwzzService.getPatientInfo(certificate_type, id_no);
        return map;
    }
    @RequestMapping(value="/QueryPatientVitalSigns", method = RequestMethod.GET)
    public Object QueryPatientVitalSigns(@RequestParam(value="medical_record_id",required = true) String medical_record_id,
                                       @RequestParam(value="emergency_id",required = true) String emergency_id){
        System.out.println("queryPatientBaseInfo medical_record_id:"+medical_record_id);
        Map<String, Object> map = jwzzService.getPatientInfo(medical_record_id, emergency_id);
        return map;
    }
}
