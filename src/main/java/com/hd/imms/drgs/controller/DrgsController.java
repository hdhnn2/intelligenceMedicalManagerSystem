package com.hd.imms.drgs.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hd.imms.drgs.service.DrgsService;
import com.hd.imms.entity.authorize.User;
import com.hd.imms.entity.drgs.Bacyxx;
import com.hd.imms.entity.drgs.DeptDict;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/drgs")
public class DrgsController {
    @Autowired
    DrgsService drgsService;
    @GetMapping(value = "/cybaxxcx")
    public JSONObject cybaxxcx(HttpServletRequest request) {
        log.error("cybaxxcx start: "+System.currentTimeMillis());
        // 病案系统出院信息
        List<Bacyxx> list = drgsService.cybaxxcx();
        JSONArray ret = (JSONArray) JSON.toJSON(list);
        JSONObject retJSON = new JSONObject();
        retJSON.put("code", 200);
        retJSON.put("data", ret);
        log.error("cybaxxcx end: "+System.currentTimeMillis());
        return retJSON;
    }
    @GetMapping(value = "/queryDeptDic")
    public JSONObject queryDeptDic(HttpServletRequest request) {
        log.error("queryDeptDic start: "+System.currentTimeMillis());
        // 病案系统出院信息
        List<DeptDict> list = drgsService.queryDeptDic();
        JSONArray ret = (JSONArray) JSON.toJSON(list);
        JSONObject retJSON = new JSONObject();
        retJSON.put("code", 200);
        retJSON.put("data", ret);
        log.error("queryDeptDic end: "+System.currentTimeMillis());
        return retJSON;
    }
}
