package Stack;

import java.util.*;

public class ReversePolishNotation {

    //将逆波兰表达式拆成一个ArrayList
    public static List<String> StringToList(String suffixExpression){
        String[] temparray=suffixExpression.split(" ");
        List<String> tempList=new ArrayList<>();
        for(int i=0;i<temparray.length;i++){
            tempList.add(temparray[i]);
        }
        return tempList;
    }

    //将得到的ArrayList进行计算
    public static int cal(List<String> tempList){
        Stack<String> stack=new Stack<String>();
        for(String s:tempList){
            //如果是多位数
            if(s.matches("\\d+")){
                stack.push(s);
            }
            //否则pop出两个数，进行运算
            else{
                int num1=Integer.parseInt(stack.pop());
                int num2=Integer.parseInt(stack.pop());
                if(s.equals("+")){
                    stack.push(""+(num1+num2));
                }
                else if(s.equals("-")){
                    stack.push(""+(num2-num1));
                }
                else if(s.equals("*")){
                    stack.push(""+(num1*num2));
                }
                else if(s.equals("/")){
                    stack.push(""+(num2/num1));
                }
                else{
                    throw new RuntimeException("符号错误");
                }
            }
        }
        return(Integer.parseInt(stack.pop()));
    }

    public static void main(String[] args) {

        Scanner s=new Scanner(System.in);
        String suffixExpression="3 4 + 5 * 6 -";
        List<String> tempList=StringToList(suffixExpression);
        int res=cal(tempList);
        System.out.println(res);

    }
}

