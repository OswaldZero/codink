package class_loader;

/**
 * @author Wuyihu
 * @version 1.0
 * @date 2021/3/22
 */
public class MyDisClassLoader extends MyClassLoader{
    @Override
    protected Class<?> loadClass(String name,boolean resolve) throws ClassNotFoundException {
        synchronized (getClassLoadingLock(name)){
            Class<?> klass = findLoadedClass(name);
            if(klass==null){
                if(name.startsWith("java.") || name.startsWith("javax")){
                    try{
                        klass = getSystemClassLoader().loadClass(name);
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }else {
                    try {
                        klass = this.findClass(name);

                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                if (klass==null){
                    if (getParent()!=null){
                        klass=getParent().loadClass(name);

                    }else {
                        klass = getSystemClassLoader().loadClass(name);
                    }
                }
            }
            if(null == klass)throw new ClassNotFoundException("cann't find this class");
            if (resolve)resolveClass(klass);
            return klass;
//            new String()
        }
    }
}
