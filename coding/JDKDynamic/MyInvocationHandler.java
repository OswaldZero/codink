package JDKDynamic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author Wuyihu
 * @version 1.0
 * @date 2021/8/5
 */
public class MyInvocationHandler implements InvocationHandler {
    private Object target;
    public MyInvocationHandler(Object target){
        this.target=target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println(proxy.getClass());
        System.out.println("begin");
        Class<?>[] interfaces = proxy.getClass().getInterfaces();
        Void invoke = (Void) method.invoke(target, args);
        System.out.println("end");
        System.out.println(invoke);
        return invoke;
    }
}
