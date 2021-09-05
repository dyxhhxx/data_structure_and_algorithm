package sparsearray;

public class SparseArray {
    public static void main(String[] args) {

        //创建原始二维数组11*11
        //0表示没有棋子，1表示黑子，2表示蓝子
        int[][] chessArr1=new int[11][11];
        chessArr1[1][2]=1;
        chessArr1[2][3]=2;
        //输出原始二维数组
        System.out.println("原始的二维数组:");
        for(int[] row:chessArr1){
            for(int item:row){
                System.out.printf("%d\t",item);
            }
            System.out.println();
        }
        //将原始数组转化为二维数组
        //1.遍历原始二维数组，得到非0数据个数
        int sum=0;
        for(int i=0;i<chessArr1.length;i++){
            for(int j=0;j<chessArr1[0].length;j++){
                if(chessArr1[i][j]!=0){
                    sum+=1;
                }
            }
        }
        System.out.printf("非0数据个数为：%d\n",sum);
        //2.创建稀疏数组
        int sparseArr2[][]=new int[sum+1][3];
        sparseArr2[0][0]=chessArr1.length;
        sparseArr2[0][1]=chessArr1[0].length;
        sparseArr2[0][2]=sum;
        //3.从原始数组中找出非0元素，并填充至稀疏数组中
        int start=1;
        for(int i=0;i<chessArr1.length;i++){
            for(int j=0;j<chessArr1[0].length;j++){
                if(chessArr1[i][j]!=0){
                    sparseArr2[start][0]=i;
                    sparseArr2[start][1]=j;
                    sparseArr2[start][2]=chessArr1[i][j];
                    start++;
                }
            }
        }
        //输出稀疏数组
        System.out.println("转换后的稀疏数组是：");
        for(int[] row:sparseArr2){
            for(int item:row){
                System.out.printf("%d\t",item);
            }
            System.out.println();
        }

        //----------------------------------------------------------------------------------------------

        //将稀疏数组转化回原始数组
        //创建与原始数组大小相同的二维数组
        int chessArr2[][]=new int[sparseArr2[0][0]][sparseArr2[0][1]];
        //依次将稀疏数组中存放非数据，填充至新创建的二维数组
        for(int i=1;i<sparseArr2.length;i++){
            chessArr2[sparseArr2[i][0]][sparseArr2[i][1]]=sparseArr2[i][2];
        }
        //输出新得到的二维数组
        System.out.println("还原后的二维数组：");
        for(int[] row:chessArr2){
            for(int item:row){
                System.out.printf("%d\t",item);
            }
            System.out.println();
        }


    }
}
