package com.xmr.demo.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
public class BaseController {

    private String SIGN = "sign";
    private String SUCCESS = "success";
    private String DATA = "data";

    String doSuccessMsg(){
        Map<String,Object> result = new HashMap<>();
        result.put(SIGN,SUCCESS);
        return JSONObject.toJSONString(result);
    }

    String doSuccess(Object data){
        Map<String,Object> result = new HashMap<>();
        result.put(SIGN,SUCCESS);
        result.put(DATA,data);
        return JSONObject.toJSONString(result);
    }

    public Date getNowDate(){
        return new Date();
    }

    public void upload(MultipartFile myFiles, HttpServletRequest request, String path){
        if(myFiles != null){
            if(!myFiles.isEmpty()){
                String fileName = myFiles.getOriginalFilename();
                assert fileName != null;
                String storeName = path + "_" + System.currentTimeMillis() + fileName.substring(fileName.lastIndexOf("."));

            }
        }
    }
}
