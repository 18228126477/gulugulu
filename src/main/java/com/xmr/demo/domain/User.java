package com.xmr.demo.domain;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class User implements Serializable {

    private Integer id;
    private String username;
    private String password;
}
