package com.eastern.moon.springsecurity.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;


@Data
@TableName("tb_user")
public class MyUser {
    @TableId(type = IdType.ASSIGN_UUID)
    private String id;
    private String userName;
    private String password;

    // 和上面冲突，导致无法映射
//    public static String ID = "id";
//    public static String USER_NAME = "user_name";
//    public static String PASSWORD = "password";
}
