package com.hd.imms.mapper;

import com.hd.imms.entity.common.CodeBean;
import com.hd.imms.entity.common.DepartmentDictionary;
import com.hd.imms.entity.common.SystemParameterBean;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface CommonMapper {
    List<SystemParameterBean> querySystemParameter(SystemParameterBean obj);
    List<DepartmentDictionary> queryDeptDict(DepartmentDictionary obj);
    List<DepartmentDictionary> queryUserDeptById(Map<String, String> map);
    List<CodeBean> queryAllBillItemClass();
}
