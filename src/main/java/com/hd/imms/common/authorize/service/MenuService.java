package com.hd.imms.common.authorize.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.hd.imms.entity.authorize.Menu;
import com.hd.imms.mapper.AuthUser;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
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
        JSONArray childrenMenuArray = new JSONArray();
        try{
            // 查询角色顶级菜单
            List<Menu> menuList = authUser.queryTopMenuByRole(roleId);
            if(menuList != null){
                for(Menu menu : menuList){
                    JSONObject json = assetMenuJSON(menu, roleId);
                    //设置
                    childrenMenuArray.add(json);
                }
            }
        } catch (Exception e){
            log.error("queryMenuByRole err: "+e.getMessage());
        }
        return childrenMenuArray;
    }

    public JSONObject assetMenuJSON(Menu menu, String roleId){
        JSONObject menuJson = new JSONObject();
        JSONArray childrenMenuArray = null;
        //查询当前节点子节点
        List<Menu> childrenMenuList = queryMenuByRole(menu, roleId);
        if(childrenMenuList != null && childrenMenuList.size()>0){
            //当前不是叶子节点，继续深度查询
            childrenMenuArray = new JSONArray();
            for(Menu childrenMenu : childrenMenuList){
                JSONObject menuJson1 = assetMenuJSON(childrenMenu, roleId);
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

    /**
     * 设置路由节点信息
     * @param json
     * @param menu
     * @return
     */
    private JSONObject setMenuJSONAttribute(JSONObject json,Menu menu){
        json.put("path", menu.getPath());
        json.put("component", menu.getComponent());
        json.put("name", menu.getName());
        String redirect = menu.getRedirect();
        if(StringUtils.isNotEmpty(redirect)){
            json.put("redirect", redirect);
        }
        //菜单名称
        JSONObject metaJson = new JSONObject();
        metaJson.put("title", menu.getTitle());
        //路由图标
        String icon = menu.getIcon();
        if(StringUtils.isNotEmpty(icon)){
            metaJson.put("icon", icon);
        }
        json.put("meta", metaJson);
        return json;
    }
    /**
     * 查询当前角色菜单的子菜单
     * @return
     */
    public List<Menu> queryMenuByRole(Menu topMenu, String roleID){
        List<Menu> childrenMenuList = authUser.queryChildrenMenuByRole(roleID, topMenu.getWid());
        return childrenMenuList;
    }
    /**
     * 查询菜单树
     * @return
     */
    public JSONArray queryMenuTree(){
        JSONArray childrenMenuArray = new JSONArray();
        try{
            // 查询角色顶级菜单
            List<Menu> menuList = authUser.queryTopMenu();
            if(menuList != null){
                for(Menu menu : menuList){
                    JSONObject json = assetMenuLeaf(menu);
                    childrenMenuArray.add(json);
                }
            }
        } catch (Exception e){
            log.error("queryMenu err: "+e.getMessage());
        }
        return childrenMenuArray;
    }

    public JSONObject assetMenuLeaf(Menu menu){
        JSONObject menuJson = new JSONObject();
        JSONArray childrenMenuArray = null;
        //查询当前节点子节点
        List<Menu> childrenMenuList = queryChildrenMenuByID(menu);
        if(childrenMenuList != null && childrenMenuList.size()>0){
            //当前不是叶子节点，继续深度查询
            childrenMenuArray = new JSONArray();
            for(Menu childrenMenu : childrenMenuList){
                JSONObject menuJson1 = assetMenuLeaf(childrenMenu);
                childrenMenuArray.add(menuJson1);
            }
            queryChildrenMenuByID(menuJson, menu);
            menuJson.put("children", childrenMenuArray);
        }else{
            //设置
            queryChildrenMenuByID(menuJson, menu);
        }
        return menuJson;
    }
    /**
     * 设置菜单树节点信息
     * @param json
     * @param menu
     * @return
     */
    private JSONObject queryChildrenMenuByID(JSONObject json,Menu menu){
        json.put("id", menu.getWid());
        json.put("label", menu.getTitle());
        return json;
    }
    /**
     * 查询当前菜单的子菜单
     * @return
     */
    public List<Menu> queryChildrenMenuByID(Menu topMenu){
        List<Menu> childrenMenuList = authUser.queryChildrenMenuByID(topMenu.getWid());
        return childrenMenuList;
    }
}
