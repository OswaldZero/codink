package singleton;

/**
 * @author Wuyihu
 * @version 1.0
 * @date 2021/3/19
 */
public class TestStatic {
    private static int a = 10;
    static {
        a = 20;
    }

    public static void main(String[] args) {
        System.out.println(a);
    }
}

interface Test{

}
