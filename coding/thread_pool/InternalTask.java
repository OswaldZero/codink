package thread_pool;

/**
 * runnable 的一个实现，主要用于线程池内部，该类会用到RunnalbeQueue
 * 然后不断从queue中取出某个runnable,并运行runnable的run方法
 * @author Wuyihu
 * @version 1.0
 * @date 2021/3/16
 */
public class InternalTask implements Runnable{
    private final RunnableQueue runnableQueue;
    private volatile boolean running = true;
    private boolean running1;

    public InternalTask(RunnableQueue runnableQueue){
        this.runnableQueue=runnableQueue;
    }


    @Override
    public void run() {
        //如果当前任务为running且未被中断，则其将不断充queue中获取runnnable,然后执行run方法
        while (running && !Thread.currentThread().isInterrupted()){
            Runnable take = null;
            try {
                take = runnableQueue.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            take.run();
        }
    }

    //停止当前任务，主要会在线程池的shutdown方法里面使用
    public void stop(){
        this.running = false;
    }
}
