package bloom_filter;

import java.util.BitSet;
import java.util.SimpleTimeZone;

/**
 * bloom filter
 * 本质就是对value散列（多个散列函数），然后判断是否包含的问题
 * 和目前密码的校验有点像，但是密码是值得比对，这个是把值映射到多个数，然后用数比对。
 * @author Wuyihu
 * @version 1.0
 * @date 2021/3/14
 */
public class MyBloomFilter {
    private static final int DEFAULT_SIZE = 2 << 25;
    private static final int[] seeds = new int[]{2,3,4,5,6,7,8,9};
    private BitSet bitSet = new BitSet(DEFAULT_SIZE);
    private SimpleHash funcs[] = new SimpleHash[seeds.length];

    public MyBloomFilter(){
        for (int i = 0; i < seeds.length; i++) {
            funcs[i] = new SimpleHash(DEFAULT_SIZE,seeds[i]);
        }
    }

    public void add(String value){
        for (SimpleHash simpleHash: funcs) {
            bitSet.set(simpleHash.hash(value),true);
        }
    }

    public boolean contains(String value){
        if (value==null){
            return false;
        }

        boolean result = true;
        for (int i = 0; i < funcs.length; i++) {
            result = result && bitSet.get(funcs[i].hash(value));
        }
        return result;
    }

    class SimpleHash{
        private int cap;
        private int seed;
        public SimpleHash(int cap,int seed){
            this.cap = cap;
            this.seed = seed;
        }
        public int hash(String value){
            int result = 0;
            int length = value.length();
            for (int i = 0; i < length; i++) {
                result = seed * result + value.charAt(i);
            }
            return cap-1 & result;
        }
    }
}
