package sort;

/**
 * @author Wuyihu
 * @version 1.0
 * @date 2021/8/6
 */
public class StraightInsertionSort {
    public static int[] array;
    public static void main(String[] args) {
        array=Utils.generateIntegerArray();
        solve1();
        solve2();
        Utils.print(array);
    }

    /**
     * 版本1，仿真版本
     * 查找到位置后后移
     */
    public static void solve1(){
        int i,j,k,temp;
        for (i = 1; i < array.length; i++) {
            for (j = i-1; j >=0; j--) {
                if(array[j]<=array[i]){
                    break;
                }
            }
            temp=array[i];
            for(k=i-1;k>j;k--){
                array[k+1]=array[k];
            }
            array[j+1]=temp;
        }
    }

    /**
     * 版本2
     * 边查找，边位移
     */
    public static void solve2(){
        int i,j,k;
        for (i = 1; i <array.length ; i++) {
            for(j=i-1;j>=0;j--){
                if(array[j]>array[j+1]){
                    Utils.swap(array,j,j+1);
                }
            }
        }
    }

}
