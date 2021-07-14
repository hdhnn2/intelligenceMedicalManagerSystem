package com.hd.imms.zhjw.code.service;

import com.hd.imms.entity.performance.DeptVsClinic;
import com.hd.imms.entity.zhjw.code.CodeNjb;
import com.hd.imms.mapper.zhjw.CodeManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
@Slf4j
public class CodeManagerService {
    @Autowired
    @Resource
    private CodeManager codeManager;
    /**
     * 功能：查询所有年级
     * @date 2021-06-30
     * @return
     */
    public List<CodeNjb> queryCodeNjbAll(){
        List<CodeNjb> list = codeManager.queryCodeNjbAll();
        return list;
    }
}
