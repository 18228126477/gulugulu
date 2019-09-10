package com.xmr.demo.service.domainService.impl;

import com.xmr.demo.dao.UserMapper;
import com.xmr.demo.domain.User;
import com.xmr.demo.service.BaseService;
import com.xmr.demo.service.domainService.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl extends BaseService implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> findAll() {
        return userMapper.findAll();
    }

    @Override
    public User findById(Integer id) {
        return userMapper.findById(id);
    }

    @Override
    public Integer saveUser(User user) {
        return userMapper.saveUser(user);
    }

    @Override
    public Integer deleteUser(Integer id) {
        return userMapper.deleteUser(id);
    }

    @Override
    public User findByUserName(String username) {
        return userMapper.findByUserName(username);
    }
}
