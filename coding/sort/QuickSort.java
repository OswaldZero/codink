package sort;

/**
 * @author Wuyihu
 * @version 1.0
 * @date 2021/8/8
 */
public class QuickSort {
    public static void main(String[] args) {
        int[] array = Utils.generateIntegerArray(10,1,30);
        solve(array,0,array.length-1);
        Utils.print(array);
    }
    public static void solve(int[] array,int left,int right){
        if(left<right){
            int mid=(left+right)/2,midNum=array[mid];
            int i=left,j=right,temp;
            Utils.swap(array,i,mid);
            for (int k = left+1; k<=right; k++) {
                if(array[k]<midNum){
                    Utils.swap(array,i,k);
                    Utils.swap(array,++i,k);
                }
            }
            solve(array,left,i-1);
            solve(array,i+1,right);
        }
    }
}
