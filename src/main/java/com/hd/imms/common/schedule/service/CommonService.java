package com.hd.imms.common.schedule.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hd.imms.common.schedule.Dao.CommonDao;
import com.hd.imms.common.schedule.Dao.LisDao;
import com.hd.imms.common.utils.EncryptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class CommonService {
    @Autowired
    CommonDao commonDao;
    @Autowired
    LisDao lisDao;
    /**
     * 核酸检查信息上传
     * @date 2021-02-07
     */
    public void hsjcxxsc() throws Exception {
        String key = "1234";
        //查询当天已上传成功单号
        List<Map<String, Object>> list = commonDao.cxhsjcxx();
        if(list != null){
            for(Map<String, Object> map : list){
                JSONObject json = this.convMapToJSON(map);
            }
        }
    }
    /**
     * 发送核酸检查信息
     * @date 2021-02-07
     */
    public static String sendHsjcPost(String url, String query) {
        BufferedReader br = null;
        String result = "";
        StringBuffer buffer = new StringBuffer();
        try{
            URL restURL = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) restURL.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);
            PrintStream ps = new PrintStream(conn.getOutputStream());
            ps.print(query);
            ps.close();
            br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while((line = br.readLine()) != null ){
                buffer.append(line);
            }
            result = buffer.toString();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if(br != null){
                    br.close();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return result;
    }
    /**
     * 把核酸上传结果信息组装成
     */
    private JSONObject convMapToJSON(Map<String, Object> map){
        JSONObject retJSON = new JSONObject();
        retJSON.put("posId", (String)map.get("posId"));
        retJSON.put("detectOrgId", (String)map.get("detectOrgId"));
        retJSON.put("detectOrgName", (String)map.get("detectOrgName"));
        retJSON.put("detectType", (String)map.get("detectType"));
        retJSON.put("certificateType", (String)map.get("certificateType"));
        retJSON.put("idNo", (String)map.get("idNo"));
        retJSON.put("name", (String)map.get("name"));
        retJSON.put("gender", (String)map.get("gender"));
        retJSON.put("birthday", (String)map.get("birthday"));
        retJSON.put("employer", (String)map.get("employer"));
        retJSON.put("telPhone", (String)map.get("telPhone"));
        retJSON.put("ayCloseContact", (String)map.get("ayCloseContact"));
        retJSON.put("address", (String)map.get("address"));
        retJSON.put("detectPersonName", (String)map.get("detectPersonName"));
        retJSON.put("detectTime", (String)map.get("detectTime"));
        retJSON.put("samplingTime", (String)map.get("samplingTime"));
        retJSON.put("samplingPersonName", (String)map.get("samplingPersonName"));
        retJSON.put("reportPersonName", (String)map.get("reportPersonName"));
        retJSON.put("reportTime", (String)map.get("reportTime"));
        retJSON.put("auditPersonName", (String)map.get("auditPersonName"));
        retJSON.put("auditTime", (String)map.get("auditTime"));
        retJSON.put("specimenNumber", (String)map.get("specimenNumber"));
        retJSON.put("remark", (String)map.get("remark"));
        //结果信息
        JSONArray arr = new JSONArray();
        JSONObject resultJSON = new JSONObject();
        resultJSON.put("itemCode", (String)map.get("itemCode"));
        resultJSON.put("itemName", (String)map.get("itemName"));
        resultJSON.put("result", (String)map.get("result"));
        resultJSON.put("unit", (String)map.get("unit"));
        resultJSON.put("limit", (String)map.get("limit"));
        arr.add(resultJSON);
        retJSON.put("resultList", arr);
        /*System.err.println("key: " + entry.getKey()+",  value: "+entry.getValue());
        System.err.println("before encrypt  content: "+content);
        // 加密数据, 返回密文
        byte[] cipherBytes = EncryptionUtils.encrypt(content.getBytes(), key.getBytes());
        System.out.println("encrypt  content"+new String(cipherBytes));
        // 解密数据, 返回明文
        byte[] plainBytes = EncryptionUtils.decrypt(cipherBytes, key.getBytes());
        // 输出解密后的明文
        System.out.println("decryption"+new String(plainBytes));*/
        return retJSON;
    }
}
