package com.chat.test.sjj.controller;


import com.chat.test.sjj.common.JsonResult;
import com.chat.test.sjj.dto.UserDto;
import com.chat.test.sjj.entity.User;
import com.chat.test.sjj.service.IUserService;
import com.chat.test.sjj.service.impl.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author jingjie.shen
 * @since 2021-07-02
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    IUserService userService;

    /**
     * 登录
     */
    @PostMapping("/login")
    public JsonResult login(@RequestBody UserDto user){
        try{
            boolean flag=userService.login(user);
            if(flag){
                return  JsonResult.success("登录成功");
            }else {
                return JsonResult.fail("登录失败");
            }
        }catch (Exception e){
            e.printStackTrace();
            return JsonResult.fail("登录失败,原因"+e.getLocalizedMessage());
        }
    }

    /**
     * 注册
     */
    @PostMapping("/register")
    public JsonResult registerUser(@RequestBody UserDto user){
        try{
            boolean flag=userService.register(user);
           if(flag){
               return  JsonResult.success("注册成功");
           }else {
               return JsonResult.fail("注册失败");
           }
        }catch (Exception e){
            e.printStackTrace();
            return JsonResult.fail("注册失败,原因"+e.getLocalizedMessage());
        }
    }
}
