package singletons;

/**
 * 线程安全
 * 非懒加载，类一被直接引用就占用内存
 * @author Wuyihu
 * @version 1.0
 * @date 2021/3/29
 */
public class HungerMan {
    private int i;
    private static HungerMan instance = new HungerMan();
    private HungerMan(){
        i=1;
    }
    public static HungerMan getInstance(){
        return instance;
    }
}
