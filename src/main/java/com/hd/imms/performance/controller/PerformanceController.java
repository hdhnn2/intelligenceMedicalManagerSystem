package com.hd.imms.performance.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hd.imms.common.authorize.service.AuthorizeService;
import com.hd.imms.entity.common.CodeBean;
import com.hd.imms.entity.common.DepartmentDictionary;
import com.hd.imms.entity.performance.*;
import com.hd.imms.performance.bean.DeptCoefficient;
import com.hd.imms.performance.service.PerformanceService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/performance")
@Slf4j
public class PerformanceController {
    @Autowired
    private PerformanceService performanceService;
    @Autowired
    private AuthorizeService authorizeService;
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
        Map<String, Object> params = new HashMap<String, Object>();
        List<DeptVsClinic> list = performanceService.queryDeptVsClinicList(params);
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
     * 计算科室医生明细得分
     */
    @PostMapping(value = "/deptCoefficient/calDoctorScoreDetail")
    public JSONObject calDoctorScoreDetail(@RequestBody DeptScore obj, HttpServletRequest request) {
        Map<String,Object> params = new HashMap<String, Object>();
        String czr = SecurityContextHolder.getContext().getAuthentication().getName();
        String rq = obj.getRq();
        log.error("calDoctorScoreDetail type: czip=" + request.getRemoteAddr()+", rq="+rq);
        params.put("czr", czr);
        params.put("rq", rq);
        params.put("czip", request.getRemoteAddr());
        String msg = performanceService.calDoctorScoreDetail(params);
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
        log.error("queryBillDetail: "+obj.toString());
        IPage<BillDetail> page = performanceService.selectPageBillDetail(obj);
        log.error("queryBillDetail ret: "+page.toString());
        JSONObject retJSON = new JSONObject();
        retJSON.put("code", 200);
        retJSON.put("data", page);
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
    @PostMapping(value = "/deptCoefficient/queryDict")
    public JSONObject queryDeptDict(@RequestBody DepartmentDictionary obj, HttpServletRequest request) {
        log.error("queryDeptDict: "+obj.toString());
        List<DepartmentDictionary> list = performanceService.queryDeptDict(obj);
        List<CodeBean> billItemClassList = performanceService.queryAllBillItemClass();
        // 登录者科室
        List<String> clinicList = performanceService.getUserClinicDept();
        JSONArray ret = (JSONArray) JSON.toJSON(list);
        JSONObject retJSON = new JSONObject();
        retJSON.put("code", 200);
        retJSON.put("deptList", list);
        retJSON.put("billItemClassList", billItemClassList);
        retJSON.put("clinicList", clinicList);
        return retJSON;
    }

    /**
     * 查询用按核算类型医院项目费
     */
    @PostMapping(value = "/deptCoefficient/queryHospitalBillByType")
    public JSONObject queryHospitalBillByType(@RequestBody BillDetailQuery obj, HttpServletRequest request) {
        log.error("queryHospitalBillByType: "+obj.toString());
        List<BillDetail> list = performanceService.queryHospitalBillByType(obj);
        JSONArray ret = (JSONArray) JSON.toJSON(list);
        JSONObject retJSON = new JSONObject();
        retJSON.put("code", 200);
        retJSON.put("data", ret);
        return retJSON;
    }
    /**
     * 查询科室角色查询
     */
    @PostMapping(value = "/deptCoefficient/queryDeptScoreByDept")
    public JSONObject queryDeptScoreByDept(@RequestBody DeptScore obj, HttpServletRequest request) {
        log.error("queryDeptScoreByDept DeptScore: "+obj.toString());
        DeptScore deptScore = performanceService.queryDeptScoreByDept(obj);
        JSONObject retJSON = new JSONObject();
        retJSON.put("code", 200);
        retJSON.put("data", JSON.toJSON(deptScore));
        return retJSON;
    }
    /**
     * 查询科室角色查询
     */
    @PostMapping(value = "/deptCoefficient/queryDeptScoreDetailByType")
    public JSONObject queryDeptScoreDetailByType(@RequestBody BillDetailQuery obj, HttpServletRequest request) {
        log.error("queryDeptScoreDetailByType DeptScore: "+obj.toString());
        IPage<BillDetail> page = performanceService.queryDeptScoreDetailByType(obj);
        JSONObject retJSON = new JSONObject();
        retJSON.put("code", 200);
        retJSON.put("data", page);
        return retJSON;
    }
    /**
     * 查询费用明细
     */
    @PostMapping(value = "/deptCoefficient/queryDoctorScoreByPage")
    public JSONObject queryDoctorScoreByPage(@RequestBody BillDetailQuery obj, HttpServletRequest request) {
        log.error("queryBillDetail: "+obj.toString());
        IPage<BillDetail> page = performanceService.queryDoctorScoreByPage(obj);
        //JSONArray ret = (JSONArray) JSON.toJSON(page.getRecords());
        JSONObject retJSON = new JSONObject();
        retJSON.put("code", 200);
        retJSON.put("data", page);
        return retJSON;
    }

    @PostMapping("/deptCoefficient/exportDeptDetail")
    public void exportDeptDetail(@RequestBody BillDetailQuery obj, HttpServletResponse response){
        log.error("export: "+obj.toString());
        // 这里 需要指定写用哪个class去写，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
        // 如果这里想使用03 则 传入excelType参数即可
        // EasyExcel.write(fileName, ScoreDetail.class).sheet("模板").doWrite(list);
        // String key = DateUtil.format(new Date(), "yyyy-MM-dd-HH-mm-ss");
        // 配置文件下载
        //response.setHeader("content-type", "application/octet-stream");
        //response.setContentType("application/octet-stream");
        //ExcelUtil.writeExcel(response, excelDtos, key, "sheet1", new ExcelScoreDto());

        log.error("exportDeptDetail: "+obj.toString());
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setCharacterEncoding("utf-8");
        String fileName = null;
        try {
            String lx = obj.getLx();
            switch (lx){
                case "1":
                    fileName = "科室医生明细";
                    break;
                case "2":
                    fileName = "护士明细";
                    break;
                case "3":
                    fileName = "医技明细";
                    break;
                case "4":
                    fileName = "医生得分明细";
                    break;
            }
            fileName = URLEncoder.encode(fileName+obj.getRq(), "UTF-8").replaceAll("\\+", "%20");
            response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");
            response.setHeader("FileName", fileName + ".xlsx");
            response.setHeader("Access-Control-Expose-Headers", "FileName");
            performanceService.downLoadExcel(obj, response);
        } catch (UnsupportedEncodingException e) {
            log.error("exportDeptDetail: "+e.toString());
        } catch (IOException e) {
            e.printStackTrace();
            log.error("exportDeptDetail: "+e.toString());
        }
    }
    /**
     * 查询护士明细
     */
    @PostMapping(value = "/deptCoefficient/queryNurseScoreDetailByType")
    public JSONObject queryNurseScoreDetailByType(@RequestBody BillDetailQuery obj, HttpServletRequest request) {
        log.error("queryNurseScoreDetailByType DeptScore: "+obj.toString());
        JSONObject retJSON = new JSONObject();
        retJSON.put("code", 200);
        if(StringUtils.equals(obj.getCkmxlx(), "1")){
            // 出院明细
            IPage<Discharge> page = performanceService.queryDischargeDetail(obj);
            retJSON.put("data", page);
        }
        if(StringUtils.equals(obj.getCkmxlx(), "2")){
            // 项目
            IPage<ScoreDetail> page = performanceService.queryNurseCareDetail(obj);
            retJSON.put("data", page);
        }
        return retJSON;
    }
    /**
     * 查询医技明细
     */
    @PostMapping(value = "/deptCoefficient/queryMedicalLabDetail")
    public JSONObject queryMedicalLabDetail(@RequestBody BillDetailQuery obj, HttpServletRequest request) {
        log.error("queryMedicalLabDetail DeptScore: "+obj.toString());
        JSONObject retJSON = new JSONObject();
        retJSON.put("code", 200);
        IPage<ScoreDetail> page = performanceService.queryMedicalLabDetail(obj);
        retJSON.put("data", page);
        return retJSON;
    }
    /**
     * 查询统计数据
     */
    @PostMapping(value = "/queryStatisticsData")
    public JSONObject queryStatisticsData(@RequestBody BillDetailQuery obj, HttpServletRequest request) {
        String token = request.getHeader("token");
        log.error("queryStatisticsData token: "+token);
        String userRole = authorizeService.getCacheUserRoleID(token);
        JSONObject retJSON = new JSONObject();
        retJSON.put("code", 200);
        retJSON.put("data", performanceService.queryStatisticsData(userRole));
        return retJSON;
    }
}
