package LinkedList;

import java.util.Stack;

//演示栈Stack的基本使用
public class TestStack {
    public static void main(String[] args) {
        Stack<String> stack=new Stack();
        //入栈
        stack.add("宋江");
        stack.add("卢俊义");
        stack.add("吴用");
        stack.add("林冲");

        //出栈
        while(stack.size()>0){
            System.out.println(stack.pop());
        }
    }

}
