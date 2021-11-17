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

    //将一个中缀表达式转化为一个List(中缀表达式由" "隔开）
    public static List<String> toInfixExpressionList1(String ori){
        List<String> list=new ArrayList<>();
        String[] str=ori.split(" ");
        for(String s:str){
            list.add(s);
        }
        return list;
    }

    //将一个中缀表达式转化为List（无" "隔开）
    public static List<String> toInfixExpressionList2(String ori){
        List<String> list=new ArrayList<>();
        char c;
        int index=0;
        while(true){
            //当遍历到String最后一位时退出
            if(index==ori.length()){
                break;
            }
//            c=ori.substring(index,index+1).charAt(0);
            c=ori.charAt(index);
            //如果是符号就直接add进List
            if(c=='+'||c=='-'||c=='*'||c=='/'||c=='('||c==')'){
                list.add(""+c);
            }
            //如果是数字，先判断后面是否同样是数字，防止多位数
            if(c<=57&&c>=48){
                String s=""+c;
                while(true){
                    if(index==ori.length()-1){
                        break;
                    }
                    if(ori.substring(index+1,index+2).charAt(0)<'0'||ori.substring(index+1,index+2).charAt(0)>'9'){
                        break;
                    }
                    char c1=ori.substring(index+1,index+2).charAt(0);
                    s+=c1;
                    index++;
                }
                list.add(s);
            }
            index++;
        }
        return list;
    }

    //将中缀表达式转换为后缀表达式
    public static List<String>  parseSuffixExpressionList(List<String> ls){
        List<String> ls1=new ArrayList<>();
        //定义两个栈 符号栈 和 中间结果栈(但中间结果栈需要逆序输出，用List集合）
        Stack<String> s1=new Stack<>();
        List<String> s2=new ArrayList<>();



        return ls1;
    }

    public static void main(String[] args) {

        //将一个中缀表达式转化为后缀表达式
        String ori="1 + ( ( 2 + 3 ) * 4 ) - 5";
        String ori1="1+((23+3)*4)-5";
        //先转化为List
        List<String> ls=toInfixExpressionList1(ori);
        List<String> ls1=toInfixExpressionList2(ori1);
        for(String s:ls1){
            System.out.println(s);
        }
        System.out.println(ls1);


        Scanner s=new Scanner(System.in);
        String suffixExpression="3 4 + 5 * 6 -";
        List<String> tempList=StringToList(suffixExpression);
        int res=cal(tempList);
        System.out.println(res);
        
    }
}

