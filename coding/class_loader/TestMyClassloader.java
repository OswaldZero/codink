package class_loader;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author Wuyihu
 * @version 1.0
 * @date 2021/3/22
 */
public class TestMyClassloader {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        MyDisClassLoader myClassLoader = new MyDisClassLoader();
        Class<?> aClass = myClassLoader.loadClass("class_loader.Hello");
        System.out.println(aClass);
        Object hello = aClass.newInstance();
        System.out.println(hello);
        Method hello1 = aClass.getMethod("hello");
        String result = (String)hello1.invoke(hello);
        System.out.println("Result: " + result);
    }
}
