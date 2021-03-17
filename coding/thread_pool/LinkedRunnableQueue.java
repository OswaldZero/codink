package thread_pool;

import java.util.LinkedList;

/**
 * 任务队列的实现
 * @author Wuyihu
 * @version 1.0
 * @date 2021/3/17
 */
public class LinkedRunnableQueue implements RunnableQueue{
    //任务队列的最大容量，在构造时传入
    private final int limit;

    //若任务队列中的任务已经满了，则需要执行拒绝策略
    private final DenyPolicy denyPolicy;

    //存放任务的队列
    private final LinkedList<Runnable> runnableLinkedList = new LinkedList<>();

    //所属的线程池
    private final ThreadPool threadPool;


    public LinkedRunnableQueue(int limit,DenyPolicy denyPolicy,ThreadPool threadPool){
        this.limit=limit;
        this.denyPolicy=denyPolicy;
        this.threadPool=threadPool;
    }

    @Override
    public void offer(Runnable runnable) {
        //对任务队列加锁
        synchronized (runnableLinkedList){
            if (runnableLinkedList.size()>=limit){
                //执行拒绝策略
                denyPolicy.reject(runnable,threadPool);
            }else {
                //将任务加到队尾，并唤醒阻塞中的线程
                runnableLinkedList.addLast(runnable);
                runnableLinkedList.notifyAll();
            }
        }
    }

    @Override
    public Runnable take() throws InterruptedException {
        synchronized (runnableLinkedList){
            while (runnableLinkedList.isEmpty()){
                try {
                    runnableLinkedList.wait();
                }catch (InterruptedException e){
                    throw e;
                }
            }
            return runnableLinkedList.removeFirst();
        }
    }

    @Override
    public int size() {
        synchronized (runnableLinkedList){
            return runnableLinkedList.size();
        }
    }
}
