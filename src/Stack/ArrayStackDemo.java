package Stack;

import java.util.Scanner;

public class ArrayStackDemo {
    public static void main(String[] args) {
        ArrayStack as=new ArrayStack(5);
        String key=" ";
        Scanner s=new Scanner(System.in);
        boolean loop=true;

        while(loop){
            System.out.println("show:显示栈");
            System.out.println("exit:退出程序");
            System.out.println("push:添加元素入栈");
            System.out.println("pop:取元素出栈");
            System.out.println("请输入您的选择");
            key=s.next();
            switch(key){
                case "show":
                    as.showStack();
                    break;
                case "exit":
                    s.close();  //记得关闭
                    loop=false;
                    break;
                case "push":
                    System.out.println("请输入要入栈的元素：");
                    int value=s.nextInt();
                    as.push(value);
                    break;
                case "pop":
                    try{
                        System.out.println(as.pop());
                    }catch(Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                default:
                    System.out.println("请您输入上述指令!");
                    break;
            }
            System.out.println("-----------------------");

        }
    }
}

class ArrayStack{
    private int top=-1;
    private int[] stack;
    private int maxSize;

    //定义构造函数
    public ArrayStack(int maxsize){
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
    public int pop(){
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



}
