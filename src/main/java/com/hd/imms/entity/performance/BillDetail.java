package com.hd.imms.entity.performance;

import lombok.Data;

@Data
public class BillDetail {
    private String itemClass;
    private String className;
    private String itemCode;
    private String itemName;
    private Double amount;
    private String units;
    private Double jf;
    private Double zjf;
    private String billingDateTime;
    private String orderedBy;
    private String performedBy;
    private String orderedByDeptName;
    private String performedByDeptName;
    private String patientId;
    private String ksmc;
    private String jfrq;
    private String hsxmdm;
    private String hsxmmc;
    private Double fy;;
    private String lx;      //类型
}
