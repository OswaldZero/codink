package sort;

/**
 * @author Wuyihu
 * @version 1.0
 * @date 2021/8/8
 */
public class MergeSort {
    public static void main(String[] args) {
        int[] array = Utils.generateIntegerArray();
        solve(array,0,array.length-1);
        Utils.print(array);
    }


    public static void solve(int[] array,int first,int last){
        if(first<last){
            //注意分割的对等
            int mid = (first+last)/2;
            solve(array,first,mid);
            solve(array,mid+1,last);
            combine(array,first,mid+1,last);
        }
    }

    /**
     *
     * @param array
     * @param m
     * @param n
     * @param j
     */
    public static void combine(int[] array,int m,int n,int j){
        int mStart=m,nStart=n,
                mEnd=n-1,nEnd=j,i=0;
        //这个临时数组可以为一个参数传进来
        int[] temp = new int[array.length];
        while (mStart<=mEnd && nStart<=nEnd) {
            if(array[mStart]<array[nStart]){
                temp[i++]=array[mStart++];
            }else {
                temp[i++]=array[nStart++];
            }
        }
        while (mStart<=mEnd){
            temp[i++]=array[mStart++];
        }

        while (nStart<=nEnd){
            temp[i++]=array[nStart++];
        }
        for (int k = 0; k < i; k++) {
            array[m+k]=temp[k];
        }
    }
}
