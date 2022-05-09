package com.eastern.moon.springsecurity.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ann")
public class AnnController {

    @Secured("ROLE_admin")  // 具有admin角色
    @GetMapping("secured")
    public String secured() {
        return "secured";
    }

//    @PreAuthorize("hasRole('ROLE_admin')")  // 进入方法前，需具有admin角色
    @PreAuthorize("hasAuthority('/ann/preAuthorize')") // 进入方法前，需具有/ann/preAuthorize权限
    @GetMapping("/preAuthorize")
    public String preAuthorize() {
        return "preAuthorize";
    }

    //    @PostAuthorize("hasRole('ROLE_admin')")  // 进入方法后，需具有admin角色
    @PostAuthorize("hasAuthority('/ann/postAuthorize')") // 进入方法后，需具有/ann/postAuthorize权限
    @GetMapping("/postAuthorize")
    public String  postAuthorize() {
        System.out.println("postAuthorize");
        return "postAuthorize";
    }

}
