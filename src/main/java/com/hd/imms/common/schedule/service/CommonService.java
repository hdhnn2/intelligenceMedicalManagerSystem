package com.hd.imms.common.schedule.service;

import com.hd.imms.common.schedule.Dao.CommonDao;
import com.hd.imms.common.utils.EncryptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;

@Service
public class CommonService {
    @Autowired
    CommonDao commonDao;

    /**
     * 核酸检查信息上传
     * @date 2021-02-07
     */
    public void hsjcxxsc() throws Exception {
        String key = "1234";
        List<Map<String, Object>> list = commonDao.cxhsjcxx();
        if(list != null){
            for(Map<String, Object> map : list){
                for(Map.Entry<String, Object> entry : map.entrySet()){
                    String content = entry.getValue().toString();
                    System.err.println("key: " + entry.getKey()+",  value: "+entry.getValue());
                    System.err.println("before encrypt  content: "+content);
                    // 加密数据, 返回密文
                    byte[] cipherBytes = EncryptionUtils.encrypt(content.getBytes(), key.getBytes());
                    // 解密数据, 返回明文
                    byte[] plainBytes = EncryptionUtils.decrypt(cipherBytes, key.getBytes());
                    // 输出解密后的明文
                    System.out.println("decryption"+new String(plainBytes));
                }

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
}
