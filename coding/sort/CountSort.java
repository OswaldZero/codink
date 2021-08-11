package sort;

/**
 * @author Wuyihu
 * @version 1.0
 * @date 2021/8/10
 */
public class CountSort {
    public static void main(String[] args) {
        int[] array = Utils.generateIntegerArray();
        solve(array);
        Utils.print(array);

    }

    public static void solve(int[] array) {
        // 找出数组A中的最大值、最小值
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for (int num : array) {
            max = Math.max(max, num);
            min = Math.min(min, num);
        }
        // 初始化计数数组count
        // 长度为最大值减最小值加1
        int[] count = new int[max-min+1];
        // 对计数数组各元素赋值
        for (int num : array) {
            // A中的元素要减去最小值，再作为新索引
            count[num-min]++;
        }
        // 计数数组变形，新元素的值是前面元素累加之和的值
        for (int i=1; i<count.length; i++) {
            count[i] += count[i-1];
        }
        // 创建结果数组
        int[] result = new int[array.length];
        // 遍历A中的元素，填充到结果数组中去
        for (int j=0; j<array.length; j++) {
            result[count[array[j]-min]-1] = array[j];
            count[array[j]-min]--;
        }
        for (int i = 0; i < array.length; i++) {
            array[i]=result[i];
        }
    }
}
