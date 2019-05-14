package com.xmr.demo.param;

import javax.websocket.Session;

public class WebSocketBean {

    private String code;        //客户端传入的唯一标识
    private String command;     //一些特殊的命令
    private Session session;    //当前webSocket的session

    public String getCode() {
        return code;
    }

    public String getCommand() {
        return command;
    }

    public Session getSession() {
        return session;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public void setSession(Session session) {
        this.session = session;
    }
}
