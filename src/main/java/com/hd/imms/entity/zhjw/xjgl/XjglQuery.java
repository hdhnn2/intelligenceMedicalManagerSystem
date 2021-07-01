package com.hd.imms.entity.zhjw.xjgl;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

@Data
public class XjglQuery {
    private String xm;      //姓名
    private String csrq;      //出生日期
    private Integer current;
    private Integer size;
}
