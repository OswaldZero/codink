package thread_pool;

/**
 * 任务队列，主要用于缓存提交到线程池中的任务
 * @author Wuyihu
 * @version 1.0
 * @date 2021/3/16
 */
public interface RunnableQueue {
    //当有新的任务进来的时首先会offer到队列里
    void offer(Runnable runnable);

    //工作线程通过take方法获取Runnable
    Runnable take() throws InterruptedException;

    //获取任务队列中线程的数量
    int size();
}
