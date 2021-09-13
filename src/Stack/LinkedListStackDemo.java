package Stack;

import java.util.Scanner;

public class LinkedListStackDemo {
    public static void main(String[] args) {
        //创建链表
        LinkedListStack lls=new LinkedListStack();
        String key=" ";
        boolean loop=true;
        Scanner s=new Scanner(System.in);

        while(loop){
            System.out.println("show:显示栈");
            System.out.println("exit:退出程序");
            System.out.println("push:添加数据入栈");
            System.out.println("pop:取一个数据出栈");
            System.out.println("请输入您的选择：");
            key=s.next();
            switch(key){
                case "show":
                    lls.show();
                    break;
                case "exit":
                    loop=false;
                    System.out.println("程序退出");
                    break;
                case "push":
                    System.out.println("请输入一个数据:");
                    int num=s.nextInt();
                    lls.push(num);
                    break;
                case "pop":
                    try{
                        System.out.println(lls.pop());
                    }catch(Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                default:
                    System.out.println("请输入正确的指令");
                    break;
            }
            System.out.println("----------------------------");
        }
    }
}

//用链表模拟栈
class LinkedListStack{
    ValueNode head=new ValueNode(0);

    //是否为空
    public boolean isEmpty(){
        return head.next==null;
    }

    //添加节点（入栈），入栈时新的节点放在head后面
    public void push(int value){
        ValueNode vn=new ValueNode(value);
        if(isEmpty()){
            head.next=vn;
        }
        else{
            ValueNode next=head.next;
            head.next=vn;
            vn.next=next;
        }
    }

    //出栈
    public int pop(){
        if(isEmpty()){
            throw new RuntimeException("栈已空");
        }
        else{
            int num=head.next.value;
            head.next=head.next.next;
            return num;
        }
    }

    //遍历，即链表顺序输出
    public void show(){
        if(isEmpty()){
            System.out.println("链表为空");
        }
        ValueNode temp=head.next;
        while (true) {
            if(temp==null){
                break;
            }
            System.out.println(temp.value);
            temp=temp.next;
        }
    }




}

//链表节点
class ValueNode{
    int value;
    ValueNode next;
    public ValueNode(int value){
        this.value=value;
    }
}
