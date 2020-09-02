package com.hd.imms.jwzzgl.entity;

import java.io.Serializable;

public class PatientBaseInfo implements Serializable {
    private String name;
    private Integer gender;
    private Integer ageValue;
    private String patientBirthday;
    private String contactPhone;
    private String cardNo;
    private String familyAddress;

    public PatientBaseInfo() {
    }

    public PatientBaseInfo(String name, Integer gender, Integer ageValue, String patientBirthday, String contactPhone, String cardNo, String familyAddress) {
        this.name = name;
        this.gender = gender;
        this.ageValue = ageValue;
        this.patientBirthday = patientBirthday;
        this.contactPhone = contactPhone;
        this.cardNo = cardNo;
        this.familyAddress = familyAddress;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Integer getAgeValue() {
        return ageValue;
    }

    public void setAgeValue(Integer ageValue) {
        this.ageValue = ageValue;
    }

    public String getPatientBirthday() {
        return patientBirthday;
    }

    public void setPatientBirthday(String patientBirthday) {
        this.patientBirthday = patientBirthday;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getFamilyAddress() {
        return familyAddress;
    }

    public void setFamilyAddress(String familyAddress) {
        this.familyAddress = familyAddress;
    }
}
