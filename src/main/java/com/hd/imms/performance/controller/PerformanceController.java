package com.hd.imms.performance.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hd.imms.common.authorize.bean.Role;
import com.hd.imms.performance.bean.DeptCoefficient;
import com.hd.imms.performance.service.PerformanceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/performance")
@Slf4j
public class PerformanceController {
    @Autowired
    private PerformanceService performanceService;

    /**
     * 查询所有角色
     */
    @GetMapping(value = "/deptCoefficient/list/{type}")
    public JSONObject queryAllDeptCoefficientByType(@PathVariable("type") String type,HttpServletRequest request) {
        String ksmc = request.getParameter("ksmc");
        log.error("queryAllDeptCoefficientByType type: "+type);
        List<DeptCoefficient> roleList = performanceService.queryAllDeptCoefficientByType(type, ksmc);
        JSONArray ret = (JSONArray) JSON.toJSON(roleList);
        JSONObject retJSON = new JSONObject();
        retJSON.put("code", 200);
        retJSON.put("data", ret);
        return retJSON;
    }
    /**
     * 更新科室系数
     */
    @PostMapping(value = "/deptCoefficient/update")
    public JSONObject updateDeptCoefficient(@RequestBody DeptCoefficient obj, HttpServletRequest request) throws IOException {
        log.debug("updateDeptCoefficient start: ");
        JSONObject ret = new JSONObject();
        ret.put("code", 200);
        ret.put("msg", performanceService.updateDeptCoefficient(obj));
        log.error("updateDeptCoefficient end ");
        return ret;
    }
}
