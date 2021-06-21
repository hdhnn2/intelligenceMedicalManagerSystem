package com.hd.imms.mapper.drgs;

import com.hd.imms.entity.drgs.Bacyxx;
import com.hd.imms.entity.drgs.DeptDict;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface Drgs {
    /**
     * 查询患者信息
     * @return
     */
    List<Bacyxx> cybaxxcx();
    /**
     * 查询科室信息
     *
     */
    List<DeptDict> queryDeptDic();
}
