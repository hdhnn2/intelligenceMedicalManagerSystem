package com.hd.imms.entity.performance;

import lombok.Data;

@Data
public class DeptScore {
    private String wid;
    private String ksdm;
    private String rq;
    private String lx;
    private Float mzlfz;
    private Float mzljf;
    private Float mzljj;
    private Float zyrrfz;
    private Float zyrrjf;
    private Float zyrrjj;
    private Float cyrcfz;
    private Float cyrcjf;
    private Float cyrcjj;
    private Float fhxfz;
    private Float fhxjf;
    private Float fhxjj;
    private Float hxfz;
    private Float hxjf;
    private Float hxjj;
    private Float ssfz;
    private Float ssjf;
    private Float ssjj;
    private Float kfxmfz;
    private Float kfxmjf;
    private Float kfxmjj;
    private Float qtxmjf;
    private String czr;
    private String czsj;
    private String ksmc;
    private Integer sffb;
    private String name;   //医生姓名
    private String zgh;

    @Override
    public String toString() {
        return "DeptScore{" +
                "ksdm='" + ksdm + '\'' +
                ", rq='" + rq + '\'' +
                ", lx='" + lx + '\'' +
                ", mzlfz=" + mzlfz +
                ", mzljf=" + mzljf +
                ", mzljj=" + mzljj +
                ", zyrrfz=" + zyrrfz +
                ", zyrrjf=" + zyrrjf +
                ", zyrrjj=" + zyrrjj +
                ", cyrcfz=" + cyrcfz +
                ", cyrcjf=" + cyrcjf +
                ", cyrcjj=" + cyrcjj +
                ", fhxfz=" + fhxfz +
                ", fhxjf=" + fhxjf +
                ", fhxjj=" + fhxjj +
                ", hxfz=" + hxfz +
                ", hxjf=" + hxjf +
                ", hxjj=" + hxjj +
                ", ssfz=" + ssfz +
                ", ssjf=" + ssjf +
                ", ssjj=" + ssjj +
                ", kfxmfz=" + kfxmfz +
                ", kfxmjf=" + kfxmjf +
                ", kfxmjj=" + kfxmjj +
                ", qtxmjf=" + qtxmjf +
                ", czr='" + czr + '\'' +
                ", czsj='" + czsj + '\'' +
                ", ksmc='" + ksmc + '\'' +
                ", sffb=" + sffb +
                '}';
    }
}
