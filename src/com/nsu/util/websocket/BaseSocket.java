package com.nsu.util.websocket;

/**
 * @author 墨盒
 * @version 1.0
 * @Date 2017/9/29
 * @Time 9:18
 * @Descorption
 */
public interface BaseSocket {
    long getRId();

    void sendMessage(String message);
}
