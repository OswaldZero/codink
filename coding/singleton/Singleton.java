package singleton;

/**
 * @author Wuyihu
 * @version 1.0
 * @date 2021/3/18
 */
public class Singleton {
    private static Singleton singleton = new Singleton();
    private static int x = 0;
    private static int y;
    private Singleton(){
        x++;
        y++;
    }
    public static Singleton getInstance(){
        return singleton;
    }

    public static void main(String[] args) {
        Singleton instance = Singleton.getInstance();
        System.out.println(instance.x);
        System.out.println(instance.y);
    }
}
