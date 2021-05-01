package singletons;

import org.omg.PortableInterceptor.INACTIVE;
import singleton.Singleton;

/**
 * @author Wuyihu
 * @version 1.0
 * @date 2021/3/29
 */
public enum Enumration {
    INSTANCE;
    private Enumration() {
        System.out.println("initialized");
    }
    public static void method(){

    }
    public static Enumration getInstance(){
        return INSTANCE;
    }

}
class OutClass{
    private int i;
    private OutClass(){
        i=1;
    }
    private enum EnumHolder{
        INSTANCE;
        private OutClass instance;
        EnumHolder()
        {
            this.instance = new OutClass();
        }
        public OutClass getInstance(){
            return instance;
        }
    }
}