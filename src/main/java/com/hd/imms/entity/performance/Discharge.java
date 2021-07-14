package com.hd.imms.entity.performance;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

@Data
public class Discharge {

    @ExcelProperty("科室名称")
    private String deptName;
    @ExcelProperty("住院号")
    private String patientId;
    @ExcelProperty("患者姓名")
    private String name;
    @ExcelProperty("住院次数")
    private String visitId;
    @ExcelProperty("性别")
    private String sex;
    @ExcelProperty("出院时间")
    private String cysj;
}
