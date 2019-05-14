package com.xmr.demo.service.domainService;

import com.xmr.demo.domain.Character;

public interface CharacterService {
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
