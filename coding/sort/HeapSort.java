package sort;

/**
 * @author Wuyihu
 * @version 1.0
 * @date 2021/8/7
 */
public class HeapSort {
    public static void main(String[] args) {
        int[] array=Utils.generateIntegerArray();
        solve(array);
        Utils.print(array);
    }
    public static void solve(int[] array){
        createHeap(array);
    }

    /**
     * 创建大顶堆函数
     * @param array
     */
    public static void createHeap(int [] array){
        for (int i = array.length/2-1; i >=0 ; i--) {
            adjust(array,i,array.length);
        }
        for (int i = array.length-1; i >0; i--) {
            Utils.swap(array,i,0);
            adjust(array,0,i);
        }
    }

    /**
     * 调节以root为根节点的数组为最大堆
     * @param array 数组
     * @param root 根节点
     */
    public static void adjust(int [] array,int root,int n){
        int temp = array[root];
        //i为直接子节点中最大子节点的下标
        for (int i = root*2+1; i < n ; i=i*2+1) {
            //找到i
            if(i+1<n && array[i]<array[i+1]){
                i++;
            }
            if(array[i]>temp){
                array[root]=array[i];
                root=i;
            }else {
                break;
            }
        }
        array[root]=temp;
    }
}
