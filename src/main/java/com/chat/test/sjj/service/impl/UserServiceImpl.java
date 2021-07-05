package com.chat.test.sjj.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.chat.test.sjj.dto.UserDto;
import com.chat.test.sjj.entity.User;
import com.chat.test.sjj.mapper.UserMapper;
import com.chat.test.sjj.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;


/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jingjie.shen
 * @since 2021-07-02
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Override
    public boolean register(UserDto user) throws Exception {
        if(StringUtils.isAnyBlank(user.getName(),
                user.getPassword(),
                user.getSex())){
            throw new RuntimeException("姓名,密码,性别不能为空!");
        }
        QueryWrapper query=new QueryWrapper();
        query.eq("name",user.getName());
        if(!CollectionUtils.isEmpty(this.baseMapper.selectList(query))){
            throw new RuntimeException("用户名已存在,请重新命名!");
        }
        User entity=new User();
        BeanUtils.copyProperties(user,entity);
        this.baseMapper.insert(entity);
        return Boolean.TRUE;
    }

    @Override
    public boolean login(UserDto user) throws Exception {
        if(StringUtils.isAnyBlank(user.getName(),user.getPassword())){
            throw new RuntimeException("用户名/密码不能为空");
        }
        QueryWrapper query=new QueryWrapper();
        query.eq("name",user.getName());
        if(CollectionUtils.isEmpty(this.baseMapper.selectList(query))){
            throw new RuntimeException("用户名不存在");
        }
        query.eq("password",user.getPassword());
        if(CollectionUtils.isEmpty(this.baseMapper.selectList(query))){
            throw new RuntimeException("密码不正确");
        }
        return Boolean.TRUE;
    }


}
