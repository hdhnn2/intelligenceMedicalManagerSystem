package com.hd.imms.entity.drgs;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Bacyxx {
    private String bah;     //病案号
    private String xm;      //姓名
    private String xb;      //性别
    private String nl;      //年龄
    private String rysj;    //
    private String cysj;    //
    private String cyksdm;  //出院科室代码
    private String cyksmc;
    private Integer zyts;   //住院天数
    private BigDecimal zyzfy;   //住院总费用
    private BigDecimal ypfy;    //药品费用

    public String getBah() {
        return bah;
    }

    public void setBah(String bah) {
        this.bah = bah;
    }

    public String getXm() {
        return xm;
    }

    public void setXm(String xm) {
        this.xm = xm;
    }

    public String getXb() {
        return xb;
    }

    public void setXb(String xb) {
        this.xb = xb;
    }

    public String getNl() {
        return nl;
    }

    public void setNl(String nl) {
        this.nl = nl;
    }

    public String getRysj() {
        return rysj;
    }

    public void setRysj(String rysj) {
        this.rysj = rysj;
    }

    public String getCysj() {
        return cysj;
    }

    public void setCysj(String cysj) {
        this.cysj = cysj;
    }

    public String getCyksdm() {
        return cyksdm;
    }

    public void setCyksdm(String cyksdm) {
        this.cyksdm = cyksdm;
    }

    public String getCyksmc() {
        return cyksmc;
    }

    public void setCyksmc(String cyksmc) {
        this.cyksmc = cyksmc;
    }

    public Integer getZyts() {
        return zyts;
    }

    public void setZyts(Integer zyts) {
        this.zyts = zyts;
    }

    public BigDecimal getZyzfy() {
        return zyzfy;
    }

    public void setZyzfy(BigDecimal zyzfy) {
        this.zyzfy = zyzfy;
    }

    public BigDecimal getYpfy() {
        return ypfy;
    }

    public void setYpfy(BigDecimal ypfy) {
        this.ypfy = ypfy;
    }
}
