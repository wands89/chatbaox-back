package com.chat.test.sjj.service;

import com.chat.test.sjj.dto.UserDto;
import com.chat.test.sjj.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jingjie.shen
 * @since 2021-07-02
 */
public interface IUserService extends IService<User> {

    boolean register(UserDto user) throws Exception;

    boolean login(UserDto user) throws Exception;
}
