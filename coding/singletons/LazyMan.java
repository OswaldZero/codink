package singletons;

/**
 * 懒加载
 * 线程不安全
 * @author Wuyihu
 * @version 1.0
 * @date 2021/3/29
 */
public class LazyMan {
    private int i;
    private static LazyMan instance = null;
    private LazyMan(){
        i = 1;
    }
    public static LazyMan getInstance(){
        if(instance==null){
            instance = new LazyMan();
        }
        return instance;
    }
}

/**
 * 线程安全
 * 懒加载
 * 但是因为synchronized的锁是重量级锁，占用较大资源
 */
class LazyManPlus{
    private int i;
    private static LazyManPlus instance = null;
    private LazyManPlus(){i=1;};
    public static synchronized LazyManPlus getInstance(){
        if (instance==null){
            instance = new LazyManPlus();
        }
        return instance;
    }
}

/**
 * 线程不安全，因为重排序的原因
 * 可能会导致还没初始化成功的属性被引用
 * 效率提高了，不是直接使用重量级锁了
 *
 */
class LazyManDoubleCheck{
    private int i;
    private static LazyManDoubleCheck instance = null;
    private LazyManDoubleCheck(){i=1;};
    public static LazyManDoubleCheck getInstance(){
        if(instance == null){
            synchronized (LazyManDoubleCheck.class){
                if (instance == null){
                    instance = new LazyManDoubleCheck();
                }
            }
        }
        return instance;
    }
}

/**
 * 因为顺序的确定，所以线程安全
 * 同时占用资源也少
 */
class LazyManDoubleCheckAndVolatile{
    private int i;
    private static volatile LazyManDoubleCheckAndVolatile instance = null;
    private LazyManDoubleCheckAndVolatile(){i=1;};
    public static LazyManDoubleCheckAndVolatile getInstance(){
        if(instance == null){
            synchronized (LazyManDoubleCheck.class){
                if (instance == null){
                    instance = new LazyManDoubleCheckAndVolatile();
                }
            }
        }
        return instance;
    }
}
