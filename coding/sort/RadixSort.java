package sort;

/**
 * @author Wuyihu
 * @version 1.0
 * @date 2021/8/10
 */
public class RadixSort {
    public static void main(String[] args) {
        int[] array = Utils.generateIntegerArray();
        solve(array,1,getMaxDigit(array));
        Utils.print(array);
    }
    public static void solve(int[] array,int digit,int maxDigit){
        if(digit>maxDigit){
            return;
        }
        int[] bucket = new int[10];
        int[] help = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            int numByDigit = getNumByDigit(array[i], digit);
            bucket[numByDigit]++;
        }
        for (int i = 1; i < bucket.length; i++) {
            bucket[i]=bucket[i]+bucket[i-1];
        }
        //因为单个桶里面的顺序是由小到大的，大的数据在后面，所以要逆序赋值
        for (int i = array.length-1; i >=0; i--) {
            int numByDigit = getNumByDigit(array[i], digit);
            help[bucket[numByDigit]-1]=array[i];
            bucket[numByDigit]--;
        }
        for (int i = 0; i < array.length; i++) {
            array[i]=help[i];
        }
        solve(array,digit+1,maxDigit);
    }
    private static int getNumByDigit(int n,int digit){
        int i=1,a=0,b;
        while (i<=digit){
            a=n%10;
            n=n/10;
            i++;
        }
        return a;
    }
    private static int getDigit(int n){
        if(n<10){
            return 1;
        }else {
            return getDigit(n/10)+1;
        }
    }
    private static int getMaxDigit(int[] array){
        int max = getDigit(array[0]);
        for (int i = 1; i < array.length; i++) {
            if (getDigit(array[i])>max) {
                max=getDigit(array[i]);
            }
        }
        return max;
    }
}
