package SortAlgorithm;

import java.util.Arrays;

public class MergeSort {
    public static void main(String[] args) {
        int maxsize = 80000;
        int[] arr = new int[maxsize];
        int[] temp = new int[arr.length];
        long start;
        long end;
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 10000000);
        }
//        System.out.println(Arrays.toString(arr));
        start = System.currentTimeMillis();
        Merge1(arr, 0, arr.length - 1, temp);
        end = System.currentTimeMillis();
        System.out.println(end - start);
//        System.out.println(Arrays.toString(arr));
    }

    //80000个数据归并排序用时9ms,每次调用方法再创建temp的话用时600+ms
    //归并排序，分为归和并两部，先写并的方法
    public static void Merge2(int[] arr, int left, int mid, int right, int[] temp) {
        int index = left;
        int l1 = left;
        int l2 = mid + 1;
        while (l1 <= mid && l2 <= right) {
            if (arr[l1] < arr[l2]) {
                temp[index] = arr[l1];
                l1++;
            } else {
                temp[index] = arr[l2];
                l2++;
            }
            index++;
        }
        //总有一半还有剩余元素，将其填充到temp后面
        while (l1 <= mid) {
            temp[index] = arr[l1];
            l1++;
            index++;
        }
        while (l2 <= right) {
            temp[index] = arr[l2];
            l2++;
            index++;
        }
        //将temp拷贝到arr
//        for(int i=0;i<arr.length;i++){
//            arr[i]=temp[i];
//        }
        //不是每次都拷贝所有，而是从当前的left拷贝到当前的right
        for (int i = left; i <= right; i++) {
            arr[i] = temp[i];
        }
    }

    //接下来写归的方法
    public static void Merge1(int[] arr, int left, int right, int[] temp) {
        int mid = (left + right) / 2;
//        if(mid>0){
//            Merge1(arr,left,mid,temp);
//        }
//        if(mid>0){
//            Merge1(arr,mid+1,right,temp);
//        }
//        Merge2(arr,left,mid,right,temp);
        //上面的算法是错误的，因为右边的数组会死循环
        if (left < right) {
            //前两部Merge1分组开始递归，当分到最小分组即每组两个元素时，排序后开始回溯Merge1并合并数组
            Merge1(arr, left, mid, temp);
            Merge1(arr, mid + 1, right, temp);
            Merge2(arr, left, mid, right, temp);
        }
    }
}
