package class_loader;

/**
 * @author Wuyihu
 * @version 1.0
 * @date 2021/3/22
 */
public class Hello {
    static {
        System.out.println("begin to init");
    }

    public String hello(){
        return "hello world";
    }
}
