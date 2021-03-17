package thread_pool;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Wuyihu
 * @version 1.0
 * @date 2021/3/17
 */
public class BasicThreadPool extends Thread implements ThreadPool{
    //初始线程池的数量
    private final int initSize;

    //线程池最大线程数量
    private final int coreSize;

    //线程池核心线程数量
    private final int maxSize;

    //当前活跃的线程数量
    private int activeCount;

    //创建线程的工厂
    private final ThreadFactory threadFactory;

    //任务队列
    private final RunnableQueue runnableQueue;

    //线程池是否被shutdown
    private volatile boolean isShutdown = false;

    //工作线程队列
    private final Queue<ThreadTask> threadTaskQueue = new ArrayDeque<>();

    //默认拒绝策略
    private final static DenyPolicy DEFAULT_THREAD_POLICY = new DenyPolicy.DiscardDenyPolicy();

    //默认的线程工厂
    private final static ThreadFactory DEFAULT_THREAD_FACTORY = new DefaultThreadFactory();

    private final long keepAliveTime;

    private final TimeUnit timeUnit;

    public BasicThreadPool(int initSize, int maxSize, int coreSize,int queueSize){
        this(initSize,maxSize,coreSize,DEFAULT_THREAD_FACTORY,queueSize,DEFAULT_THREAD_POLICY,10,TimeUnit.SECONDS);
    }


    public BasicThreadPool(int initSize,int maxSize,int coreSize,ThreadFactory threadFactory,
                           int queueSize,DenyPolicy denyPolicy,long keepAliveTime,TimeUnit timeUnit){
        this.initSize = initSize;
        this.maxSize = maxSize;
        this.coreSize = coreSize;
        this.threadFactory = threadFactory;
        this.runnableQueue = new LinkedRunnableQueue(queueSize,denyPolicy,this);
        this.keepAliveTime = keepAliveTime;
        this.timeUnit = timeUnit;
        this.init();
    }

    /**
     * 初始化
     */
    private void init(){
        start();
        for (int i = 0; i < initSize; i++) {
            newThread();
        }
    }

    /**
     * 创建新线程并启动
     */
    private void newThread(){
        //创建任务线程，并且启动
        InternalTask internalTask = new InternalTask(runnableQueue);
        Thread thread = this.threadFactory.createThread(internalTask);
        ThreadTask threadTask = new ThreadTask(thread,internalTask);
        threadTaskQueue.offer(threadTask);
        this.activeCount++;
        thread.start();
    }

    /**
     * 增加任务
     * @param runnable 任务
     */
    @Override
    public void execute(Runnable runnable){
        if(this.isShutdown)throw new IllegalStateException("the thread pool is destory");
        this.runnableQueue.offer(runnable);
    }

    //ThreadTask只是InternalTask 和 Thread的一个组合
    private static class ThreadTask{
        Thread thread;
        InternalTask internalTask;
        public ThreadTask(Thread thread,InternalTask internalTask){
            this.internalTask = internalTask;
            this.thread = thread;
        }
    }


    //线程池自动维护
    public void removeThread(){
        //从线程池里移除某个线程
        ThreadTask threadTask = threadTaskQueue.remove();
        threadTask.internalTask.stop();
        this.activeCount--;
    }

    @Override
    public void run(){
        while (!isShutdown && !isInterrupted()){
            try {
                timeUnit.sleep(keepAliveTime);
            } catch (InterruptedException e) {
                isShutdown = true;
                break;
            }
            synchronized (threadFactory)
            {
                if (isShutdown)break;
                //当前队列中尚有任务未处理，并且activeCount < coreSize则继续扩容
                if(runnableQueue.size() > 0 && activeCount < coreSize){
                    for (int i = initSize; i < coreSize; i++) {
                        newThread();
                    }
                    //continue 的目的在于不想让线程扩容直接打到maxsize
                    continue;
                }

                //当前对了有任务尚未处理，并且activeCount < maxSize
                if (runnableQueue.size() > 0 && activeCount < maxSize){
                    for (int i = coreSize; i < maxSize ; i++) {
                        newThread();
                    }
                }

                //如果任务队列里没有任务，则需要收回，回收到coreSize即可
                if (runnableQueue.size() == 0 && activeCount > coreSize){
                    for (int i = coreSize; i < activeCount ; i++) {
                        removeThread();
                    }
                }
            }
        }
    }

    @Override
    public void shutdown(){
        synchronized (this){
            if (isShutdown)return;
            isShutdown = true;
            threadTaskQueue.forEach(threadTask -> {
                threadTask.internalTask.stop();
                threadTask.thread.interrupt();
            });
        }

    }

    @Override
    public int getInitSize(){
        if (isShutdown){
            throw new IllegalStateException("the thread pool is destory");
        }
        return this.initSize;
    }

    @Override
    public int getMaxSize(){
        if (isShutdown)throw new IllegalStateException("the thread pool is destory");
        return this.maxSize;
    }

    @Override
    public int getCoreSize(){
        if(isShutdown)throw new IllegalStateException("the thread pool is destory");
        return this.coreSize;
    }

    @Override
    public int getQueueSize(){
        if (isShutdown)throw new IllegalStateException("the thread pool is destory");
        return runnableQueue.size();
    }

    @Override
    public int getActiveCount(){
        synchronized (this){
            return this.activeCount;
        }
    }

    @Override
    public boolean isShutdown(){
        return this.isShutdown;
    }

    private static class DefaultThreadFactory implements ThreadFactory{
        private static final AtomicInteger GROUP_COUNTER = new AtomicInteger(1);
        private static final ThreadGroup group = new ThreadGroup("MyThreadPool-"+GROUP_COUNTER.getAndDecrement());
        private static final AtomicInteger COUNTER = new AtomicInteger(0);
        @Override
        public Thread createThread(Runnable runnable){
            return new Thread(group,runnable,"thread-pool-"+COUNTER.getAndDecrement());
        }
    }
}
