package cn.nsu.edu.web.four.handler.socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.*;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class TextSocketHandler extends TextWebSocketHandler {
    private Logger log = LoggerFactory.getLogger(getClass());
    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        super.handleMessage(session, message);
        log.info("发送的消息="+message.toString());
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        super.handleTextMessage(session, message);
        log.info("发送的消息="+message.toString());
    }

    @Override
    protected void handlePongMessage(WebSocketSession session, PongMessage message) throws Exception {
        super.handlePongMessage(session, message);
        log.info("发送的消息="+message.toString());
    }

    @Override
    protected void handleBinaryMessage(WebSocketSession session, BinaryMessage message) {
        super.handleBinaryMessage(session, message);
        log.info("发送的消息="+message.toString());
    }

}
