package JDKDynamic;

import java.lang.reflect.Proxy;

/**
 * @author Wuyihu
 * @version 1.0
 * @date 2021/8/5
 */
public class Test {
    public static void main(String[] args) {
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        Subject o =(Subject)Proxy.newProxyInstance(SubjectImpl.class.getClassLoader(),SubjectImpl.class.getInterfaces(), new MyInvocationHandler(new SubjectImpl()));
        o.sayHello("hello");
    }
}
