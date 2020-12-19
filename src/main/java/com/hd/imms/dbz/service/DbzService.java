package com.hd.imms.dbz.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class DbzService {

    public static void main(String[] args){
        //接口地址参考：接口说明—>API使用说明—>接口地址
        String url = "http://192.1.8.199:80/interface/010102/Cap-Adult/1.2/put/";
        //上报数据格式参考：接口说明—>API使用说明—>请求参数
        //如{"key1":"value1","key2":"value2","key3":"value3"...}
        String data = "";
        //上报并返回結果
        String result = sendPost(url,data);
        System.out.println(result);
    }
    public static String sendPost(String url, String query) {
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
