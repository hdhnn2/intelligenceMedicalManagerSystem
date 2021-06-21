package com.hd.imms.entity.performance;

import lombok.Data;

@Data
public class DeptVsClinic {
    private String ksdm;
    private String ksmc;
    private String mzdm;
    private String mzmc;
    private String tybz;
    private String tyrq;

    public String getKsdm() {
        return ksdm;
    }

    public void setKsdm(String ksdm) {
        this.ksdm = ksdm;
    }

    public String getKsmc() {
        return ksmc;
    }

    public void setKsmc(String ksmc) {
        this.ksmc = ksmc;
    }

    public String getMzdm() {
        return mzdm;
    }

    public void setMzdm(String mzdm) {
        this.mzdm = mzdm;
    }

    public String getMzmc() {
        return mzmc;
    }

    public void setMzmc(String mzmc) {
        this.mzmc = mzmc;
    }

    public String getTybz() {
        return tybz;
    }

    public void setTybz(String tybz) {
        this.tybz = tybz;
    }

    public String getTyrq() {
        return tyrq;
    }

    public void setTyrq(String tyrq) {
        this.tyrq = tyrq;
    }
}
