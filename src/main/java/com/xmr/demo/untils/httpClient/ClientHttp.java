package com.xmr.demo.untils.httpClient;

import com.xmr.demo.untils.mail.MailSend;
import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class ClientHttp {
    public static void main(String[] args){
        new Timer("client").schedule(new TimerTask() {
            @Override
            public void run() {
                // 获得Http客户端(可以理解为:你得先有一个浏览器;注意:实际上HttpClient与浏览器是不一样的)
                CloseableHttpClient httpClient = HttpClientBuilder.create().build();
                // 创建Get请求
                HttpPost httpGet = new HttpPost("https://hq.uestc.edu.cn/web/");
                // 响应模型
                CloseableHttpResponse response = null;
                // 由客户端执行(发送)Get请求
                try {
                    response = httpClient.execute(httpGet);
                    // 从响应模型中获取响应实体
                    HttpEntity responseEntity = response.getEntity();
                    if (!(200 == response.getStatusLine().getStatusCode())) {
                        System.err.println(response.getStatusLine().getStatusCode());
                        MailSend.send(response.getStatusLine().getStatusCode());
                    }else{
                        System.out.println(response.getStatusLine().getStatusCode());
                    }
                } catch (ParseException | IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        // 释放资源
                        if (httpClient != null) {
                            httpClient.close();
                        }
                        if (response != null) {
                            response.close();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, 0, 300000);
    }
}