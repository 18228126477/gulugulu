package com.xmr.demo.service.domainService;

import com.xmr.demo.domain.User;

import java.util.List;

public interface UserService {

    /**
     * 获取人物信息
     * @return Character
     */
    List<User> findAll();

    /**
     * 根据人物 ID，获取人物信息
     * @param id
     * @return Character
     */
    User findById(Integer id);

    /**
     * 保存人物实体
     * @param user
     * @return Integer
     */
    Integer saveUser(User user);

    /**
     * 删除人物实体
     * @param id
     * @return Integer
     */
    Integer deleteUser(Integer id);

    /**
     * 查询用户名
     * @param username
     * @return Integer
     */
    User findByUserName(String username);
}
