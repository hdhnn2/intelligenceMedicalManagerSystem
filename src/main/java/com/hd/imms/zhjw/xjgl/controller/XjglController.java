package com.hd.imms.zhjw.xjgl.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hd.imms.entity.performance.BillDetail;
import com.hd.imms.entity.performance.BillDetailQuery;
import com.hd.imms.entity.zhjw.xjgl.XjglQuery;
import com.hd.imms.entity.zhjw.xjgl.Xsjbxx;
import com.hd.imms.zhjw.xjgl.service.XjglService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/zhjw")
@Slf4j
public class XjglController {
    @Autowired
    private XjglService xjglService;
    /**
     * 查询费用明细
     */
    @PostMapping(value = "/xjgl/queryXjxxByPage")
    public JSONObject queryXjxxByPage(@RequestBody XjglQuery obj, HttpServletRequest request) {
        log.error("queryBillDetail: "+obj.toString());
        IPage<Xsjbxx> page = xjglService.queryXjxxByPage(obj);
        //JSONArray ret = (JSONArray) JSON.toJSON(page.getRecords());
        JSONObject retJSON = new JSONObject();
        retJSON.put("code", 200);
        retJSON.put("data", page);
        return retJSON;
    }
}
