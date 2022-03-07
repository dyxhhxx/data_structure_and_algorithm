package Algorithm;

public class KPMAlgorithm {

    public static void main(String[] args) {
        String str1="BBC ABCDAB ABCDABCDABDE";
        String str2="ABCDABD";
        int[] next=getKmpNext(str2);
        System.out.printf("str2在str1中开始出现重复的位置是%d",kmpSearch(str1,str2,next));
    }

    //KPM搜索算法
    /**
     *
     * @param str1 源字符串
     * @param str2 子字符串
     * @param next 子串部分匹配表
     * @return 源字符串的匹配位置，没有匹配到返回-1
     */
    public static int kmpSearch(String str1,String str2,int[] next){
        for(int i=0,j=0;i<str1.length();i++){
            //此步判断j是否需要重新定位，以及借助部分匹配表进行定位的操作
            //是KMP算法的核心
            //其目的就是避免重复比较str2已经比较过的子字符串
            while(j>0&&str1.charAt(i)!=str2.charAt(j)){
                j=next[j-1];
            }
            //需要在之前先判断是否需要重新定位j的位置
            if(str1.charAt(i)==str2.charAt(j)){
                j++;
            }
            if(j==str2.length()){
                return i-j+1;
            }
        }
        return -1;
    }

    //获取子串的next部分匹配表
    public static int[] getKmpNext(String str){
        int[] next=new int[str.length()];
        next[0]=0;
        for(int i=1,j=0;i<str.length();i++){
            //整个KMP算法的核心！！
            //目的是，使j定位到前缀字符串和后缀字符串的重合部分（若之前的重合部分不在重合则找重合部分内的重合子串，直至j=0说明不存在，退出循环）
            while(j>0&&str.charAt(i)!=str.charAt(j)){
                j=next[j-1];
            }
            if(str.charAt(i)==str.charAt(j)){
                j++;
            }
            next[i]=j;
        }
        return next;
    }

}
