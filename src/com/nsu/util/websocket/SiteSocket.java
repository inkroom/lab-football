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
 * @Date 2017/9/29
 * @Time 9:20
 * @Descorption 发送直播消息
 */
@ServerEndpoint(value = "/site", configurator = Config.class)
public class SiteSocket implements BaseSocket {
    private long rid;
    private Log log = LogFactory.getLog(getClass());
    private Session session;

    /**
     * 触发连接事件
     */
    @OnOpen
    public void onOpen(Session session, EndpointConfig config) {
        this.session = session;
        userjoin();
        HttpSession httpSession = (HttpSession) config.getUserProperties().get(HttpSession.class.getName());
        //获取比赛id
        //现场管理员管理比赛
        Map<String, Object> map = (Map<String, Object>) httpSession.getAttribute(Constants.LOGIN_USER);
        if (map == null) {
            try {
                session.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }

        rid = (long) map.get("RID");
        log.info(" 发布比赛  " + rid);
    }

    /**
     * 触发关闭事件
     */
    @OnClose
    public void onClose() {
        userLeave();
    }

    /**
     * 客户端发送消息到服务器时触发事件
     */
    @OnMessage
    public void onMessage(String message) {
//        if (user == null) {
//            log.info(" 服务器收到消息<" + message + ">，但是实际是未登录");
//            return;
//        }

        log.info("rid = " + rid + "   来自客户端的消息:" + message);

        ChatServerPool.sendMessage(message, rid);

    }


    /**
     * 触发异常事件
     */
    @OnError
    public void onError(Throwable ex) {

        ex.printStackTrace();
        ChatServerPool.removeUser(this);
//        if (this.user == null) {
//            httpSession.removeAttribute("RID");
//        }
//        if (conn != null) {
        //some errors like port binding failed may not be assignable to a specific websocket
//        }
    }


    /**
     * 用户加入处理
     */
    public void userjoin() {
//		AjaxJson j = new AjaxJson();
//		j.put("type", "user_join");
//		j.put("user", "<a onclick=\"toUserMsg('"+user+"');\">"+user+"</a>");
//		MsgServerPool.sendMessage(j.getJsonStr());				//把当前用户加入到所有在线用户列表中
//		String joinMsg = "{\"from\":\"[系统]\",\"content\":\""+user+"上线了\",\"timestamp\":"+new Date().getTime()+",\"type\":\"message\"}";
//		MsgServerPool.sendMessage(joinMsg);						//向所有在线用户推送当前用户上线的消息
//		j = new AjaxJson();
//		j.put("type", "get_online_user");
        ChatServerPool.addUser(null, this);                            //向连接池添加当前的连接对象
//		j.put("list", MsgServerPool.getOnlineUser());
//		MsgServerPool.sendMessageToUser(conn, j.getJsonStr());	//向当前连接发送当前在线用户的列表
    }

    /**
     * 用户下线处理
     */
    public void userLeave() {
//        String user = ChatServerPool.getUserByKey(this);
        boolean b = ChatServerPool.removeUser(this);                //在连接池中移除连接
//		if(b){
//			AjaxJson j = new AjaxJson();
//			j.put("type", "user_leave");
//			j.put("user", "<a onclick=\"toUserMsg('"+user+"');\">"+user+"</a>");
//			MsgServerPool.sendMessage(j.getJsonStr());			//把当前用户从所有在线用户列表中删除
//			String joinMsg = "{\"from\":\"[系统]\",\"content\":\""+user+"下线了\",\"timestamp\":"+new Date().getTime()+",\"type\":\"message\"}";
//			MsgServerPool.sendMessage(joinMsg);					//向在线用户发送当前用户退出的消息
//		}
    }


    @Override
    public long getRId() {
        return this.rid;
    }

    @Override
    public void sendMessage(String message) {
        log.info("  发不直播的socket不负责发送消息");
    }
}
