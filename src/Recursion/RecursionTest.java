package Recursion;

public class RecursionTest {

    public static void test(int n){
        if(n>2){
            test(n-1);
        }
        System.out.println("n="+n);
    }


    //阶乘问题
    public static int factorial(int n){
        int res=1;
        if(n>1){
            res=n*factorial(n-1);
        }
        return res;
    }

    public static int factorial_1(int n){
        if(n==1){
            return 1;
        }else{
            return n*factorial(n-1);
        }
    }

    public static void main(String[] args) {
        test(4);
        System.out.println(factorial(4));
        System.out.println(factorial_1(4));
    }

}
