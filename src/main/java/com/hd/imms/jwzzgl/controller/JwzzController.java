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
    @RequestMapping(value="/QueryPatientBaseInfo", method = RequestMethod.GET)
    public Object queryPatientBaseInfo(@RequestParam(value="certificate_type",required = true) String certificate_type,
                                       @RequestParam(value="id_no",required = true) String id_no){
        System.out.println("queryPatientBaseInfo id_no:"+id_no);
        Map<String, Object> map = jwzzService.getPatientInfo(certificate_type, id_no);
        return map;
    }
}
