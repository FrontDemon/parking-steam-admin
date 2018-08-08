package com.smdm.bean;

import java.util.HashMap;
import java.util.Map;

public class Msg {
	private int code;

    private String msg;

    private Map<String, Object> extend = new HashMap<>();

    public static Msg success(int code, String msg){
        Msg result = new Msg();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }
    
    public static Msg success(String msg){
        Msg result = new Msg();
        result.setCode(1);
        result.setMsg(msg);
        return result;
    }
    
    public static Msg fail(int code, String msg){
        Msg result = new Msg();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }

    public static Msg fail(String msg){
        Msg result = new Msg();
        result.setCode(0);
        result.setMsg(msg);
        return result;
    }

    public Msg add(String key, Object value){
        this.getExtend().put(key, value);
        return this;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Map<String, Object> getExtend() {
        return extend;
    }

    public void setExtend(Map<String, Object> extend) {
        this.extend = extend;
    }
}
