package cn.nsu.edu.web.four.handler.socket;

import cn.nsu.edu.web.four.config.BaseStatic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

import java.io.UnsupportedEncodingException;

/**
 * @author 墨盒
 * @version 1.0
 * @Date 2018/4/21
 * @Time 22:30
 * @Descorption
 */

public class SocketListener implements MessageListener {

    private Logger logger = LoggerFactory.getLogger(getClass());
    private RedisSerializer serializer;

    public SocketListener(RedisSerializer serializer) {
        this.serializer = serializer;
    }

    @Override
    public void onMessage(Message message, byte[] bytes) {
        try {
            String msg = serializer.deserialize(message.getBody()).toString();
            logger.info(msg);
            LiveWebSocket.sendMessage(msg);
        } catch (SerializationException e) {
            e.printStackTrace();
        }
    }
}
