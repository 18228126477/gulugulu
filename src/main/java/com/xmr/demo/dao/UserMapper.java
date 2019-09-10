package com.xmr.demo.dao;

import com.xmr.demo.domain.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {

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
