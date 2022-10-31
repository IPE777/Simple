package com.wangyuhang.dreamboot.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wangyuhang.dreamboot.common.R;
import com.wangyuhang.dreamboot.entity.User;
import com.wangyuhang.dreamboot.mapper.UserMapper;
import com.wangyuhang.dreamboot.utils.RUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserMapper userMapper;

    @RequestMapping("/hello")
    public String hello(){
        return "hello";
    }

    // 分页查找所有记录
    @GetMapping("/getUser/{start}/{end}")
    public R getUser(@PathVariable("start") Integer start, @PathVariable("end") Integer end){
        Page<User> page = new Page<>(start,end);
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        return RUtils.success(userMapper.selectPage(page,queryWrapper));
    }

    // 根据id查找
    @GetMapping("/findById/{id}")
    public R selectById(@PathVariable("id") Integer id){
        return RUtils.success(userMapper.selectById(id));

    }

    // 根据姓名查找
    @GetMapping("/findByName")
    public R selectByName(@RequestParam(name="name",  required = true) String name){

        User user = new User();
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name",name);

        // 判断是否有输入搜索 没有则返回全部 待完善
        if (!Objects.equals(name, "")){
           user = userMapper.selectOne(queryWrapper);
        }

        User[] arr = new User[]{user};

        return RUtils.success(arr);

    }
    // 添加用户并保存
    @PostMapping("/save")
    public R save(@RequestBody User user){
        int result = userMapper.insert(user);
        if (result >= 1){
            return RUtils.success("success");
        }else {
            return RUtils.Err(400,"err");
        }

    }

    // 更新用户
    @PutMapping("/updateById")
    public R updateById( @RequestBody User user){
        int result = userMapper.updateById(user);
        if (result >= 1){
            return RUtils.success("success");
        }else {
            return RUtils.Err(400,"err");
        }
    }

    // 删除用户
    @GetMapping("/deleteById/{id}")
    public R deleteById(@PathVariable("id") int id){

        return RUtils.success(userMapper.deleteById(id));
    }



}
