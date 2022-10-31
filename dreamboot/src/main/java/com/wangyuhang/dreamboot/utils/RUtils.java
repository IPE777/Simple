package com.wangyuhang.dreamboot.utils;


import com.wangyuhang.dreamboot.common.R;
import com.wangyuhang.dreamboot.vo.Renum;

public class RUtils {
    /*成功，且返回体有数据*/
    public static R success(Object object) {
        R r = new R();
        r.setCode(Renum.SUCCESS.getCode());
        r.setMsg(Renum.SUCCESS.getMsg());
        r.setData(object);
        return r;
    }
    //成功，但返回体没数据
    public static  R success(){
        return success(null);
    }
    //失败返回信息
    public static R Err(Integer code,String msg){
        R r = new R();
        r.setCode(code);
        r.setMsg(msg);
        return r;
    }
}
