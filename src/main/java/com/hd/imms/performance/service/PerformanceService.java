package com.hd.imms.performance.service;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hd.imms.common.utils.SecurityUtils;
import com.hd.imms.entity.common.DepartmentDictionary;
import com.hd.imms.entity.common.SystemParameterBean;
import com.hd.imms.entity.performance.*;
import com.hd.imms.mapper.CommonMapper;
import com.hd.imms.mapper.Performance;
import com.hd.imms.performance.bean.DeptCoefficient;
import com.hd.imms.performance.dao.PerformanceDao;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;

@Service
@Slf4j
public class PerformanceService {
    @Autowired
    private PerformanceDao dao;
    @Autowired
    @Resource
    Performance performance;
    @Autowired
    @Resource
    CommonMapper commonMapper;

    /**
     * 查询类型所有科室系数
     * @return
     */
    public List<DeptCoefficient> queryAllDeptCoefficientByType(String type, String ksmc){
        return dao.queryAllDeptCoefficientByType(type, ksmc);
    }
    /**
     * 功能：更新科室系数
     * @date 2021-03-08
     * @return
     */
    public String updateDeptCoefficient(DeptCoefficient obj){
        return dao.updateDeptCoefficient(obj);
    }
    /**
     * 功能：查询科室门诊对照
     * @date 2021-05-12
     * @return
     */
    public List<DeptVsClinic> queryDeptVsClinicList(){
        List<DeptVsClinic> list = performance.queryDeptVsClinicList();
        return list;
    }
    /**
     * 功能：查询科室得分
     * @date 2021-05-13
     * @return
     */
    public List<DeptScore> queryDeptScore(DeptScore obj){
        List<DeptScore> list = performance.queryDeptScore(obj);
        return list;
    }
    /**
     * 功能：计算科室医生得分
     * @date 2021-05-13
     * @return
     */
    public String calDoctorScore(Map<String,Object> params){
        String rq = params.get("rq").toString();
        params.put("ksrq", getCalulateDate(rq, "start"));
        params.put("jsrq", getCalulateDate(rq, "end"));
        performance.calDoctorScore(params);
        String msg = (String)params.get("msg");
        return msg;
    }
    /**
     * 功能：计算科室护士得分
     * @date 2021-05-14
     * @return
     */
    public String calNurseScore(Map<String,Object> params){
        String rq = params.get("rq").toString();
        params.put("ksrq", getCalulateDate(rq, "start"));
        params.put("jsrq", getCalulateDate(rq, "end"));
        performance.calNurseScore(params);
        String msg = (String)params.get("msg");
        return msg;
    }
    /**
     * 功能：计算医技科室得分
     * @date 2021-05-14
     * @return
     */
    public String calMedLabScore(Map<String,Object> params){
        String rq = params.get("rq").toString();
        params.put("ksrq", getCalulateDate(rq, "start"));
        params.put("jsrq", getCalulateDate(rq, "end"));
        performance.calMedLabScore(params);
        String msg = (String)params.get("msg");
        return msg;
    }
    /**
     * 功能：计算科室医生得分
     * @date 2021-06-07
     * @return
     */
    public String calDoctorScoreDetail(Map<String,Object> params){
        String rq = params.get("rq").toString();
        params.put("ksrq", getCalulateDate(rq, "start"));
        params.put("jsrq", getCalulateDate(rq, "end"));
        performance.calDoctorScoreDetail(params);
        String msg = (String)params.get("msg");
        return msg;
    }
    private String getCalulateDate(String rq, String type){
        Integer year = Integer.parseInt(rq.substring(0,4));
        Integer month = Integer.parseInt(rq.substring(5));
        Calendar cal = Calendar.getInstance();
        if(StringUtils.equals(type, "start")){
            cal.set(month == 1 ? year-1 : year, month-2, 26, 0, 0, 0);
        }else{
            cal.set(year, month-1, 25, 23,59,59);
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(cal.getTime());
    }
    public List<BillDetail> selectPageBillDetail(BillDetailQuery obj) {

/*        Page<BillDetail> page = new Page<>(1, 5);
        page.setOptimizeCountSql(false);
        List<BillDetail> userIPage = performance.selectPageBillDetail(page);*/
        Map<String,Object> params = new HashMap<String,Object>();
        String[] jfrq = obj.getJfrq();
        params.put("kssj", jfrq[0]+" 00:00:00");
        params.put("jssj", jfrq[1]+" 23:59:59");
        String xmmc = obj.getXmmc();
        if(StringUtils.isNotEmpty(xmmc)){
            params.put("xmmc", xmmc.trim());
        }
        List<BillDetail> list = performance.selectPageBillDetail(params);
        return list;
    }
    public SystemParameterBean getSysParmById(SystemParameterBean obj){
        SystemParameterBean ret = new SystemParameterBean();
        List<SystemParameterBean> list = commonMapper.querySystemParameter(obj);
        if(list != null && list.size()>0){
            ret = list.get(0);
        }
        return ret;
    }
    public JSONObject checkDeptIsBrowse(){
        //默认不可以查询
        JSONObject retJSON = new JSONObject();
        boolean checkResult = false;
        SystemParameterBean obj = new SystemParameterBean();
        obj.setCsdm("JXGL");
        obj.setZcsdm("KSSFKYCX");
        SystemParameterBean ret = this.getSysParmById(obj);
        if(ret != null && StringUtils.equals("1", ret.getCsz())){
            checkResult = true;
        }else {
            retJSON.put("msg", "");
        }
        retJSON.put("checkResult", checkResult);
        return retJSON;
    }
    /**
     * 功能：查询科室字典
     * @date 2021-05-20
     * @return
     */
    public List<DepartmentDictionary> queryDeptDict(DepartmentDictionary obj){
        List<DepartmentDictionary> list = commonMapper.queryDeptDict(obj);
        return list;
    }

    /**
     * 查询医院费用，不分页
     * @param obj
     * @return
     */
    public List<BillDetail> queryHospitalBillByType(BillDetailQuery obj) {
        List<BillDetail> list = new ArrayList<BillDetail>();
        Map<String,Object> params = new HashMap<String,Object>();
        String[] jfrq = obj.getJfrq();
        params.put("ksrq", jfrq[0]);
        params.put("jsrq", jfrq[1]);
        params.put("kssj", jfrq[0]+" 00:00:00");
        params.put("jssj", jfrq[1]+" 23:59:59");
        String type = obj.getLx();
        if(StringUtils.equals("1", type)){
            //住院开单
            list = performance.queryInpBillByOrder(params);
        }
        if(StringUtils.equals("2", type)){
            //住院执行
            list = performance.queryInpBillByPerformBy(params);
        }
        if(StringUtils.equals("3", type)){
            //门诊开单
            list = performance.queryOutpBillByOrder(params);
        }
        if(StringUtils.equals("4", type)){
            //门诊开单
            list = performance.queryOutpByPerformBy(params);
        }
        return list;
    }

    /**
     * 功能：查询科室得分
     * @date 2021-05-13
     * @return
     */
    public DeptScore queryDeptScoreByDept(DeptScore obj){
        //查询当前用户所在科室
        String userDept = getUserDept();
        DeptScore ret = new DeptScore();
        if(StringUtils.isEmpty(userDept)){
            return ret;
        }
        obj.setKsdm(userDept);
        log.error("queryDeptScoreByDept obj:-___"+ obj.toString());
        List<DeptScore> list = performance.queryDeptScore(obj);
        if(list != null && list.size()>0){
            ret = list.get(0);
        }
        return ret;
    }

    // 分类查询科室明细
    public IPage<BillDetail> queryDeptScoreDetailByType(BillDetailQuery obj){
        Map<String,Object> params = new HashMap<String,Object>();
        String rq = obj.getRq();
        String kssj = getCalulateDate(rq, "start");
        String jssj = getCalulateDate(rq, "end");
        String xmmc = obj.getXmmc();
        Page<BillDetail> page = new Page<>(obj.getCurrent(), obj.getSize());
        //当前用户所在科室
        String orderBy = getUserDept();
        if(StringUtils.equals(obj.getLx(), "1")){
            // 科室核心
            return performance.queryDeptTreatDetail(page, kssj, jssj, orderBy, (xmmc != null ? xmmc.trim() : null));
        }
        if(StringUtils.equals(obj.getLx(), "2")){
            // 科室执行
            return performance.queryDeptExecuteDetail(page, kssj, jssj, orderBy, (xmmc != null ? xmmc.trim() : null));
        }
        if(StringUtils.equals(obj.getLx(), "3")){
            // 科室非核心
            return performance.queryDeptLabDetail(page, kssj, jssj, orderBy, (xmmc != null ? xmmc.trim() : null));
        }
        if(StringUtils.equals(obj.getLx(), "4")){
            // 科室手术
            return performance.queryDeptOperationScoreByPage(page, kssj, jssj, orderBy, (xmmc != null ? xmmc.trim() : null));
        }
        if(StringUtils.equals(obj.getLx(), "5")){
            // 科室康复项目
            return performance.queryDeptRecoveryDetail(page, kssj, jssj, orderBy, (xmmc != null ? xmmc.trim() : null));
        }
        return page;
    }
    public IPage<BillDetail> selectPageBillDetail() {

        Page<BillDetail> page = new Page<>();
        IPage<BillDetail> userIPage = performance.selectPageBillDetail1(page);

        return userIPage;
    }
    public String getUserDept(){
        //查询当前用户所在科室
        String userName = SecurityUtils.getUsername();
        String userDept = "";
        log.error("getUserDept userName:-___"+ userName);
        DeptScore ret = new DeptScore();
        if(StringUtils.isEmpty(userName) || StringUtils.equals(userName,StringUtils.lowerCase("anonymous"))){
            return userDept;
        }
        Map<String,String> map = new HashMap<>();
        map.put("yhm", userName);
        List<DepartmentDictionary> deptList = commonMapper.queryUserDeptById(map);
        if(deptList != null && deptList.size()>0){
            userDept = deptList.get(0).getDeptCode();
        }
        //医技科室直接返回，护士以02结尾，替换成01
        return userDept.startsWith("02") ? userDept : userDept.substring(0,4)+"01";
    }

    /**
     * 查询医生个人得分概览
     * @Date 2021-06-21
     * @return
     */
    public IPage<BillDetail> queryDoctorScoreByPage(BillDetailQuery params) {
        Page<BillDetail> page = new Page<>(params.getCurrent(), params.getSize());
        IPage<BillDetail> userIPage = performance.queryDoctorScoreByPage(page, params.getRq(), params.getKsmc());
        return userIPage;
    }
    /**
     *
     */
    public JSONObject convertPageToJSON(Page page){
        JSONObject ret = new JSONObject();
        ret.put("items", page.getRecords());
        return ret;
    }
    /**
     * 功能：计算科室得分明细
     * @date 2021-06-07
     * @return
     */
    public void downLoadExcel(BillDetailQuery obj, HttpServletResponse response) throws IOException {
        String lx = obj.getLx();
        switch (lx){
            case "1":
                downLoadDeptDetail(obj, response);
                break;
            case "2":
                downLoadNurseDetail(obj, response);
                break;
            case "3":
                downLoadMedLabDetail(obj, response);
                break;
            case "4":
                downLoadDoctorDetail(obj, response);
                break;
        }
    }
    /**
     * 功能：计算科室得分明细
     * @date 2021-06-07
     * @return
     */
    public void downLoadDeptDetail(BillDetailQuery obj, HttpServletResponse response) throws IOException {
        String rq = obj.getRq();
        Map<String, Object> params = new HashMap<>();
        params.put("kssj", getCalulateDate(rq, "start"));
        params.put("jssj", getCalulateDate(rq, "end"));
        List<ScoreDetail> operationList = performance.queryDeptOperationDetail(params);
        List<ScoreDetail> labList = performance.queryDeptLabDetail(params);
        List<ScoreDetail> treatList = performance.queryDeptTreatDetail(params);
        List<ScoreDetail> deptExecuteList = performance.queryDeptExecuteDetail(params);
        List<ScoreDetail> recoveryList = performance.queryDeptRecoveryDetail(params);
        ExcelWriter excelWriter = null;
        try{
            excelWriter = EasyExcel.write(response.getOutputStream(), ScoreDetail.class).build();
            WriteSheet writeSheet = EasyExcel.writerSheet(0, "手术").build();
            excelWriter.write(operationList, writeSheet);
            writeSheet = EasyExcel.writerSheet(1, "非核心").build();
            excelWriter.write(labList, writeSheet);
            writeSheet = EasyExcel.writerSheet(2, "核心").build();
            excelWriter.write(treatList, writeSheet);
            writeSheet = EasyExcel.writerSheet(3, "本科室执行").build();
            excelWriter.write(deptExecuteList, writeSheet);
            writeSheet = EasyExcel.writerSheet(4, "康复").build();
            excelWriter.write(recoveryList, writeSheet);
        } finally {
            if(excelWriter != null){
                excelWriter.finish();
            }
        }
    }
    /**
     * 功能：计算科室得分明细
     * @date 2021-06-07
     * @return
     */
    public void downLoadNurseDetail(BillDetailQuery obj, HttpServletResponse response) throws IOException {
        String rq = obj.getRq();
        Map<String, Object> params = new HashMap<>();
        params.put("kssj", getCalulateDate(rq, "start"));
        params.put("jssj", getCalulateDate(rq, "end"));
        List<Discharge> dischargeList = performance.queryDischargeDetail(params);
        List<ScoreDetail> careList = performance.queryNurseCareDetail(params);
        ExcelWriter excelWriter = null;
        try{
            excelWriter = EasyExcel.write(response.getOutputStream(), ScoreDetail.class).build();
            WriteSheet writeSheet = EasyExcel.writerSheet(0, "出院").build();
            excelWriter.write(dischargeList, writeSheet);
            writeSheet = EasyExcel.writerSheet(1, "核心").build();
            excelWriter.write(careList, writeSheet);
        } finally {
            if(excelWriter != null){
                excelWriter.finish();
            }
        }
    }
    /**
     * 功能：医技科室得分明细
     * @date 2021-06-07
     * @return
     */
    public void downLoadMedLabDetail(BillDetailQuery obj, HttpServletResponse response) throws IOException {
        String rq = obj.getRq();
        Map<String, Object> params = new HashMap<>();
        params.put("kssj", getCalulateDate(rq, "start"));
        params.put("jssj", getCalulateDate(rq, "end"));
        List<ScoreDetail> list = performance.queryMedicalLabDetail(params);
        ExcelWriter excelWriter = null;
        try{
            excelWriter = EasyExcel.write(response.getOutputStream(), ScoreDetail.class).build();
            WriteSheet writeSheet = EasyExcel.writerSheet(0, "核心").build();
            excelWriter.write(list, writeSheet);
        } finally {
            if(excelWriter != null){
                excelWriter.finish();
            }
        }
    }
    /**
     * 功能：计算科室得分明细
     * @date 2021-06-07
     * @return
     */
    public void downLoadDoctorDetail(BillDetailQuery obj, HttpServletResponse response) throws IOException {
        String rq = obj.getRq();
        Map<String, Object> params = new HashMap<>();
        params.put("kssj", getCalulateDate(rq, "start"));
        params.put("jssj", getCalulateDate(rq, "end"));
        List<Outpatient> outpatientListList = performance.queryDoctorOutpDetail(params);
        List<DoctScore> operationList = performance.queryDoctorOperDetail(params);
        List<DoctScore> labList = performance.queryDoctorLabDetail(params);
        List<DoctScore> treatList = performance.queryDoctorTreatDetail(params);
        List<DoctScore> deptExecuteList = performance.queryDoctorExecuteDetail(params);
        List<DoctScore> recoveryList = performance.queryDoctorRecoveryDetail(params);
        ExcelWriter excelWriter = null;
        try{
            excelWriter = EasyExcel.write(response.getOutputStream(), DoctScore.class).build();
            WriteSheet writeSheet = EasyExcel.writerSheet(0, "手术").build();
            excelWriter.write(operationList, writeSheet);
            writeSheet = EasyExcel.writerSheet(1, "非核心").build();
            excelWriter.write(labList, writeSheet);
            writeSheet = EasyExcel.writerSheet(2, "核心").build();
            excelWriter.write(treatList, writeSheet);
            writeSheet = EasyExcel.writerSheet(3, "本科室执行").build();
            excelWriter.write(deptExecuteList, writeSheet);
            writeSheet = EasyExcel.writerSheet(4, "康复").build();
            excelWriter.write(recoveryList, writeSheet);
            writeSheet = EasyExcel.writerSheet(5, "门诊").build();
            excelWriter.write(outpatientListList, writeSheet);
        } finally {
            if(excelWriter != null){
                excelWriter.finish();
            }
        }
    }
    // 出院人次
    public IPage<Discharge> queryDischargeDetail(BillDetailQuery obj){
        Map<String,Object> params = new HashMap<String,Object>();
        String rq = obj.getRq();
        String kssj = getCalulateDate(rq, "start");
        String jssj = getCalulateDate(rq, "end");
        Page<Discharge> page = new Page<>(obj.getCurrent(), obj.getSize());
        //当前用户所在科室
        String orderBy = getUserDept();
        // 出院按病区
        return performance.queryDischargeDetail(page, kssj, jssj, orderBy.substring(0,4) + "02");
    }
    // 项目明细
    public IPage<ScoreDetail> queryNurseCareDetail(BillDetailQuery obj){
        Map<String,Object> params = new HashMap<String,Object>();
        String rq = obj.getRq();
        String kssj = getCalulateDate(rq, "start");
        String jssj = getCalulateDate(rq, "end");
        String xmmc = obj.getXmmc();
        Page<ScoreDetail> page = new Page<>(obj.getCurrent(), obj.getSize());
        String orderBy = getUserDept();
        return performance.queryNurseCareDetail(page, kssj, jssj, orderBy, (xmmc != null ? xmmc.trim() : null));
    }
    // 项目明细
    public IPage<ScoreDetail> queryMedicalLabDetail(BillDetailQuery obj){
        Map<String,Object> params = new HashMap<String,Object>();
        String rq = obj.getRq();
        String kssj = getCalulateDate(rq, "start");
        String jssj = getCalulateDate(rq, "end");
        String xmmc = obj.getXmmc();
        Page<ScoreDetail> page = new Page<>(obj.getCurrent(), obj.getSize());
        String orderBy = getUserDept();
        return performance.queryMedicalLabDetail(page, kssj, jssj, orderBy, (xmmc != null ? xmmc.trim() : null));
    }
}
