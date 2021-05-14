package com.hd.imms.performance.service;

import com.hd.imms.entity.performance.DeptScore;
import com.hd.imms.entity.performance.DeptVsClinic;
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
     * 功能：查询科室得分
     * @date 2021-05-13
     * @return
     */
    public String calDoctorScore(Map<String,Object> params){
        String msg = "";
        String rq = params.get("rq").toString();
        params.put("ksrq", getCalulateDate(rq, "start"));
        params.put("jsrq", getCalulateDate(rq, "end"));
        performance.calDoctorScore(params);
        log.error("_____________________________"+(String)params.get("msg"));
        return msg;
    }
    private String getCalulateDate(String rq, String type){
        Integer year = Integer.parseInt(rq.substring(0,3));
        Integer month = Integer.parseInt(rq.substring(5));
        Calendar cal = Calendar.getInstance();
        if(StringUtils.equals(type, "start")){
            cal.set(month == 1 ? year-1 : year, month-1, 26);
        }else{
            cal.set(year, month, 25);
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(cal.getTime());
    }
}
