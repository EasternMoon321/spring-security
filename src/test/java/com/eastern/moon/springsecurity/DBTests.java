package com.eastern.moon.springsecurity;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.eastern.moon.springsecurity.entity.MyUser;
import com.eastern.moon.springsecurity.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Slf4j
public class DBTests extends SpringSecurityApplicationTests {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void save() {
        MyUser user = new MyUser();
        user.setUserName("eastern");
        user.setPassword("moon");
        userMapper.insert(user);
    }

    @Test
    public void find() {
        QueryWrapper<MyUser> queryWrapper = new QueryWrapper<>();
        List<MyUser> myUsers = userMapper.selectList(queryWrapper);
        log.info(JSON.toJSONString(myUsers));
    }
}
