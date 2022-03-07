package Algorithm;

import java.util.Arrays;

//动态规划算法
public class Dynamic {

    public static void main(String[] args) {

        int[] w={1,4,3};  //物品重量
        int[] val={1500,3000,2000};  //物品价值
        int m=4;  //背包容量
        int n=w.length;  //物品数量
        int[][] v=new int[n+1][m+1];  //v[i][j]表示在前i个物品中能够装入容量为j的背包中的最大价值
        int[][] path=new int[n+1][m+1];  //记录商品放入情况

        //初始化v的第一行和第一列
        for(int i=0;i<v.length;i++){
            v[i][0]=0;  //第一列置0
        }
        for(int i=0;i<v[0].length;i++){
            v[0][i]=0;  //第一行置0
        }

        //动态规划处理
        for(int i=1;i<v.length;i++){  //遍历物品
            for(int j=1;j<v[i].length;j++){  //遍历背包重量
                if(j<w[i-1]){  //当前物品一个也放不进去时，只能让容量为j背包的最大价值为上一个物品时相同容量的背包价值
                    v[i][j]=v[i-1][j];
                }else{  //可以放进去时，开始比较
                    int temp=val[i-1]+v[i-1][j-w[i-1]];  //放入当前物品
                    int ori=v[i-1][j];
//                    v[i][j]=Math.max(temp,ori);
                    //为了将放置结果记录在path中，需要用一个if-else判断
                    if(temp>ori){
                        v[i][j]=temp;
                        path[i][j]=1;
                    }else{
                        v[i][j]=ori;
                    }
                }
            }
        }

        for(int[] arr:v){
            System.out.println(Arrays.toString(arr));
        }
//        System.out.println();
//        for(int[] arr:path){
//            System.out.println(Arrays.toString(arr));
//        }

        int i=path.length-1; //最大行下标,即第几个物品
        int j=path[0].length-1; //最大列下标，即当前背包重量
        while(i>0&&j>0){
            if(path[i][j]==1){
                System.out.printf("第%d个商品放进背包\n",i);
                j-=w[i-1];
            }
            i--;
        }

    }




}
