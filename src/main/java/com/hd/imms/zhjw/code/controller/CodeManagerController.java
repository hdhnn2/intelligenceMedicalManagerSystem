package com.hd.imms.zhjw.code.controller;

import com.alibaba.fastjson.JSONObject;
import com.hd.imms.entity.zhjw.code.CodeNjb;
import com.hd.imms.performance.bean.DeptCoefficient;
import com.hd.imms.zhjw.code.service.CodeManagerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/zhjw/code")
@Slf4j
public class CodeManagerController {
    @Autowired
    private CodeManagerService codeManagerService;
    /**
     * 查询类型所有科室系数
     */
    @GetMapping(value = "/queryCodeNjbAll")
    public JSONObject queryCodeNjbAll(HttpServletRequest request) {
        log.error("queryCodeNjbAll: ");
        List<CodeNjb> list = codeManagerService.queryCodeNjbAll();
        JSONObject retJSON = new JSONObject();
        retJSON.put("code", 200);
        retJSON.put("data", list);
        return retJSON;
    }
}
