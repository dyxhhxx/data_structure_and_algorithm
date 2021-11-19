package SortAlgorithm;

import java.lang.reflect.Array;
import java.util.Arrays;

public class BubbleSort {

    public static void Bubble(int[] numlist) {
        for (int i = numlist.length - 1; i > 0; i--) {
            //冒泡排序标志符，为true时说明为排序部分已经按照正序，可以退出
            boolean flag = true;
            for (int j = 0; j < i; j++) {
                int x;
                if (numlist[j] > numlist[j + 1]) {
                    x = numlist[j];
                    numlist[j] = numlist[j + 1];
                    numlist[j + 1] = x;
                    flag = false;
                }
            }
            if (flag) {
                break;
            }
        }
    }

    //没有flag的Bubble方法
    public static void Bubble1(int[] numlist) {
        for (int i = numlist.length - 1; i > 0; i--) {
            //冒泡排序标志符，为true时说明为排序部分已经按照正序，可以退出
//            boolean flag=true;
            for (int j = 0; j < i; j++) {
                int x;
                if (numlist[j] > numlist[j + 1]) {
                    x = numlist[j];
                    numlist[j] = numlist[j + 1];
                    numlist[j + 1] = x;
//                    flag=false;
                }
            }
//            if(flag){
//                break;
//            }
        }
    }

    public static void main(String[] args) {
        int[] numlist = {1, 3, 2, 4, 5};
        int[] numlist1 = {3, 9, -1, 10, -2};
        int[] numlist2 = new int[80000];
        int[] numlist3 = new int[80000];
        long start;
        long end;
        Bubble(numlist1);
        Bubble(numlist);
        for (int i : numlist) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i : numlist1) {
            System.out.print(i + " ");
        }
        //用运行时间说明，添加flag后程序运行时间有所减短
        //注意：增强型for循环只能遍历，不能给数组赋值
        for (int i = 0; i < numlist2.length; i++) {
            int j = (int) (Math.random() * 100000);
            numlist2[i] = j;
            numlist3[i] = j;
        }
        start = System.currentTimeMillis();
        Bubble(numlist2);
        end = System.currentTimeMillis();
        System.out.println(end - start);
        start = System.currentTimeMillis();
        Bubble1(numlist3);
        end = System.currentTimeMillis();
        System.out.println(end - start);
//        System.out.println(Arrays.toString(numlist2));
//        for(int i:numlist2){
//            System.out.print(i+" ");
//        }
//        System.out.println();
//        for(int i:numlist3){
//            System.out.print(i+" ");
//        }
    }

}
