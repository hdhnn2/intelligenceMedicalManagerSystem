package com.hd.imms.performance.bean;

import lombok.Data;

@Data
public class ProcedureParameter {
    private String month;
    private String czr;

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getCzr() {
        return czr;
    }

    public void setCzr(String czr) {
        this.czr = czr;
    }
}
