package com.wangyuhang.dreamboot.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wangyuhang.dreamboot.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}
