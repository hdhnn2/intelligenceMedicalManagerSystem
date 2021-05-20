package com.hd.imms.performance.service;

import com.alibaba.fastjson.JSONObject;
import com.hd.imms.common.utils.Page;
import com.hd.imms.entity.common.DepartmentDictionary;
import com.hd.imms.entity.common.SystemParameterBean;
import com.hd.imms.entity.performance.BillDetail;
import com.hd.imms.entity.performance.DeptScore;
import com.hd.imms.entity.performance.DeptVsClinic;
import com.hd.imms.mapper.ds1.CommonMapper;
import com.hd.imms.mapper.ds1.Performance;
import com.hd.imms.performance.bean.DeptCoefficient;
import com.hd.imms.performance.bean.ProcedureParameter;
import com.hd.imms.performance.dao.PerformanceDao;
import io.netty.util.internal.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    private String getCalulateDate(String rq, String type){
        Integer year = Integer.parseInt(rq.substring(0,4));
        Integer month = Integer.parseInt(rq.substring(5));
        Calendar cal = Calendar.getInstance();
        if(StringUtils.equals(type, "start")){
            cal.set(month == 1 ? year-1 : year, month-2, 26);
        }else{
            cal.set(year, month-1, 25);
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(cal.getTime());
    }
    public List<BillDetail> selectPageBillDetail() {

/*        Page<BillDetail> page = new Page<>(1, 5);
        page.setOptimizeCountSql(false);
        List<BillDetail> userIPage = performance.selectPageBillDetail(page);*/
        Page page = new Page();
        page.setShowCount(1);
        page.setCurrentResult(3);
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("zyh","00496339");
        List<BillDetail> orders = performance.selectPageBillDetail(page, "00496339");

        return orders;
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
}
