package com.xmr.demo.controller;


import com.xmr.demo.param.WebSocketBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@ServerEndpoint("/webSocket/{type}/{code}")
@Component
public class WebSocketOrder extends BaseController{

    private static final Logger logger = LoggerFactory.getLogger(WebSocketOrder.class);

    //静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
    private static int onlineCount = 0;
    //concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。若要实现服务端与单一客户端通信的话，可以使用Map来存放，其中Key可以为用户标识
    //private static CopyOnWriteArraySet<WebSocketOrder> webSocketSet = new CopyOnWriteArraySet<>();
    private static Map<String, WebSocketBean> webSocketMap = new ConcurrentHashMap<>();

    /**
    * 连接建立成功调用的方法
    * @param session  可选的参数。session为与某个客户端的连接会话，需要通过它来给客户端发送数据
    */
    @OnOpen
    public void onOpen(@PathParam("type") String type,@PathParam("code") String code, Session session){
        WebSocketBean webSocketBean =  new WebSocketBean();
        webSocketBean.setCode(code);
        webSocketBean.setSession(session);
        webSocketMap.put(type,webSocketBean);
        updateOnlineCount();
        logger.info("有新连接加入！当前在线人数为" + getOnlineCount());
    }

    /**
    * 连接关闭调用的方法
    */
    @OnClose
    public void onClose(@PathParam("code") String code){
        webSocketMap.remove(code);
        updateOnlineCount();
        logger.info("有一连接关闭！当前在线人数为" + getOnlineCount());
    }

    /**
    * 收到客户端消息后调用的方法
    * @param message 客户端发送过来的消息
    */
    @OnMessage
    public void onMessage(@PathParam("code") String code,String message) {
        logger.info("来自客户端的消息:" + message);
        //群发消息
        for (String key : webSocketMap.keySet()) {
            if(("backstage"+code).equals(key)){
                WebSocketBean webSocketBean = webSocketMap.get(key);
                try {
                    webSocketBean.getSession().getBasicRemote().sendText(message);
                } catch (IOException e) {
                    logger.info("webSocket发送消息出错"+e.getMessage());
                }
            }
        }
    }

    public static synchronized void updateOnlineCount(){ onlineCount = webSocketMap.size();}

    public static synchronized int getOnlineCount() {
        return onlineCount;
    }
}
