package cn.nsu.edu.web.four.handler.socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

@ServerEndpoint(value = "/test")
public class LiveWebSocket {

    //    private static final AtomicInteger connectionIds = new AtomicInteger(0);
    private static final List<LiveWebSocket> connections =
            new LinkedList<>();
    private Session session;

    private Logger log  = LoggerFactory.getLogger(getClass());
//    public ChatAnnotation() {
//        nickname = GUEST_PREFIX + connectionIds.getAndIncrement();
//    }

    @OnOpen
    public void start(Session session, EndpointConfig config) {
        this.session = session;
        connections.add(this);
    }


    @OnClose
    public void end() {
        try {
            session.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        connections.remove(this);
//        String message = "对方已退出游戏！";
//        board.clear();
    }

    /**
     * 收到消息
     *
     * @param message 收到的消息
     */
    @OnMessage
    public void incoming(String message) {
    }


    @OnError
    public void onError(Throwable t) throws Throwable {
//        board.clear();
        try {
            session.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        t.printStackTrace();
        connections.remove(this);
        log.info("OnError");
    }

    public static  void sendMessage(String message){
        // TODO: 18-3-25 添加区分id发送
        for (LiveWebSocket s : connections) {
            try {
                s.session.getBasicRemote().sendText(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static List<LiveWebSocket> getList(){
        return connections;
    }

}
