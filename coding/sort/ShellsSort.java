package sort;

/**
 * @author Wuyihu
 * @version 1.0
 * @date 2021/8/7
 */
public class ShellsSort {
    public static void main(String[] args) {
        int[] array=Utils.generateIntegerArray();
        solve(array);
        Utils.print(array);
    }

    /**
     * 减少位置的移动
     * @param array
     */
    public static void solve(int[] array){
        int i,j,k,l;
        for (i = array.length;  i>0 ; i/=2) {
            for(j=i;j<array.length;j++){
                for(k=j-i;k>=0;k=k-i){
                    if(array[k]>array[k+i]){
                        l=array[k+i];
                        array[k+i]=array[k];
                        array[k]=l;
                    }
                }
            }
        }
    }
}
