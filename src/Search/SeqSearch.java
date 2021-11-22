package Search;

import java.util.Arrays;

public class SeqSearch {
    public static void main(String[] args) {
        int[] arr = new int[20];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 30);
        }
        System.out.println(Arrays.toString(arr));
        int index = seqSearch(arr, 15);
        if (index == -1) {
            System.out.println("随机数组中没有值为15的元素");
        } else {
            System.out.println("该随机数组中第一个值为15的元素在第" + index + "位");
        }
        System.out.println("-------------------------");
        seqSearch1(arr, 15);
    }

    //返回找到的第一个元素
    public static int seqSearch(int[] arr, int val) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == val) {
                return i;
            }
        }
        return -1;
    }

    //打印出多个目标元素
    public static void seqSearch1(int[] arr, int val) {
        boolean flag = false;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == val) {
                flag = true;
                System.out.println("该随机数组中值为15的元素在第" + i + "位");
            }
        }
        if (!flag) {
            System.out.println("随机数组中没有值为15的元素");
        }
    }


}

