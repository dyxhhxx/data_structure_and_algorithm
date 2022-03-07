package Algorithm;

//暴力匹配算法，思路简单；但会产生大量的回溯，每次只移动一位，若是不匹配，移动到下一位接着判断，浪费了大量的时间
public class ViolenceMatch {

    public static void main(String[] args) {
        String str1="BBC ABCDAB ABCDABCDABCDABDE";
        String str2="ABCDABD";
        System.out.println(violenceMatch(str1,str2));
    }

    //暴力匹配算法实现
    public static int violenceMatch(String str1,String str2){
        int i=0;
        int j=0;
        char[] char1=str1.toCharArray();
        char[] char2=str2.toCharArray();
        while(i<char1.length&&j<char2.length){
            if(char1[i]==char2[j]){
                i++;
                j++;
            }else{
                i-=j-1;
                j=0;
            }
        }
        if(j==char2.length){  //注意此处是j==char2.length不是char2.length-1
            return i-j;
        }else{
            return -1;
        }
    }

}
