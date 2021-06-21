package com.hd.imms.entity.performance;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
public class BillDetailQuery {
    private String[] jfrq;
    private String jffs;
    private String[] tjlb;
    private String xmmc;
    private String lx;
    private String rq;      //月份
    private String ksmc;      //科室名称
    private Integer current;
    private Integer size;
}
