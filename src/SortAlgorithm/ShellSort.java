package SortAlgorithm;

import java.util.Arrays;

public class ShellSort {

    //希尔排序(Shell排序），在插入排序的基础上，先将整个大的数组根据步长分为几个小的数组，然后将步长由大到小
    //插入法  80000个数据9ms排序完成
    public static void shellInsert(int[] arr){
        //length为步长(步长即为有多少组），每次缩小一半
        for(int length=arr.length/2;length>=1;length/=2){
            //根据步长，开始插入排序
            for(int i=length;i<arr.length;i++) {
                int index=i;
                int val=arr[index];
                while(index>=length&&arr[index-length]>arr[index]){
                    arr[index]=arr[index-length];
                    index-=length;
                }
                arr[index]=val;
            }
        }
    }

    //交换法   80000个数据7000ms排序完成
    public static void shellExchange(int[] arr){
        int x;
        //length为步长(步长即为有多少组），每次缩小一半
        for(int length=arr.length/2;length>=1;length/=2){
            //根据步长，开始插入排序
            for(int i=length;i<arr.length;i++) {
                for(int index=i;index>=length;index-=length){
                    if(arr[index-length]>arr[index]){
                        x=arr[index];
                        arr[index]=arr[index-length];
                        arr[index-length]=x;
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        int maxsize=8000000;
        int[] arr=new int[maxsize];
        int[] arr1=new int[maxsize];
        long start;
        long end;
        for(int i=0;i<arr.length;i++){
            int j=(int)(Math.random()*10000000);
            arr[i]=j;
            arr1[i]=j;
        }
//        System.out.println(Arrays.toString(arr));
        start=System.currentTimeMillis();
        shellInsert(arr);
        end=System.currentTimeMillis();
        System.out.println(end-start);
//        System.out.println(Arrays.toString(arr));
//        System.out.println(Arrays.toString(arr1));
//        start=System.currentTimeMillis();
//        shellExchange(arr1);
//        end=System.currentTimeMillis();
//        System.out.println(end-start);
//        System.out.println(Arrays.toString(arr1));
    }
}
