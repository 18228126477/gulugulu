package com.xmr.demo.dao;

import com.xmr.demo.domain.Character;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CharacterMapper {

    /**
     * 获取人物信息
     * @return Character
     */
    List<Character> findAll();

    /**
     * 根据人物 ID，获取人物信息
     * @param id
     * @return Character
     */
    Character findById(Integer id);

    /**
     * 保存人物实体
     * @param character
     * @return Integer
     */
    Integer saveCharacter(Character character);

    /**
     * 删除人物实体
     * @param id
     * @return Integer
     */
    Integer deleteCharacter(Integer id);
}
