package Search;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BinarySearch {
    static int index=-1;
    static int count;
    public static void main(String[] args) {
        int[] arr=new int[100];
        for(int i=0;i<arr.length;i++){
            arr[i]=i;
        }
//        System.out.println(Arrays.toString(arr));
//        int index=binarySearch1(arr,0,arr.length-1,4);
//        if (index == -1) {
//            System.out.println("随机数组中没有值为15的元素");
//        } else {
//            System.out.println("该随机数组中第一个值为15的元素在第" + index + "位");
//        }
        int[] arr1={1,2,3,3,4,5};
        List<Integer> intlist=binarySearchPro1(arr,0,arr1.length-1,15);
        Integer[] res= intlist.toArray(new Integer[intlist.size()]);
        Object[] res1=intlist.toArray();
        System.out.println(Arrays.toString(res));
        System.out.println(Arrays.toString(res1));
        System.out.println(count);
    }

    //二分查找的对象，必须是一个有序数组
    public static int binarySearch(int[] arr,int left,int right,int val){
        int l=left;
        int r=right;
        int mid=(l+r)/2;
        if(l<=r) {
            if (arr[mid] == val) {
                index=mid;
                 //如果用while循环，必须加上break！！否则回溯时因为l一直小于r，while循环无法退出！！
            }
            else if (arr[mid] < val) {
                binarySearch(arr, mid + 1, r, val);
            }
            else if(arr[mid]>val){
                binarySearch(arr,l,mid-1,val);
            }
        }
        return index;
    }


    //更简单的实现方法
    //上面自己写的方法存在问题如下：
    //没必要定义局部变量l，r，因为对他们没有操作
    //回溯的代码没有想清楚，即应该return返回值即可，没必要定义全局变量保存index
    public static int binarySearch1(int[] arr,int left,int right,int val){
        int mid=(left+right)/2;
        if(left<=right){
            if(arr[mid]==val){
                return mid;
            }
            if(arr[mid]<val){
                return binarySearch1(arr,mid+1,right,val);
            }
            else{
                return binarySearch1(arr,left,mid-1,val);
            }
        }else {
            return -1;
        }
    }

    //目标数组中有多个目标元素时的二分查找方法
    public static List<Integer> binarySearchPro(int[] arr, int left, int right, int val){
        List<Integer> intlist=new ArrayList<>();
        int mid=(left+right)/2;
        if(left<=right){
            if(arr[mid]==val){
                intlist.add(mid);
                for(int i=mid+1;i<=right;i++){
                    if(arr[i]==val){
                        intlist.add(i);
                    }
                }for(int i=mid-1;i>=left;i--){
                    if(arr[i]==val){
                        intlist.add(i);
                    }
                }
                return intlist;
            }
            if(arr[mid]<val){
                return binarySearchPro(arr, mid+1, right, val);
            }
            else{
               return binarySearchPro(arr, left, mid-1, val);
            }
        }else{
            return new ArrayList<Integer>();
        }

    }
    public static List<Integer> binarySearchPro1(int[] arr,int left,int right,int val){
        count++;
        if(left>right){
            return new ArrayList<Integer>();
        }
        int mid=(left+right)/2;
        if(val>arr[mid]){
            return binarySearchPro1(arr,mid+1,right,val);
        }
        if(val<arr[mid]){
            return binarySearchPro1(arr,left,mid-1,val);
        }else{
            List<Integer> intlist=new ArrayList<>();
            int temp=mid;
            while(true){
                if(temp>right||arr[temp]!=val){
                    break;
                }
                intlist.add(temp);
                temp++;
            }
            temp=mid-1;
            while(true){
                if(temp<left||arr[temp]!=val){
                    break;
                }
                intlist.add(temp);
                temp--;
            }
            return intlist;
        }
    }
}
