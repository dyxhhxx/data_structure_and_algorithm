package Recursion;

public class Queen8 {

    static int[] arr=new int[8];  //此处如果不将数组设置为静态属性，需将下面的方法也设为非静态方法
    static int countPrint;
    static int countCheck;

    public static void main(String[] args) {
        put(0);
        System.out.println(countPrint);
        System.out.println(countCheck);
    }

    //判断是否满足八皇后条件
    public static boolean check(int n){
        countCheck++;
        for(int i=0;i<n;i++){
            if(arr[i]==arr[n]||Math.abs(n-i)==Math.abs(arr[n]-arr[i])){   //分别为在同一列和在同一条斜线
                return false;
            }
        }
        return true;
    }

    //从第n个（第n行）开始放皇后
    public static void put(int n){
        if(n==8){
            print();
            return;
        }
        for(int i=0;i<8;i++){
            arr[n]=i;
            if(check(n)){
                put(n+1);
            }
        }
    }

    //打印八皇后队列
    public static void print(){
        for(int i:arr){
            System.out.print(i+" ");
        }
        System.out.println();
        countPrint++;
    }
}
