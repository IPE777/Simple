package com.wangyuhang.dreamboot.entity;


import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("sys_dict_detail")
public class SysDictDetail {

    private int id;
    private String name;
    private String code;
    private String dict_code;

}
