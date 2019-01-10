package com.nsu.util.websocket;

import com.nsu.common.Constants;
import com.nsu.util.websocket.onchat.ChatServerPool;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.http.HttpSession;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Map;

/**
 * @author 墨盒
 * @version 1.0
 * @Date 2017/9/27
 * @Time 15:52
 * @Descorption 看直播的socket，只发送消息，不接受消息
 */
@ServerEndpoint(value = "/live", configurator = Config.class)
public class LiveSocket implements BaseSocket {

    private HttpSession httpSession;
    Log log = LogFactory.getLog(getClass());
    long rId = -1;

    private Session session;
    private Map<String, Object> user;

    /**
     * 触发连接事件
     */
    @OnOpen
    public void onOpen(Session session, EndpointConfig config) {
        this.session = session;
        userjoin();
        httpSession = (HttpSession) config.getUserProperties().get(HttpSession.class.getName());
        //获取比赛id
        log.info("RID = " + httpSession.getAttribute("RID"));
        if (httpSession.getAttribute("RID") != null) {//观看比赛
            rId = Long.parseLong(httpSession.getAttribute("RID").toString());
            log.info(" 观看比赛  " + rId);
        }
    }

    /**
     * 触发关闭事件
     */
    @OnClose
    public void onClose() {
        userLeave();
        if (this.user == null) {
            httpSession.removeAttribute("RID");
        }
    }

    /**
     * 客户端发送消息到服务器时触发事件
     */
    @OnMessage
    public void onMessage(String message) {

    }


    /**
     * 触发异常事件
     */
    @OnError
    public void onError(Throwable ex) {

        ex.printStackTrace();
        ChatServerPool.removeUser(this);
        if (this.user == null) {
            httpSession.removeAttribute("RID");
        }
//        if (conn != null) {
        //some errors like port binding failed may not be assignable to a specific websocket
//        }
    }


    /**
     * 用户加入处理
     */
    public void userjoin() {
        ChatServerPool.addUser(null, this);                            //向连接池添加当前的连接对象
    }

    /**
     * 用户下线处理
     */
    public void userLeave() {
//        String user = ChatServerPool.getUserByKey(this);
        boolean b = ChatServerPool.removeUser(this);                //在连接池中移除连接
    }

    @Override
    public void sendMessage(String message) {
        try {
            session.getBasicRemote().sendText(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public long getRId() {
        return rId;
    }

    public Map<String, Object> getUser() {
        return user;
    }
}

