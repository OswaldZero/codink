package thread_pool;

/**
 * 创建线程的工厂
 * @author Wuyihu
 * @version 1.0
 * @date 2021/3/16
 */
@FunctionalInterface
public interface ThreadFactory {
    //创建线程方法
    Thread createThread(Runnable runnable);
}
