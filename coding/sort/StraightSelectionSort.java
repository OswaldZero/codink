package sort;

/**
 * @author Wuyihu
 * @version 1.0
 * @date 2021/8/7
 */
public class StraightSelectionSort {
    public static void main(String[] args) {
        int[] array=Utils.generateIntegerArray();
        solve(array);
        Utils.print(array);
    }
    public static void solve(int[] array){
        int i,j,k;
        for (i = 0; i <array.length-1 ; i++) {
            k=i;
            for (j = i+1; j <array.length ; j++) {
                if(array[j]<array[k]){
                    k=j;
                }
            }
            j=array[k];
            array[k]=array[i];
            array[i]=j;
        }
    }
}
