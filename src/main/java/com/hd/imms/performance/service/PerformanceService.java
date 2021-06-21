package com.hd.imms.performance.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hd.imms.common.utils.SecurityUtils;
import com.hd.imms.entity.common.DepartmentDictionary;
import com.hd.imms.entity.common.SystemParameterBean;
import com.hd.imms.entity.performance.BillDetail;
import com.hd.imms.entity.performance.BillDetailQuery;
import com.hd.imms.entity.performance.DeptScore;
import com.hd.imms.entity.performance.DeptVsClinic;
import com.hd.imms.mapper.CommonMapper;
import com.hd.imms.mapper.Performance;
import com.hd.imms.performance.bean.DeptCoefficient;
import com.hd.imms.performance.bean.ProcedureParameter;
import com.hd.imms.performance.dao.PerformanceDao;
import io.netty.util.internal.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

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

    // 查询科室手术积分
    public List<BillDetail> queryDeptScoreDetailByType(BillDetailQuery obj){
        Map<String,Object> params = new HashMap<String,Object>();
        String rq = obj.getRq();
        params.put("kssj", getCalulateDate(rq, "start"));
        params.put("jssj", getCalulateDate(rq, "end"));
        //当前用户所在科室
        String userDept = getUserDept();
        params.put("orderBy", userDept);
        if(StringUtils.equals(obj.getLx(), "1")){
            return queryDeptOperationScoreDetail(params);
        }
        return null;
    }
    // 查询科室手术积分
    public List<BillDetail> queryDeptOperationScoreDetail(Map<String,Object> params){
        List<BillDetail> list = performance.queryDeptOperationScoreDetail(params);
        return list;
    }
    public IPage<BillDetail> selectPageBillDetail() {

        Page<BillDetail> page = new Page<>(1, 5);
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
        return userDept;
    }
}
