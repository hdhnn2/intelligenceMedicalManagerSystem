package com.hd.imms.zhjw.xjgl.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hd.imms.entity.zhjw.xjgl.XjglQuery;
import com.hd.imms.entity.zhjw.xjgl.Xsjbxx;
import com.hd.imms.mapper.zhjw.xjgl.XsjbxxMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Slf4j
public class XjglService {
    @Autowired
    @Resource
    private XsjbxxMapper xsjbxxMapper;
    /**
     * 查询学生信息
     * @Date 2021-07-01
     * @return
     */
    public IPage<Xsjbxx> queryXjxxByPage(XjglQuery params) {
        Page<Xsjbxx> page = new Page<Xsjbxx>(params.getCurrent(), params.getSize());
        IPage<Xsjbxx> userIPage = xsjbxxMapper.queryXjxxByPage(page);
        return userIPage;
    }
}
