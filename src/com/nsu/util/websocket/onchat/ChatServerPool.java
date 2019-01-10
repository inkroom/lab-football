/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.nsu.util.websocket.onchat;

import com.nsu.common.mapper.JsonMapper;
import com.nsu.filter.XSSRequestWrapper;
import com.nsu.util.websocket.BaseSocket;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.text.SimpleDateFormat;
import java.util.*;


public class ChatServerPool {
    private static final Log log = LogFactory.getLog(ChatServerPool.class);
//    private static final Map<BaseSocket, String> userconnections = new HashMap<>();
    private static final List<BaseSocket> sockets = new ArrayList<>();

    /**
     * 获取用户名
     */
//    public static String getUserByKey(BaseSocket conn) {
//        return sockets.get(conn);
//    }

    /**
     * 获取WebSocket
     *
     * @param user
     */
//    public static BaseSocket getWebSocketByUser(String user) {
//        Set<BaseSocket> keySet = userconnections.keySet();
//        synchronized (keySet) {
//            for (BaseSocket conn : keySet) {
//                String cuser = userconnections.get(conn);
//                if (cuser.equals(user)) {
//                    return conn;
//                }
//            }
//        }
//        return null;
//    }

    /**
     * 向连接池中添加连接
     */
    public static void addUser(String user, BaseSocket conn) {
        sockets.add(conn);    //添加连接
    }

    /**
     * 获取所有的在线用户
     *
     * @return
     */
//    public static Collection<String> getOnlineUser() {
////		List<String> setUsers = new ArrayList<String>();
//        Collection<String> setUsers = userconnections.values();
////		for(String u:setUser){
////			setUsers.add("<a onclick=\"toUserMsg('"+u+"');\">"+u+"</a>");
////		}
//        return setUsers;
//    }

    /**
     * 移除连接池中的连接
     *
     * @param conn
     * @return
     */
    public static boolean removeUser(BaseSocket conn) {
        if (sockets.contains(conn)) {
            sockets.remove(conn);    //移除连接
            return true;
        } else {
            return false;
        }
    }

    /**
     * 向特定的用户发送数据
     *
     * @param conn
     * @param message
     */
//    public static void sendMessageToUser(BaseSocket conn, String message) {
//        if (null != conn && null != sockets.get(conn)) {
//            conn.sendMessage(message);
//        }
//    }

    /**
     * 向所有的用户发送消息
     *
     * @param message 发送的消息
     * @param rId     比赛id
     */
    public static void sendMessage(String message, long rId) {

        log.info("进入方法了 sendMessage    " + rId);

        try {
            Map<String, Object> map = JsonMapper.getInstance().fromJson(message, Map.class);
            SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");//设置日期格式
            map.put("liveTime", df.format(new Date()));
//            log.info("用户数=" + sockets..keySet().size());
            log.info("1***=" + map.toString());
            map.put("html", XSSRequestWrapper.stripXSS(map.get("html").toString()));
            log.info("2***=" + map.toString());
//            Set<BaseSocket> keySet = userconnections.keySet();

            synchronized (sockets) {
                for (BaseSocket conn : sockets) {
                    if (conn.getRId() == rId) {
                        log.info("发送消息给" + rId + "   消息为 = " + message);
//                        String user = userconnections.get(conn);
                        log.info("发布信息");
                        conn.sendMessage(JsonMapper.getInstance().toJson(map));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
