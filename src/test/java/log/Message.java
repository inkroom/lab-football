package log;

/**
 * @author 墨盒
 */
public class Message {
    private long time;
    private String message;

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Message{" +
                "time=" + time +
                ", message='" + message + '\'' +
                '}';
    }
}
