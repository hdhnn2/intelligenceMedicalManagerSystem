package com.hd.imms.entity.authorize;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

@Data
public class Menu {
    @JSONField(name = "wid")
    private String wid;
    @JSONField(name = "path")
    private String path;
    @JSONField(name = "icon")
    private String icon;
    @JSONField(name = "component")
    private String component;
    @JSONField(name = "redirect")
    private String redirect;
    @JSONField(name = "name")
    private String name;
    @JSONField(name = "title")
    private String title;
    @JSONField(name = "nocache")
    private String nocache;
    @JSONField(name = "parentId")
    private String parentId;
    @JSONField(name = "bz")
    private String bz;
}
