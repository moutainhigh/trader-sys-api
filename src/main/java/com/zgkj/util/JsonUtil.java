package com.zgkj.util;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtil {
    /**
     * 001.json转换成对象
     * @param:传入对象，json字符串
     * @return:Object
     */
    public static Object jsonToObj(Object obj,String jsonStr){
        try {
            ObjectMapper mapper = new ObjectMapper();
            obj = mapper.readValue(jsonStr, obj.getClass());
        }catch (Exception e){
            return "Json转换错误!";
        }
        return obj;
    }
    /**
     * 002.对象转换成json
     * @param:传入对象
     * @return:json字符串
     */
    public static String objToJson(Object obj){
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(obj);
        }catch (Exception e){
            return "Json转换错误";
        }
    }


}
