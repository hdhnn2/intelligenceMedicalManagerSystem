package com.hd.imms.yjfz.controller;

import com.alibaba.fastjson.JSONObject;
import com.hd.imms.performance.bean.DeptCoefficient;
import com.hd.imms.yjfz.bean.Yjfz;
import com.hd.imms.yjfz.service.YjfzService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;

@RestController
@Slf4j
@RequestMapping("/yjfz")
public class YjfzController {
    @Autowired
    YjfzService yjfzService;

    /**
     * 更新科室系数
     */
    @PostMapping(value="/save")
    public JSONObject updateDeptCoefficient(@RequestBody Yjfz obj, HttpServletRequest request) throws IOException {
        log.error("saveYjfz start: ");
        /*Yjfz obj = new Yjfz();
        obj.setDh(dh);
        obj.setXm(xm);
        obj.setSfzh(sfzh);*/
        JSONObject ret = new JSONObject();
        ret.put("code", 200);
        ret.put("msg", yjfzService.saveYjfz(obj));
        log.error("saveYjfz end ");
        return ret;
    }
    /**
     * 更新科室系数
     */
    @PostMapping(value="/fzbd")
    public JSONObject fzbd(@RequestBody Yjfz obj) {
        log.error("fzbd start: "+obj.getPatientId());
        JSONObject ret = yjfzService.fzbd(obj.getPatientId());
        log.error("fzbd end ");
        return ret;
    }
}
