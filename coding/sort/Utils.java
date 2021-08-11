package sort;

import java.util.Random;
import java.util.stream.IntStream;

/**
 * 算法工具类
 * @author Wuyihu
 * @version 1.0
 * @date 2021/8/7
 */
public class Utils {
    //数组大小
    private static final int ARRAY_SIZE = 10;
    //产生的整数的最小值
    private static final int ARRAY_BEGIN = 1;
    //产生的整数的最大值（不包括）
    private static final int ARRAY_BOUND = 21;

    /**
     * 生成无序数组
     * @param size 数组大小
     * @param begin 数组中的整数的最小值
     * @param bound 数组中整数的最大值（不包括）
     * @return 无序数组
     */
    public static int[] generateIntegerArray(int size,int begin,int bound){
        Random random = new Random();
        IntStream ints = random.ints(size, begin, bound);
        return ints.toArray();
    }

    /**
     * 生成默认值的无序数组
     * @return 默认值的无序数组
     */
    public static int[] generateIntegerArray(){
        return generateIntegerArray(ARRAY_SIZE,ARRAY_BEGIN,ARRAY_BOUND);
    }

    /**
     * 交换数组两个位置值
     * @param array 数组
     * @param i 位置1
     * @param j 位置2
     */
    public static void swap(int[] array,int i,int j){
        int temp=array[i];
        array[i]=array[j];
        array[j]=temp;
    }
    public static void print(int[] array){
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i]+" ");
        }
    }
}
