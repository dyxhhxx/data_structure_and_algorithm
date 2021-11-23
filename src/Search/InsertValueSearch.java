package Search;

public class InsertValueSearch {

    static int count;
    public static void main(String[] args) {
        int[] arr=new int[100];
        for(int i=0;i<arr.length;i++){
            arr[i]=i;
        }
        System.out.println(insertvalueSearch(arr,0,arr.length-1,15));
        System.out.println(count);
    }

    //差值查找，与二分查找类似，但mid的定位方式不同，用比例尺更快的定位查找元素
    //当目标数组元素顺序线性成都较好，差值查找会比二分查找速度快很多（尤其是查找超大数组时）
    //但如果元素分布不均匀，差值查找效率偏低
    public static int insertvalueSearch(int[] arr,int left,int right,int val){
        count++;
        if(left>right||val<arr[left]||arr[val]>arr[right]){
            return -1;
        }
        //注意！！(val-arr[left])/(arr[right]-arr[left])是整除，如果写在前面就会变成0！！ 要把乘法写在前面
        int mid=left+(right-left)*(val-arr[left])/(arr[right]-arr[left]);
        if(arr[mid]>val){
            return insertvalueSearch(arr,left,mid-1,val);
        }else if(arr[mid]<val){
            return insertvalueSearch(arr,mid+1,right,val);
        }else{
            return mid;
        }
    }

}
