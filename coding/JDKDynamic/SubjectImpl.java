package JDKDynamic;

/**
 * @author Wuyihu
 * @version 1.0
 * @date 2021/8/5
 */
public class SubjectImpl implements Subject{
    @Override
    public void sayHello(String name) {
        System.out.println("hello," + name);
    }
}
