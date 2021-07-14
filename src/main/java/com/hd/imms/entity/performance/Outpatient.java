package com.hd.imms.entity.performance;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

@Data
public class Outpatient {
    @ExcelIgnore
    private String ksdm;
    @ExcelProperty("科室名称")
    private String deptName;
    @ExcelProperty("就诊日期")
    private String jzrq;
    @ExcelProperty("医生工号")
    private String doctor;
    @ExcelProperty("医生姓名")
    private String doctorName;
    @ExcelProperty("住院号")
    private String patientId;
    @ExcelProperty("患者姓名")
    private String name;
}
