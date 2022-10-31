package com.wangyuhang.dreamboot.common;



import lombok.Data;

import java.io.Serializable;

@Data
public class R<T> {
    /*返回体*/
    private  Integer code;
    private String msg;
    private T data;


}
