package Search;

import java.util.Arrays;

public class FibonacciSearch {
    public static void main(String[] args) {
        int[] arr = {1, 2, 4, 55, 66, 190};
        System.out.println(fibonacciSearch(arr, 8));

    }

    //先写一个方法生成斐波那契数列
    public static int[] Fibonacci(int length) {
        int[] arr = new int[length];
        arr[0] = 1;
        arr[1] = 1;
        for (int i = 2; i < arr.length; i++) {
            arr[i] = arr[i - 1] + arr[i - 2];
        }
        return arr;
    }

    //斐波那契查找，原理同样与二分查找类似，借助斐波那契数列分割目标队列
    public static int fibonacciSearch(int[] arr, int val) {
        int[] f = Fibonacci(20);
        int l;
        int r;
        int mid = 0;
        boolean flag = false;
        //先获取第一次切分时，要用第几个斐波那契数(该数应为斐波那契数列第一个大于目标数组长度的数）
        int k = 0;
        while (f[k] - 1 < arr.length) {
            k++;
        }
        //因为目标数组的长度小于等于f[k]-1,应该填充目标数组使其长度等于f[k]-1
        int[] temp = Arrays.copyOf(arr, f[k] - 1);    //Arrays.copyof()粘贴时，如果后面的长度大于前面的数组长度，会补0
        for (int i = arr.length; i < temp.length; i++) {
            temp[i] = temp[arr.length - 1];
        }
        l = 0;
        r = arr.length - 1;
        while (l <= r) {
            mid = l + f[k - 1] - 1;
            if (temp[mid] > val) {
                r = mid - 1;
                k -= 1;
            } else if (temp[mid] < val) {
                l = mid + 1;
                k -= 2;
            } else {   //找到目标元素
                //但此处需要考虑如果目标元素是在最后的情况
                if (mid <= r) {
                    return mid;
                } else {
                    return r;
                }
            }
        }
        return -1;
    }
}
