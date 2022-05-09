package com.eastern.moon.springsecurity.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.eastern.moon.springsecurity.entity.MyUser;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<MyUser> {
}
