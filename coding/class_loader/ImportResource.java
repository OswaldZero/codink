package class_loader;

/**
 * @author Wuyihu
 * @version 1.0
 * @date 2021/3/22
 */
public class ImportResource {
    public static void main(String[] args) {
        //系统类加载器（应用程序加载器）
        ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
        System.out.println(System.getProperty("java.class.path"));
        //扩展类加载器
        ClassLoader extendedClassLoader = systemClassLoader.getParent();
        System.out.println(System.getProperty("java.ext.dirs"));
        //引导加载器（根加载器）
        ClassLoader bootstrapClassLoader = extendedClassLoader.getParent();
        System.out.println(System.getProperty("sun.boot.class.path"));


    }
}
