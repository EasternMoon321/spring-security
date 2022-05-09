package com.eastern.moon.springsecurity.service;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.eastern.moon.springsecurity.entity.MyUser;
import com.eastern.moon.springsecurity.mapper.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class MyUserDetailsService implements UserDetailsService {

    private final Logger logger = LoggerFactory.getLogger(MyUserDetailsService.class);

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (StringUtils.hasLength(username)) {
            QueryWrapper<MyUser> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("user_name", username);
            MyUser myUser = userMapper.selectOne(queryWrapper);
            logger.debug("find user :{}", JSON.toJSON(myUser));
            if (!ObjectUtils.isEmpty(myUser) && StringUtils.hasLength(myUser.getPassword())) {
                // 权限正常写，角色前面需加ROLE_前缀, 多个授权信息逗号分割
                List<GrantedAuthority> authorities = AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_admin, /ann/preAuthorize");
                return new User(myUser.getUserName(), bCryptPasswordEncoder.encode(myUser.getPassword()), authorities);
            } else {
                throw new UsernameNotFoundException("用户名不存在！！");
            }
        } else {
            throw new RuntimeException("用户名不能为空！");
        }
    }
}
