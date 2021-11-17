package Stack;

import java.util.ArrayList;
import java.util.Scanner;

//中缀表达式
public class Calculator {
    public static void main(String[] args) {
        Scanner s=new Scanner(System.in);
        String expression=s.next();
        //创建数栈符号栈
        ArrayStack1 numStack=new ArrayStack1(expression.length());
        ArrayStack1 operStack=new ArrayStack1(expression.length());
        int index=0;

        while(index<expression.length()){
            char c=expression.substring(index,index+1).charAt(0);
            //如果是数，直接入数栈
            if(!operStack.isOper(c)){
//                numStack.push(c-48);
                //但上一句仍存在问题，如果是个多位数就会计算出错
                //因此需要判断当前index的下一位是否是数组，如果是就继续拼接，最后将完整数字push入栈
                String finalc = "" + c;
                while (true) {
                    //首先判断index是否已经遍历到最后，否则index+1超出范围会报错
                    if(index==expression.length()-1){
                        break;
                    }
                    //判断下一位如果是操作符就跳出循环，否则就遍历到下一位并拼接到字符串后面
                    if(operStack.isOper(expression.substring(index+1, index + 2).charAt(0))) {
                        break;
                    }
                    index++;
                    finalc += expression.substring(index, index + 1).charAt(0);
                }
                numStack.push(Integer.parseInt(finalc));
            }
            //如果是符号
            else{
                //解决首位是符号的问题
                if(index==0){
                    numStack.push(0);
                }
                //如果符号栈为空，就直接入符号栈
                if(operStack.isEmpty()){
                    operStack.push(c);
                }
                //如果符号栈不为空，和栈顶符号比较优先级
                else{
                    //如果当前符号优先级较高，直接入符号栈
                    if(operStack.priority(c)>operStack.priority(operStack.peek())){
                        operStack.push(c);
                    }
                    //如果当前操作符优先级小于等于栈顶操作符，数栈pop出两个，符号栈pop出一个，
                    // 计算后结果入数栈，当前符号入符号栈
                    else{
                        int num1=numStack.pop();  //该数字是后面数字；当符号栈不为空时，数栈中元素至少两个
                        int num2=numStack.pop();
                        int oper=operStack.pop();
                        int res=numStack.cal(num1,num2,oper);
                        numStack.push(res);
                        operStack.push(c);
                    }
                }
            }
            index++;
        }
        //表达式全部遍历完毕，顺序的从数栈和符号栈中pop出相应的数和符号，并计算
        while(!operStack.isEmpty()){
            int num1=numStack.pop();
            int num2=numStack.pop();
            int oper=operStack.pop();
            int res=numStack.cal(num1,num2,oper);
            numStack.push(res);
        }
        //最后输出结果
        System.out.println(numStack.pop());
    }
}

//在之前数组模拟栈的基础上添加新的功能，比较是否是运算法，比较运算法大小，计算方法
class ArrayStack1{
    private int top=-1;
    private int[] stack;
    private int maxSize;

    //定义构造函数
    public ArrayStack1(int maxsize){
        maxSize=maxsize;
        stack=new int[maxsize];
    }

    //判断栈是否已满
    public boolean isFull(){
        return top==maxSize-1;
    }

    //判断栈是否为空
    public boolean isEmpty(){
        return top==-1;
    }

    //数据入栈
    public void push(int value){
        if(isFull()){
            System.out.println("栈已满");
            return;
        }
        top++;
        stack[top]=value;
    }

    //出栈
    public int pop() throws RuntimeException{
        if(isEmpty()){
            throw new RuntimeException("栈已空");
        }
        int value=stack[top];
        stack[top]=0;
        top--;
        return value;
    }

    //显示栈（遍历）,需要从栈顶显示
    public void showStack(){
        if(isEmpty()){
            System.out.println("栈为空");
            return;
        }
        for(int i=top;i>=0;i--){
            System.out.println("stack["+i+"]="+stack[i]);
        }
    }

    //判断是否是运算符
    public boolean isOper(char val){
        return val=='+'||val=='-'||val=='*'||val=='/';
    }

    //比较运算法优先级,因为需要和栈顶符号比较，需要peek方法查看栈顶元素
    public int peek(){
        if(top==-1){
            throw new RuntimeException("栈为空");
        }
        return stack[top];
    }

    public int priority(int oper){
        //假定只有+ - * /
        if(oper=='*'||oper=='/'){
            return 1;
        }
        if(oper=='+'||oper=='-'){
            return 0;
        }
        else{
            return -1;
        }
    }

    //计算方法
    public int cal(int num1,int num2,int oper){
        switch(oper){
            case '+':
                return num1+num2;
            case '-':
                return num2-num1;   //注意先后顺序
            case '*':
                return num2*num1;
            case '/':
                return num2/num1;
            default:
                throw new RuntimeException("符号异常，请重新输入！");
        }
    }
}
