package com.hd.imms.performance.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hd.imms.common.authorize.bean.Role;
import com.hd.imms.entity.common.DepartmentDictionary;
import com.hd.imms.entity.common.SystemParameterBean;
import com.hd.imms.entity.performance.BillDetail;
import com.hd.imms.entity.performance.BillDetailQuery;
import com.hd.imms.entity.performance.DeptScore;
import com.hd.imms.entity.performance.DeptVsClinic;
import com.hd.imms.performance.bean.DeptCoefficient;
import com.hd.imms.performance.service.PerformanceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/performance")
@Slf4j
public class PerformanceController {
    @Autowired
    private PerformanceService performanceService;

    /**
     * 查询类型所有科室系数
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
    /**
     * 查询科室门诊对照
     */
    @GetMapping(value = "/deptCoefficient/queryDeptVsClinicList")
    public JSONObject queryDeptVsClinicList(HttpServletRequest request) {
        log.error("queryAllDeptCoefficientByType type: ");
        List<DeptVsClinic> list = performanceService.queryDeptVsClinicList();
        JSONArray ret = (JSONArray) JSON.toJSON(list);
        JSONObject retJSON = new JSONObject();
        retJSON.put("code", 200);
        retJSON.put("data", ret);
        return retJSON;
    }
    /**
     * 查询科室门诊对照
     */
    @PostMapping(value = "/deptCoefficient/queryDeptScore")
    public JSONObject queryDeptScore(@RequestBody DeptScore obj, HttpServletRequest request) {
        log.error("queryDeptScore DeptScore: "+obj.toString());
        List<DeptScore> list = performanceService.queryDeptScore(obj);
        JSONArray ret = (JSONArray) JSON.toJSON(list);
        JSONObject retJSON = new JSONObject();
        retJSON.put("code", 200);
        retJSON.put("data", ret);
        return retJSON;
    }
    /**
     * 计算科室医生得分
     */
    @PostMapping(value = "/deptCoefficient/calDoctorScore")
    public JSONObject calDoctorScore(@RequestBody DeptScore obj, HttpServletRequest request) {
        Map<String,Object> params = new HashMap<String, Object>();
        String czr = SecurityContextHolder.getContext().getAuthentication().getName();
        String rq = obj.getRq();
        log.error("calDoctorScore type: czip=" + request.getRemoteAddr()+", rq="+rq);
        params.put("czr", czr);
        params.put("rq", rq);
        params.put("czip", request.getRemoteAddr());
        String msg = performanceService.calDoctorScore(params);
        JSONObject retJSON = new JSONObject();
        retJSON.put("code", 200);
        retJSON.put("data", msg);
        return retJSON;
    }
    /**
     * 计算科室护士得分
     */
    @PostMapping(value = "/deptCoefficient/calNurseScore")
    public JSONObject calNurseScore(@RequestBody DeptScore obj, HttpServletRequest request) {
        Map<String,Object> params = new HashMap<String, Object>();
        String czr = SecurityContextHolder.getContext().getAuthentication().getName();
        String rq = obj.getRq();
        log.error("calNurseScore type: czip=" + request.getRemoteAddr()+", rq="+rq);
        params.put("czr", czr);
        params.put("rq", rq);
        params.put("czip", request.getRemoteAddr());
        String msg = performanceService.calNurseScore(params);
        JSONObject retJSON = new JSONObject();
        retJSON.put("code", 200);
        retJSON.put("data", msg);
        return retJSON;
    }
    /**
     * 计算科室医生得分
     */
    @PostMapping(value = "/deptCoefficient/calMedLabScore")
    public JSONObject calMedLabScore(@RequestBody DeptScore obj, HttpServletRequest request) {
        Map<String,Object> params = new HashMap<String, Object>();
        String czr = SecurityContextHolder.getContext().getAuthentication().getName();
        String rq = obj.getRq();
        log.error("calMedLabScore type: czip=" + request.getRemoteAddr()+", rq="+rq);
        params.put("czr", czr);
        params.put("rq", rq);
        params.put("czip", request.getRemoteAddr());
        String msg = performanceService.calMedLabScore(params);
        JSONObject retJSON = new JSONObject();
        retJSON.put("code", 200);
        retJSON.put("data", msg);
        return retJSON;
    }

    /**
     * 查询费用明细
     */
    @PostMapping(value = "/deptCoefficient/queryBillDetail")
    public JSONObject queryBillDetail(@RequestBody BillDetailQuery obj, HttpServletRequest request) {
        log.error("selectPageBillDetail: "+obj.toString());
        //page..isSearchCount(true);
        List<BillDetail> list = performanceService.selectPageBillDetail(obj);
        JSONArray ret = (JSONArray) JSON.toJSON(list);
        JSONObject retJSON = new JSONObject();
        retJSON.put("code", 200);
        retJSON.put("data", ret);
        return retJSON;
    }

    /**
     * 查询科室门诊对照
     */
    @GetMapping(value = "/deptCoefficient/checkDeptIsBrowse")
    public JSONObject checkDeptIsBrowse(HttpServletRequest request) {
        log.error("checkDeptIsBrowse type: ");
        SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        JSONObject retJSON = new JSONObject();
        retJSON.put("code", 200);
        retJSON.put("data", performanceService.checkDeptIsBrowse());
        return retJSON;
    }

    /**
     * 查询费用明细
     */
    @PostMapping(value = "/deptCoefficient/queryDeptDict")
    public JSONObject queryDeptDict(@RequestBody DepartmentDictionary obj, HttpServletRequest request) {
        log.error("queryDeptDict: "+obj.toString());
        List<DepartmentDictionary> list = performanceService.queryDeptDict(obj);
        JSONArray ret = (JSONArray) JSON.toJSON(list);
        JSONObject retJSON = new JSONObject();
        retJSON.put("code", 200);
        retJSON.put("data", ret);
        return retJSON;
    }
}
