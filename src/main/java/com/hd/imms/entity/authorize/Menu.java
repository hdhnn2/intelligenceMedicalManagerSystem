package com.hd.imms.entity.authorize;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

@Data
public class Menu {
    @JSONField(name = "wid")
    private String wid;
    @JSONField(name = "path" , ordinal = 1)
    private String path;
    @JSONField(name = "icon")
    private String icon;
    @JSONField(name = "component", ordinal = 2)
    private String component;
    @JSONField(name = "redirect")
    private String redirect;
    @JSONField(name = "name", ordinal = 3)
    private String name;
    @JSONField(name = "title", ordinal = 4)
    private String title;
    @JSONField(name = "nocache")
    private String nocache;
    @JSONField(name = "parentId")
    private String parentId;

    public Menu(String path) {
        this.path = path;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "wid='" + wid + '\'' +
                ", path='" + path + '\'' +
                ", icon='" + icon + '\'' +
                ", component='" + component + '\'' +
                ", redirect='" + redirect + '\'' +
                ", name='" + name + '\'' +
                ", title='" + title + '\'' +
                ", nocache='" + nocache + '\'' +
                ", parentId='" + parentId + '\'' +
                '}';
    }
}
