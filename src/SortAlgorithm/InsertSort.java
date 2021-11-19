package SortAlgorithm;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class InsertSort {

    //插入排序，将一个数组看成两个列表，一个有序表（以排好顺序的元素），一个无序表（还未排序的元素）
    //排序的过程，就是不断的从无序表中取出第一个元素，并按顺序插入有序表的过程
    //由于用到链表，时间很长，所以尽量使用数组
    public static void Insert(int[] arr) {
        LinkedList<Integer> res = new LinkedList<>();
        res.add(0, arr[0]);
        for (int i = 1; i < arr.length; i++) {
            boolean flag = false;
            //arr有序部分长度为i，无序部分长度为arr.length-i，res长度为i
            for (int index = 0; index < i; index++) {
                if (res.get(index) > arr[i]) {
                    res.add(index, arr[i]);
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                res.add(i, arr[i]);
            }
        }
        for (int i = 0; i < arr.length; i++) {
            arr[i] = res.get(i);
        }
    }

    //插入排序，不用链表，使用数组，用时2710ms
    public static void InsertArr(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int index = i - 1;
//            while (true) {
//                if (arr[index+1] < arr[index]) {
//                    int x;
//                    x = arr[index];
//                    arr[index] = arr[index+1];
//                    arr[index+1] = x;
//                }
//                index--;
//                if(index<0){
//                    break;
//                }
//            }
            while (index >= 0 && arr[index + 1] < arr[index]) {
                int x;
                x = arr[index];
                arr[index] = arr[index + 1];
                arr[index + 1] = x;
                index--;
            }
        }
    }

    public static void main(String[] args) {
        int maxsize = 80000;
        int[] arr = new int[maxsize];
        int[] arr1 = new int[maxsize];
        long start;
        long end;
        for (int i = 0; i < arr.length; i++) {
            int j = (int) (Math.random() * 100000);
            arr[i] = j;
            arr1[i] = j;
        }
//        System.out.println("原始数组：");
//        for (int i : arr) {
//            System.out.print(i + " ");
//        }
//        System.out.println();
//        InsertArr(arr);
//        System.out.println("排序后：");
//        for (int i : arr) {
//            System.out.print(i + " ");
//        }
//        start=System.currentTimeMillis();
//        Insert(arr);
//        end=System.currentTimeMillis();
//        System.out.println(end-start);
        start = System.currentTimeMillis();
        InsertArr(arr1);
        end = System.currentTimeMillis();
        System.out.println(end - start);

    }
}
