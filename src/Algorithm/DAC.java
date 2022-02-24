package Algorithm;

//分治算法
public class DAC {

    static int count=0;

    public static void main(String[] args) {
        hanoittower(3,'A','B','C');
        System.out.println(count);
    }

    //分治算法实现汉诺塔
    public static void hanoittower(int num,char a,char b,char c){
        count++;
        if(num==1){  //只有一个盘
            System.out.println("第1个盘从"+a+"->"+c);
        }else{  //大于一个盘时，总是可以看作两个盘：最下面的一个盘和上面的所有盘
            //1先把最上面的盘A->B，移动过程中会用到C
            hanoittower(num-1,a,c,b);
            //2把最下面的盘A->C
            System.out.println("第"+num+"个盘从"+a+"->"+c);
            //3把B所有的盘移动到C
            hanoittower(num-1,b,a,c);
        }
    }

}

