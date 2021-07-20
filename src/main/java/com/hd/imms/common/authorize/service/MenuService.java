package com.hd.imms.common.authorize.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.hd.imms.entity.authorize.Menu;
import com.hd.imms.mapper.AuthUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
@Slf4j
public class MenuService {

    @Resource
    @Autowired
    AuthUser authUser;


    /**
     * 查询用户角色
     * @return
     */
    public JSONArray queryMenuByRole(String roleId){
        JSONObject ret = new JSONObject();
        JSONArray arr = new JSONArray();
        try{
            // 查询角色顶级菜单
            List<Menu> menuList = authUser.queryTopMenuByRole(roleId);
            if(menuList != null){
                JSONArray childrenMenuArray = new JSONArray();
                for(Menu menu : menuList){
                    JSONObject menuJson = new JSONObject();
                    JSONObject json = assetMenuJSON(menu);
                    //设置

                    childrenMenuArray.add(json);
                    menuJson.put("children", childrenMenuArray);
                    arr.add(menuJson);
                }
            }
        } catch (Exception e){
            log.error("queryMenuByRole err: "+e.getMessage());
        }
        return arr;
    }

    public JSONObject assetMenuJSON(Menu menu){
        JSONObject menuJson = new JSONObject();
        JSONArray childrenMenuArray = null;
        //查询当前节点子节点
        List<Menu> childrenMenuList = queryMenuByRole(menu);
        if(childrenMenuList != null && childrenMenuList.size()>0){
            //当前不是叶子节点，继续深度查询
            childrenMenuArray = new JSONArray();
            for(Menu childrenMenu : childrenMenuList){
                JSONObject menuJson1 = assetMenuJSON(childrenMenu);
                //
                childrenMenuArray.add(menuJson1);
            }
            //有大问题，递归回来的信息和被调用信息重新赋值了^^^
            setMenuJSONAttribute(menuJson, menu);
            // 设置子菜单
            menuJson.put("children", childrenMenuArray);
        }else{
            //设置
            setMenuJSONAttribute(menuJson, menu);
        }
        return menuJson;
    }
    private JSONObject setMenuJSONAttribute(JSONObject json,Menu menu){
        json.put("path", menu.getPath());
        json.put("component", menu.getComponent());
        json.put("name", menu.getName());
        //菜单名称
        JSONObject metaJson = new JSONObject();
        metaJson.put("title", menu.getTitle());
        json.put("meta", metaJson);
        return json;
    }
    /**
     * 查询当前菜单的子菜单
     * @return
     */
    public List<Menu> queryMenuByRole(Menu topMenu){
        List<Menu> childrenMenuList = authUser.queryChildrenMenuByID(topMenu.getWid());
        return childrenMenuList;
    }
}
