package cn.edu.nsu.lib.handlers;


import cn.edu.nsu.lib.enums.Result;

/**
 * @author 墨盒
 * @version 1.0
 * @Date 2017/9/1
 * @Time 21:55
 * @Descorption 消息传递异常
 */
public class MessageException extends Exception {
    private Exception exception;
    private Result result = Result.EXCEPTION;//消息类型


    public MessageException(Result result) {
        this.result = result;
    }

    public MessageException(String message) {
        super(message);
    }

    public Exception getException() {
        return exception;
    }

    public void setException(Exception exception) {
        this.exception = exception;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public MessageException(String message, Result result) {
        super(message);
        this.result = result;
    }


}
