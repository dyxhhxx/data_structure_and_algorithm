package Algorithm;

//二分查找的 非递归 实现形式
public class BinarySearchNoRecur {

    public static void main(String[] args) {

        int[] arr={1,2,3,4,5,6,7,8,9};
        int i = binarySearch(arr, 1);
        System.out.println(i);

    }

    /**
     *
     * @param arr  被查找的数组
     * @param target  目标数
     * @return  下标，-1表示没有找到
     */
    public static int binarySearch(int[] arr,int target){

        int left=0;
        int right=arr.length-1;

        while(left<=right){
            int mid=(left+right)/2;
            if(arr[mid]==target){
                return mid;
            }
            else if(arr[mid]<target){
                left=mid+1;
            }else{
                right=mid-1;
            }
        }
        return -1;
    }

}
