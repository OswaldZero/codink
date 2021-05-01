package singletons;

/**
 * @author Wuyihu
 * @version 1.0
 * @date 2021/3/29
 */
public class Holder {
    private int i;
    private Holder(){i=1;};
    private static class innerClass{
        private static Holder instance = new Holder();
    }
    public static Holder getInstance(){
        return innerClass.instance;
    }
    public int getI(){
        return i;
    }

    public static void main(String[] args) {
        Holder instance = Holder.getInstance();
        System.out.println(instance.getI());
    }

}
