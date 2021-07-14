package com.hd.imms.entity.performance;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

@Data
public class ScoreDetail {
    @ExcelIgnore
    private String ksdm;
    @ExcelProperty("科室名称")
    private String deptName;
    @ExcelProperty("类型")
    private String lx;      //住院zy门诊mz
    @ExcelIgnore
    private String itemClass;
    @ExcelProperty("项目类别名称")
    private String className;

    @ExcelProperty("项目代码")
    private String itemCode;
    @ExcelProperty("项目名称")
    private String itemName;
    @ExcelProperty("总数量")
    private Float sl;
    @ExcelProperty("总积分")
    private Float zfz;
    @ExcelProperty("积分")
    private Float jf;

}
