package com.hd.imms.common.schedule;

import com.hd.imms.common.schedule.service.CommonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Date;

/**
 * 功能：计划任务类
 * 日期：2021-02-07
 * 作者：hnn
 */
@Configuration
@EnableScheduling
@Slf4j
public class ScheduleJob {
    @Autowired
    CommonService commonService;

    /**
     * 功能：核酸检测
     * 日期：2021-02-07
     * 作者：hnn
     */
    @Scheduled(cron = "0 0/50 * * * ?")
    private void hsjcxxsc(){
        log.info("hsjcxxsc start:"+new Date());
        try {
            commonService.hsjcxxsc();
        } catch (Exception e) {
            log.info("hsjcxxsc err:"+ e.getMessage());
        }
        log.info("hsjcxxsc end:"+new Date().toString());
    }
}
