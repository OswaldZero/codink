package thread_pool;

/**
 * 当任务达到上限的时候采取的拒绝策略
 * @author Wuyihu
 * @version 1.0
 * @date 2021/3/16
 */
@FunctionalInterface
public interface DenyPolicy {
    void reject(Runnable runnable,ThreadPool threadPool) throws RunnableDenyException;

    //直接丢弃任务的拒绝策略
    class DiscardDenyPolicy implements DenyPolicy{
        @Override
        public void reject(Runnable runnable, ThreadPool threadPool) {
            //do nothing
        }
    }

    //向任务提交者抛出异常
    class AbortDenyPolicy implements DenyPolicy{
        @Override
        public void reject(Runnable runnable, ThreadPool threadPool) throws RunnableDenyException {
            throw new RunnableDenyException("the runnable "+runnable+" will abort.");
        }
    }

    //该拒绝策略会使任务在提交者所在的线程中执行任务
    class RunnerDenyPolicy implements DenyPolicy{
        @Override
        public void reject(Runnable runnable, ThreadPool threadPool) throws RunnableDenyException {
            if (!threadPool.isShutdown()){
                runnable.run();
            }
        }
    }
}
