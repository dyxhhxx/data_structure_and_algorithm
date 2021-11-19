package SortAlgorithm;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class InsertSort {

    //插入排序，将一个数组看成两个列表，一个有序表（以排好顺序的元素），一个无序表（还未排序的元素）
    //排序的过程，就是不断的从无序表中取出第一个元素，并按顺序插入有序表的过程
    public static void Insert(int[] arr) {
        LinkedList<Integer> res=new LinkedList<>();
        res.add(0,arr[0]);
        for (int i = 1; i < arr.length; i++) {
            boolean flag=false;
            //arr有序部分长度为i，无序部分长度为arr.length-i，res长度为i
            for(int index=0;index<i;index++){
                if(res.get(index)>arr[i]){
                    res.add(index,arr[i]);
                    flag=true;
                    break;
                }
            }
            if(!flag){
                res.add(i,arr[i]);
            }
        }
        for(int i=0;i<arr.length;i++){
            arr[i]=res.get(i);
        }
    }

    public static void main(String[] args) {
        int[] arr=new int[10];
        for(int i=0;i<arr.length;i++){
            arr[i]=(int)(Math.random()*100);
        }
        System.out.println("原始数组：");
        for(int i:arr){
            System.out.print(i+" ");
        }
        System.out.println();
        Insert(arr);
        System.out.println("排序后：");
        for(int i:arr){
            System.out.print(i+" ");
        }
    }
}
