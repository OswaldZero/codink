package volatileFind;

import java.util.concurrent.TimeUnit;

/**
 * @author Wuyihu
 * @version 1.0
 * @date 2021/3/24
 */
public class VolatileFoo {
    //init_value的最大值
    final static int MAX = 5;
    //init_value的初始值
    static volatile int init_value = 0;

    public static void main(String[] args) {
        new Thread(()->{
            int  localValue = init_value;
            while (localValue < MAX){
                if (init_value != localValue){
                    System.out.printf("the init_value is updated to [%d]\n",init_value);
                    //对localValue进行重新赋值
                    localValue = init_value;
                }
            }
        },"Reader").start();

        //启动updater线程，主要用于对init_value的修改，当local_value>=5的时候则退出生命周期
        new Thread(()->{
            int localValue = init_value;
            while (localValue < MAX){
                //修改init_value
                System.out.printf("the init_value will be changed to [%d]\n",++localValue);
                init_value = localValue;
                try{
                    //短暂休眠，目的是为了让reader线程能够来得及输出变化的内容
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"Updater").start();
    }
}
