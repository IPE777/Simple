package com.wangyuhang.dreamboot.entity;


import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("sys_dict")
public class SysDict {

    private int id;
    private String name;
    private String descript;
    private int status;
    private Date create_time;
    private String create_name;
    private int delete_flag;
}
