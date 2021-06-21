package com.hd.imms.drgs.service;

import com.hd.imms.entity.drgs.Bacyxx;
import com.hd.imms.entity.drgs.DeptDict;
import com.hd.imms.mapper.drgs.Drgs;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
@Slf4j
public class DrgsService {
    @Resource
    @Autowired
    Drgs drgs;
    /**
     * 查询出院病案信息
     * @return
     */
    public List<Bacyxx> cybaxxcx(){
        List<Bacyxx> list = null;
        try{
            list = drgs.cybaxxcx();
        } catch (Exception e){
            log.error("cybaxxcx err: "+e.getMessage());
        }
        return list;
    }
    /**
     * 查询科室信息
     * @return
     */
    public List<DeptDict> queryDeptDic(){
        List<DeptDict> list = null;
        try{
            list = drgs.queryDeptDic();
        } catch (Exception e){
            log.error("queryDeptDic err: "+e.getMessage());
        }
        return list;
    }
}
