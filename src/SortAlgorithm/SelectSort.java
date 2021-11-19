package SortAlgorithm;

import javax.swing.plaf.SliderUI;

public class SelectSort {

    //选择排序1，从第一个开始与后面比较，如果更小就直接交换
    public static void Select1(int[] numarr) {
        int x;
        for (int i = 0; i < numarr.length; i++) {
            for (int j = i + 1; j < numarr.length; j++) {
                if (numarr[j] < numarr[i]) {
                    x = numarr[i];
                    numarr[i] = numarr[j];
                    numarr[j] = x;
                }
            }
        }
    }

    //选择排序2，从头至尾找到最小元素的大小和下表，然后和头部元素交换
    //同样80000个数据，借助index遍历的方法（1000+ms），比直接交换的方法（7000+ms）节省大量时间
    public static void Select2(int[] numarr) {
        int index;
        int num;
        for (int i = 0; i < numarr.length; i++) {
            index = i;
            num = numarr[i];
            for (int j = i; j < numarr.length; j++) {
                if (numarr[j] < num) {
                    index = j;
                    num = numarr[j];
                }
            }
            //将index处的元素与i处的元素交换
            if (index != i) {
                numarr[index] = numarr[i];
                numarr[i] = num;
            }
        }
    }

    public static void main(String[] args) {
        int[] numarr = new int[80000];
        int[] numarr1 = new int[80000];
        long start;
        long end;
        for (int i = 0; i < numarr.length; i++) {
            int j = (int) (Math.random() * 1000000);
            numarr[i] = j;
            numarr1[i] = j;
        }
        start = System.currentTimeMillis();
        Select1(numarr);
        end = System.currentTimeMillis();
        System.out.println(end - start);
//        for(int i:numarr){
//            System.out.print(i+" ");
//        }
//        System.out.println();
        start = System.currentTimeMillis();
        Select2(numarr1);
        end = System.currentTimeMillis();
        System.out.println(end - start);
//        for(int i:numarr1){
//            System.out.print(i+" ");
//        }
    }

}
