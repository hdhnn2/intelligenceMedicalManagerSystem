package com.hd.imms.performance.bean;

/**
 * 科室系数
 */
public class DeptCoefficient {
    private String ksdm;    //科室代码
    private String lx;      //类型1医生2护士3医技
    private Float mzlfz;    //门诊量分值
    private Float zyrrfz;   //在院人日分值
    private Float fhxfz;   //非核心分值
    private Float hxjf;     //核心分值
    private String hsksdm;   //核算科室代码
    private Float cyrcfz99;     //护士出院分值
    private Float hlczfz99;     //护理操作分值
    private Float zyrrfz99;     //护理在院人日分值
    private Float zlssfz;
    private Float ryrsfz;
    private Float ssfz;         //手术分值
    private Float kfxmfz;       //康复项目分值
    private String ksmc;        //科室名称
    private String hsksmc;      //核算科室名称

    public String getKsdm() {
        return ksdm;
    }

    public void setKsdm(String ksdm) {
        this.ksdm = ksdm;
    }

    public String getLx() {
        return lx;
    }

    public void setLx(String lx) {
        this.lx = lx;
    }

    public Float getMzlfz() {
        return mzlfz;
    }

    public void setMzlfz(Float mzlfz) {
        this.mzlfz = mzlfz;
    }

    public Float getZyrrfz() {
        return zyrrfz;
    }

    public void setZyrrfz(Float zyrrfz) {
        this.zyrrfz = zyrrfz;
    }

    public Float getFhxfz() {
        return fhxfz;
    }

    public void setFhxfz(Float fhxfz) {
        this.fhxfz = fhxfz;
    }

    public Float getHxjf() {
        return hxjf;
    }

    public void setHxjf(Float hxjf) {
        this.hxjf = hxjf;
    }

    public String getHsksdm() {
        return hsksdm;
    }

    public void setHsksdm(String hsksdm) {
        this.hsksdm = hsksdm;
    }

    public Float getCyrcfz99() {
        return cyrcfz99;
    }

    public void setCyrcfz99(Float cyrcfz99) {
        this.cyrcfz99 = cyrcfz99;
    }

    public Float getHlczfz99() {
        return hlczfz99;
    }

    public void setHlczfz99(Float hlczfz99) {
        this.hlczfz99 = hlczfz99;
    }

    public Float getZyrrfz99() {
        return zyrrfz99;
    }

    public void setZyrrfz99(Float zyrrfz99) {
        this.zyrrfz99 = zyrrfz99;
    }

    public Float getZlssfz() {
        return zlssfz;
    }

    public void setZlssfz(Float zlssfz) {
        this.zlssfz = zlssfz;
    }

    public Float getRyrsfz() {
        return ryrsfz;
    }

    public void setRyrsfz(Float ryrsfz) {
        this.ryrsfz = ryrsfz;
    }

    public Float getSsfz() {
        return ssfz;
    }

    public void setSsfz(Float ssfz) {
        this.ssfz = ssfz;
    }

    public Float getKfxmfz() {
        return kfxmfz;
    }

    public void setKfxmfz(Float kfxmfz) {
        this.kfxmfz = kfxmfz;
    }

    public String getKsmc() {
        return ksmc;
    }

    public void setKsmc(String ksmc) {
        this.ksmc = ksmc;
    }

    public String getHsksmc() {
        return hsksmc;
    }

    public void setHsksmc(String hsksmc) {
        this.hsksmc = hsksmc;
    }
}
