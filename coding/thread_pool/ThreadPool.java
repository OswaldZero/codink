package thread_pool;

/**
 * 线程池接口
 * @author Wuyihu
 * @version 1.0
 * @date 2021/3/16
 */
public interface ThreadPool {
    //提交任务到线程池
    void execute(Runnable runnable) throws IllegalStateException;

    //关闭线程池
    void shutdown();

    //获取线程池的初始化大小
    int getInitSize();

    //获取线程池的最大线程数
    int getMaxSize();

    //获取线程池的核心线程数
    int getCoreSize();

    //获取线程池中用于缓存任务队列的大小
    int getQueueSize();

    //获取线程池中活跃线程的数量
    int getActiveCount();

    //查看线程池是否被shutdown
    boolean isShutdown();
}
