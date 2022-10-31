package com.wangyuhang.dreamboot.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wangyuhang.dreamboot.common.R;
import com.wangyuhang.dreamboot.common.Result;
import com.wangyuhang.dreamboot.entity.User;
import com.wangyuhang.dreamboot.mapper.UserMapper;
import com.wangyuhang.dreamboot.utils.RUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DictController {

    @Autowired
    private UserMapper userMapper;
    @GetMapping("/dict")
    public Result test() throws NoSuchFieldException {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name","王宇杭");
        return Result.ok(userMapper.selectOne(queryWrapper));
    }

    @GetMapping("/getUser2/{start}/{end}")
    public Result getUser(@PathVariable("start") Integer start, @PathVariable("end") Integer end){
        Page<User> page = new Page<>(start,end);
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        return Result.ok(userMapper.selectPage(page,queryWrapper));
    }
}
