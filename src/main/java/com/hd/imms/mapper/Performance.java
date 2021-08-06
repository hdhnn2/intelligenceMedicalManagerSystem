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
    List<DeptVsClinic> queryDeptVsClinicList(Map<String, Object> params);
    List<DeptScore> queryDeptScore(DeptScore obj);
    Map<String, Object> calDoctorScore(Map<String, Object> params);
    Map<String, Object> calNurseScore(Map<String, Object> params);
    Map<String, Object> calMedLabScore(Map<String, Object> params);
    Map<String, Object> calDoctorScoreDetail(Map<String, Object> params);
    IPage<BillDetail> selectPageBillDetail(@Param("page") Page page, @Param("kssj")String kssj,
                                           @Param("jssj")String jssj,@Param("xmmc")String xmmc,
             @Param("ksdms")String[] ksdms,@Param("xmlb")String[] xmlb);
    IPage<BillDetail> selectPageBillDetailByPerform(@Param("page") Page page, @Param("kssj")String kssj,
                                           @Param("jssj")String jssj,@Param("xmmc")String xmmc,
                                           @Param("ksdms")String[] ksdms,@Param("xmlb")String[] xmlb);
    List<BillDetail> selectPageBillDetail(Map<String, Object> params);
    //查询住院开单费用
    List<BillDetail> queryInpBillByOrder(Map<String, Object> params);
    List<BillDetail> queryInpBillByPerformBy(Map<String, Object> params);
    List<BillDetail> queryOutpBillByOrder(Map<String, Object> params);
    List<BillDetail> queryOutpByPerformBy(Map<String, Object> params);

    // 查询科室手术积分
    IPage<BillDetail> queryDeptOperationScoreByPage(Page page, @Param("kssj")String kssj, @Param("jssj")String jssj,@Param("orderBy")String ksmc,@Param("xmmc")String xmmc);
    IPage<BillDetail> queryDeptLabDetail(Page page, @Param("kssj")String kssj, @Param("jssj")String jssj,@Param("orderBy")String ksmc,@Param("xmmc")String xmmc);
    //IPage<BillDetail> queryDeptLabDetail(Page page, Map<String, Object> params);
    IPage<BillDetail> queryDeptTreatDetail(Page page, @Param("kssj")String kssj, @Param("jssj")String jssj,@Param("orderBy")String ksmc,@Param("xmmc")String xmmc);
    IPage<BillDetail> queryDeptExecuteDetail(Page page, @Param("kssj")String kssj, @Param("jssj")String jssj,@Param("orderBy")String ksmc,@Param("xmmc")String xmmc);
    IPage<BillDetail> queryDeptRecoveryDetail(Page page, @Param("kssj")String kssj, @Param("jssj")String jssj,@Param("orderBy")String ksmc,@Param("xmmc")String xmmc);
    IPage<BillDetail> queryDoctorScoreByPage(Page page, @Param("rq") String rq, @Param("ksmc") String ksmc);
    // 科室明细
    List<ScoreDetail> queryDeptOperationDetail(Map<String, Object> params);
    List<ScoreDetail> queryDeptLabDetail(Map<String, Object> params);
    List<ScoreDetail> queryDeptTreatDetail(Map<String, Object> params);
    List<ScoreDetail> queryDeptExecuteDetail(Map<String, Object> params);
    List<ScoreDetail> queryDeptRecoveryDetail(Map<String, Object> params);
    //护士
    List<Discharge> queryDischargeDetail(Map<String, Object> params);
    List<ScoreDetail> queryNurseCareDetail(Map<String, Object> params);
    IPage<Discharge> queryDischargeDetail(Page page, @Param("kssj")String kssj, @Param("jssj")String jssj,@Param("bqdm")String bqdm);
    IPage<ScoreDetail> queryNurseCareDetail(Page page, @Param("kssj")String kssj, @Param("jssj")String jssj,@Param("bqdm")String bqdm,@Param("xmmc")String xmmc);
    //医技
    List<ScoreDetail> queryMedicalLabDetail(Map<String, Object> params);
    IPage<ScoreDetail> queryMedicalLabDetail(Page page, @Param("kssj")String kssj, @Param("jssj")String jssj,@Param("ksdm")String ksdm,@Param("xmmc")String xmmc);
    // 医生
    List<Outpatient> queryDoctorOutpDetail(Map<String, Object> params);
    List<DoctScore> queryDoctorOperDetail(Map<String, Object> params);
    List<DoctScore> queryDoctorLabDetail(Map<String, Object> params);
    List<DoctScore> queryDoctorTreatDetail(Map<String, Object> params);
    List<DoctScore> queryDoctorExecuteDetail(Map<String, Object> params);
    List<DoctScore> queryDoctorRecoveryDetail(Map<String, Object> params);
}
