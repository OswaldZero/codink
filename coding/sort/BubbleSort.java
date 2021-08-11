package sort;

/**
 * @author Wuyihu
 * @version 1.0
 * @date 2021/8/8
 */
public class BubbleSort {
    public static void main(String[] args) {
        int[] array = Utils.generateIntegerArray();
        solve(array);
        Utils.print(array);
    }
    public static void solve(int[] array){
        int i,j,k;
        for(i=0;i<array.length-1;i++){
            for (j=0;j<array.length-i-1;j++){
                if(array[j]>array[j+1]){
                    Utils.swap(array,j,j+1);
                }
            }
        }
    }
}
