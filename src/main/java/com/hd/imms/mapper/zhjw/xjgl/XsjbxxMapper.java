package com.hd.imms.mapper.zhjw.xjgl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hd.imms.entity.performance.BillDetail;
import com.hd.imms.entity.zhjw.xjgl.Xsjbxx;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface XsjbxxMapper {
    int deleteByPrimaryKey(String xh);

    int insert(Xsjbxx record);

    int insertSelective(Xsjbxx record);

    Xsjbxx selectByPrimaryKey(String xh);

    int updateByPrimaryKeySelective(Xsjbxx record);

    int updateByPrimaryKey(Xsjbxx record);

    IPage<Xsjbxx> queryXjxxByPage(@Param("page") Page page);
}
