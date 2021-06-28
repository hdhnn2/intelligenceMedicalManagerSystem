package com.hd.imms.mapper;



import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hd.imms.entity.performance.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface Performance {
    List<DeptVsClinic> queryDeptVsClinicList();
    List<DeptScore> queryDeptScore(DeptScore obj);
    Map<String, Object> calDoctorScore(Map<String, Object> params);
    Map<String, Object> calNurseScore(Map<String, Object> params);
    Map<String, Object> calMedLabScore(Map<String, Object> params);
    Map<String, Object> calDoctorScoreDetail(Map<String, Object> params);
    List<BillDetail> selectPageBillDetail(@Param("page") Page page, @Param("zyh") String zyh);
    List<BillDetail> selectPageBillDetail(Map<String, Object> params);
    //查询住院开单费用
    List<BillDetail> queryInpBillByOrder(Map<String, Object> params);
    List<BillDetail> queryInpBillByPerformBy(Map<String, Object> params);
    List<BillDetail> queryOutpBillByOrder(Map<String, Object> params);
    List<BillDetail> queryOutpByPerformBy(Map<String, Object> params);

    IPage<BillDetail> selectPageBillDetail1(@Param("page") Page page);

    // 查询科室手术积分
    List<BillDetail> queryDeptOperationScoreDetail(Map<String, Object> params);
    IPage<BillDetail> queryDoctorScoreByPage(Page page, @Param("rq") String rq, @Param("ksmc") String ksmc);
    // 科室明细
    List<ScoreDetail> queryDeptOperationDetail(Map<String, Object> params);
    List<ScoreDetail> queryDeptLabDetail(Map<String, Object> params);
    List<ScoreDetail> queryDeptTreatDetail(Map<String, Object> params);
    List<ScoreDetail> queryDeptExecuteDetail(Map<String, Object> params);
    List<ScoreDetail> queryDeptRecoveryDetail(Map<String, Object> params);
    //护士
    List<ScoreDetail> queryDischargeDetail(Map<String, Object> params);
    List<ScoreDetail> queryNurseCareDetail(Map<String, Object> params);
}
