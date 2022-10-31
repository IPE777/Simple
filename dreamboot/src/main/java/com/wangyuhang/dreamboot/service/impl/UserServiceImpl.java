package com.wangyuhang.dreamboot.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wangyuhang.dreamboot.entity.User;
import com.wangyuhang.dreamboot.mapper.UserMapper;
import com.wangyuhang.dreamboot.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
