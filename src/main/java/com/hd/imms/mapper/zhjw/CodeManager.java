package com.hd.imms.mapper.zhjw;



import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hd.imms.entity.performance.*;
import com.hd.imms.entity.zhjw.code.CodeNjb;
import com.hd.imms.entity.zhjw.code.CodeZcb;
import com.hd.imms.entity.zhjw.code.CodeZzmmb;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface CodeManager {
    //年级
    List<CodeNjb> queryCodeNjbAll();
    //政治面貌
    List<CodeZzmmb> queryCodeZzmmAll();
    //职称
    List<CodeZcb> queryCodeZcbAll();
}
