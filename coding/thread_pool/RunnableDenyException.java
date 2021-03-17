package thread_pool;

/**
 * 任务拒绝异常
 * @author Wuyihu
 * @version 1.0
 * @date 2021/3/16
 */
public class RunnableDenyException extends RuntimeException{
    public RunnableDenyException(){
        super();
    }
    public RunnableDenyException(Throwable throwable){
        super(throwable);
    }
    public RunnableDenyException(Throwable throwable,String msg){
        super(msg,throwable);
    }
    public RunnableDenyException(String msg){
        super(msg);
    }
}
