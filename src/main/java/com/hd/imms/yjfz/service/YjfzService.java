package com.hd.imms.yjfz.service;

import com.alibaba.fastjson.JSONObject;
import com.hd.imms.yjfz.bean.Yjfz;
import com.hd.imms.yjfz.dao.YjfzDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class YjfzService {
    @Autowired
    private YjfzDao dao;
    /**
     * 功能：保存预检分诊
     * @date 2021-03-18
     * @return
     */
    public String saveYjfz(Yjfz obj){
        return dao.saveYjfz(obj);
    }

    /**
     * 复诊报道
     * @param patientId
     * @return
     */
    public JSONObject fzbd(String patientId){
        return dao.fzbd(patientId);
    }
}
