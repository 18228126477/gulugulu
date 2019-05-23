package com.xmr.demo.service.domainService.impl;

import com.xmr.demo.dao.CharacterMapper;
import com.xmr.demo.domain.Character;
import com.xmr.demo.service.domainService.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CharacterServiceImpl implements CharacterService {

    @Autowired
    private CharacterMapper characterMapper;

    @Override
    public List<Character> findAll() {
        return characterMapper.findAll();
    }

    @Override
    public Character findById(Integer id) {
        return characterMapper.findById(id);
    }

    @Override
    public Integer saveCharacter(Character character) {
        return characterMapper.saveCharacter(character);
    }

    @Override
    public Integer deleteCharacter(Integer id) {
        return characterMapper.deleteCharacter(id);
    }
}
