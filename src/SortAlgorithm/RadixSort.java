package SortAlgorithm;

import java.lang.reflect.Array;
import java.util.Arrays;

public class RadixSort {
    public static void main(String[] args) {

        int maxsize=8000000;
        int[] arr=new int[maxsize];
        long start;
        long end;
        for(int i=0;i<arr.length;i++){
            arr[i]=(int)(Math.random()*100000000);
        }
//        System.out.println(Arrays.toString(arr));
        start=System.currentTimeMillis();
        Radix(arr);
        end=System.currentTimeMillis();
        System.out.println(end-start);
//        System.out.println(Arrays.toString(arr));
    }


    //80000个数据13ms，8000000个数据300+ms
    //基数排序，类似于桶排序，从个位开始，从0-9排序，再填回原数组，循环该过程直至最高位数
    public static void Radix(int[] arr){
        int digit=10;
        int[][] bucket=new int[10][arr.length];
        //记录每个桶中放的元素个数
        boolean flag=true;
        while(flag){
            int[] count=new int[10];
            flag=false;
            //先将原数组中的所有元素放入桶中
            for(int i=0;i<arr.length;i++){
                if(arr[i]/digit!=0){
                    flag=true;
                }
//                int num=arr[i]%digit-arr[i]%(digit/10);     //也可以写成arr[i]/(digit/10)%10
                bucket[arr[i]%digit/(digit/10)][count[arr[i]%digit/(digit/10)]]=arr[i];  
                count[arr[i]%digit/(digit/10)]++;
            }
            //再按照桶的顺序将元素放回原数组
            int index=0;
            for(int i=0;i<bucket.length;i++){
                if(count[i]==0){
                    continue;
                }
//                int j=1;
//                while(j<=count[i]&&index<=9){
//                    arr[index]=bucket[i][j-1];
//                    index++;
//                    j++;
//                }
                //下面的代码回报错数组下表越界
                for(int j=1;j<=count[i];j++){
                    arr[index++]=bucket[i][j-1];
//                    index++;
                }
            }
            digit*=10;
        }
    }
}
