package com.hd.imms.jwzzgl.entity;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PatientBaseInfoRowMap implements RowMapper<PatientBaseInfo> {
    @Override
    public PatientBaseInfo mapRow(ResultSet rs, int i) throws SQLException {
        PatientBaseInfo patientBaseInfo = new PatientBaseInfo();
        patientBaseInfo.setName(rs.getString("NAME"));
        patientBaseInfo.setAgeValue(rs.getInt("AGEVALUE"));
        patientBaseInfo.setCardNo(rs.getString("CARD_NO"));
        patientBaseInfo.setContactPhone(rs.getString("CONTACT_PHONE"));
        patientBaseInfo.setFamilyAddress(rs.getString("FAMILY_ADDRESS"));
        patientBaseInfo.setGender(rs.getInt("GENDER"));
        patientBaseInfo.setPatientBirthday(rs.getString("PATIENT_BIRTHDAY"));
        return patientBaseInfo;
    }
}
