package com.xmr.demo.controller;

import com.xmr.demo.domain.Character;
import com.xmr.demo.service.domainService.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("indexData")
@ResponseBody
public class IndexBody {

    @Autowired
    private CharacterService characterService;

    @RequestMapping("index")
    public Character index(){
        return characterService.findById(1);
    }
}
