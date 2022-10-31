package com.wangyuhang.dreamboot;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wangyuhang.dreamboot.entity.User;
import com.wangyuhang.dreamboot.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DreambootApplicationTests {

    @Autowired
    private UserMapper userMapper;
    @Test
    void contextLoads() {


        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name",222);
        User user = userMapper.selectOne(queryWrapper);
        System.out.println(user);
    }

}
