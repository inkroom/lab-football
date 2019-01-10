package log;

import org.junit.Before;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 当收到消息之后不急着输出，先按序存储到集合，等待一段时间之后没有新的消息，则通知输出
 *
 * @author 墨盒
 */
public class Server extends Thread {

    /**
     * 用有序链表存储到达的记录，
     * 新纪录到达，使用插入排序到有序链表
     * 等待时延，在时延范围内到达的记录保证有序，时延之后清空队列
     */


    private ServerSocket socket;

    private int port;

    private int interval;//间隔时间，毫秒

    private List<Message> queue;

    private OutUtil util;


    public static void main(String[] args) {
        Server server = new Server(4566, 10);
        server.start();
    }

    public Server(int port, int interval) {
        this.port = port;
        this.interval = interval;
        try {
            socket = new ServerSocket(port);

            queue = Collections.synchronizedList(new LinkedList<Message>());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public synchronized void start() {
        super.start();
    }

    @Override
    public void run() {
        while (true) {
            try {
                Socket client = socket.accept();
                Client n = new Client();
                n.setSocket(client);
                n.start();
            } catch (IOException e) {
                e.printStackTrace();
                break;
            }
        }
    }

    public void add(Message m) {
        synchronized (LinkedList.class) {
            int size = queue.size();
            if (size == 0) {
                queue.add(m);
                return;
            }
            for (int i = size - 1; i >= 0; i--) {
                if (queue.get(i).getTime() <= m.getTime()) {
                    if (i != size - 1) {
                        queue.add(i + 1, m);

                    } else {
                        queue.add(m);
                    }
                    break;
                }
            }
        }
    }

    public void outAll() {
        synchronized (LinkedList.class) {
            Iterator<Message> iterator = queue.iterator();
            while (iterator.hasNext()) {
                Message m = iterator.next();
                System.out.println(m.getTime() + "------" + m.getMessage());
                iterator.remove();
            }
        }
    }


    class Client extends Thread {

        private final Pattern p = Pattern.compile("([0-9]+)-(.*)");
        private Socket socket;

        public void setSocket(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            BufferedReader reader = null;
            try {
                reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            } catch (IOException e) {
                e.printStackTrace();
                try {
                    socket.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            while (true) {
                try {
                    String line = null;
                    while ((line = reader.readLine()) != null) {
                        //截取时间戳
                        Matcher matcher = p.matcher(line);
                        if (matcher.find()) {
                            Message message = new Message();
                            message.setTime(Long.parseLong(matcher.group(1)));
                            message.setMessage(matcher.group(2));
                            add(message);

                            //启动定时器,清空
                            if (util == null) {
                                util = new OutUtil();
                                util.start();
                            }
                        }
                    }
                } catch (IOException e) {
                    break;
                } finally {
                    try {
                        if (socket != null)
                            socket.close();
                        if (reader != null) {
                            reader.close();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }
        }

    }

    class OutUtil extends Thread {

        @Override
        public void run() {
            synchronized (LinkedList.class) {
                try {
                    Thread.sleep(interval);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                outAll();
            }
            util = null;
        }
    }

}
