package com.wangyuhang.dreamboot.entity;


import com.baomidou.mybatisplus.annotation.TableName;
import com.wangyuhang.dreamboot.common.Dict;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("user")
public class User {

    private int id;
    private String name;
    private int age;

    @Dict(dictCode = "sex")
    private int sex;
    private String image;
}
