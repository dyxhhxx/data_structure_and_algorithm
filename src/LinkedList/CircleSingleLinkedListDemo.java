package LinkedList;

import Queue.CircleArrayQueue;
import org.w3c.dom.css.CSSRuleList;

import java.util.Scanner;

public class CircleSingleLinkedListDemo {

    public static void main(String[] args) {
        CircleSingleLinkedList cssl=new CircleSingleLinkedList();
        cssl.addNode(5);
        cssl.showList();
        System.out.printf("小孩个数是：%d\n",cssl.getSize());
        System.out.println("出圈顺序：");
        cssl.JosephuMethod(2,2);

//        Scanner s=new Scanner(System.in);
//        System.out.println("请输入小孩个数：");
//        int size=s.nextInt();
//        System.out.println("请输入开始位置：");
//        int start=s.nextInt();
//        System.out.println("请输入报数个数：");
//        int length=s.nextInt();
//        CircleSingleLinkedList cssl1=new CircleSingleLinkedList();
//        cssl1.addNode(size);
//        cssl1.showList();
//        System.out.println("---------Josephu----------");
//        cssl1.JosephuMethod(start,length);

    }
}

//创建环形单向链表
class CircleSingleLinkedList{
    Boy first;

    //添加节点
    public void addNode(int num){
        if(num<1){
            System.out.println("boy数量必须大于1");
        }
        Boy cur=null;
        for(int i=1;i<=num;i++){
            Boy boy=new Boy(i);
            if(i==1){
                first=boy;
                first.next=first;
                cur=first;
            }
            else{
                cur.next=boy;
                boy.next=first;
                cur=boy;  //最后指针移动
            }
        }
    }

    //遍历环形链表
    public void showList(){
        if(first==null){
            System.out.println("链表为空");
            return;
        }
        Boy cur=first;
        while(true){
            System.out.println(cur.no);
            if(cur.next==first){
                break;
            }
            cur=cur.next;
        }
    }

    //链表长度，但完全没必要，因为
    public int getSize(){
        if(first==null){
            return 0;
        }
        Boy temp=first;
        int sum=0;
        while(true){
            sum++;
            if(temp.next==first){  //此处不可以用temp.next=temp.next.next代替
                break;
            }
            temp=temp.next;
        }
        return sum;
    }

    //Josephu出圈方法
    public void JosephuMethod(int start,int length){
        if(getSize()==0||start>getSize()||start<1){
            System.out.println("开始位置不在正常范围内，请重新设置start位置");
            return;
        }
        Boy temp=first;
        //让temp指向first的上一个位置，便于之后操作
        while(true){
            if(temp.next==first){
                break;
            }
            temp=temp.next;
        }
        //start=1时temp不需要移动
        if(start>1) {
            for (int i = 2; i <= start; i++) {
                temp = temp.next;
                first=first.next;
            }
        }
        while(true){
            if(getSize()==1){
                System.out.println(temp.no);
                break;
            }
            //让temp向后移动length-1的位置
            for(int i=0;i<length-1;i++){
                temp=temp.next;
                first=first.next;
            }
            //找出temp下一个位置的no，并使其出链表
            System.out.println(first.no);
            first=first.next;
            temp.next=temp.next.next;
        }
    }

}

class Boy{
    int no;
    Boy next;
    public Boy(int no){
        this.no=no;
    }

}
