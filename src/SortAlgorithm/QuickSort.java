package SortAlgorithm;

import java.util.Arrays;

public class QuickSort {

    public static void main(String[] args) {
        int maxsize = 8000000;
        long start;
        long end;
        int[] arr = new int[maxsize];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 10000000);
        }
//        System.out.println(Arrays.toString(arr));
        start = System.currentTimeMillis();
        Quick(arr, 0, arr.length - 1);
        end = System.currentTimeMillis();
        System.out.println(end - start);
//        System.out.println(Arrays.toString(arr));
    }

    //快速排序，80000个元素用时12ms
    public static void Quick(int[] arr, int left, int right) {
        int pivot = arr[(left + right) / 2];
        int l = left;
        int r = right;
        int x;
        while (l < r) {
            while (arr[l] < pivot) {
                l++;
            }
            while (arr[r] > pivot) {
                r--;
            }
            x = arr[l];
            arr[l] = arr[r];
            arr[r] = x;
            //没有下面的代码index会到-1（当l和r到数组边界时）
            if (l >= r) {
                break;
            }
            //防止两边都出现与pivot相同的值，会导致死循环
            if (arr[l] == pivot) {
                r--;
            }
            if (arr[r] == pivot) {
                l++;
            }
        }
        l++;
        r--;
        if (r > left) {
            Quick(arr, left, r);
        }
        if (l < right) {
            Quick(arr, l, right);
        }
    }
}
