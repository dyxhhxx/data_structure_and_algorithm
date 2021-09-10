package LinkedList;

import java.text.ParseException;
import java.util.Stack;

public class SingleLinkedListDemo {

    public static void main(String[] args) {
        //先创建节点
        HeroNode hero1=new HeroNode(1,"宋江","及时雨");
        HeroNode hero2=new HeroNode(3,"卢俊义","玉麒麟");
        HeroNode hero3=new HeroNode(6,"吴用","智多星");
        HeroNode hero4=new HeroNode(9,"林冲","豹子头");
        //创建链表
        SingleLickedList sll=new SingleLickedList();
        //将四个节点加入链表
        sll.addNode(hero1);
        sll.addNode(hero2);
        sll.addNodebyOrder(hero4);
        sll.addNodebyOrder(hero3);
        //显示
        sll.list();
        //修改节点信息
        HeroNode newhero2=new HeroNode(2,"卢员外","玉面麒麟");
        sll.update(newhero2);
        System.out.println("修改后的链表是：");
        sll.list();  //检查
        //删除节点
        SingleLickedList sll1=new SingleLickedList();
        sll1.deleteNode(1);
//        sll.deleteNode(5);
////        sll.deleteNode(1);
////        sll.deleteNode(2);
////        sll.deleteNode(3);
////        sll.deleteNode(4);
//        System.out.println("删除节点后的链表是：");
//        sll.list();
        //链表长度（节点个数）
        System.out.printf("链表sll的长度为%d个节点\n",sll.getLength());
        System.out.printf("链表sll1的长度为%d个节点\n",sll1.getLength());

        //查找倒数第二个节点
        System.out.println("查找倒数第2个节点为：");
        System.out.println(sll.findLastIndexNode(2));

        //链表倒序
        sll.reverseList();
        System.out.println("倒序后的链表是：");
        sll.list();

        //反向打印
        System.out.println("利用从尾遍历反向打印的结果是：");
        sll.reverseErgitoc();
        System.out.println("利用Stack栈反向打印的结果是：");
        sll.reverseByStack();

        //新建两个简单的链表，并将其合并
        HeroNode hero5=new HeroNode(0,"","");
        HeroNode hero6=new HeroNode(5,"","");
        HeroNode hero7=new HeroNode(6,"","");
        HeroNode hero8=new HeroNode(8,"","");
        HeroNode hero9=new HeroNode(10,"","");
        SingleLickedList sll2=new SingleLickedList();
        sll2.addNode(hero5);
        sll2.addNode(hero6);
        sll2.addNode(hero7);
        sll2.addNode(hero8);
        sll2.addNode(hero9);
        System.out.println("新建的链表是：");
        sll2.list();
        sll.reverseList();
        sll.combineList(sll2);
        System.out.println("合并后的链表是：");
        sll.list();


    }

}

//定义SingleLickedList,管理英雄
class SingleLickedList{
    //先初始化一个头节点，头节点不动，不存放具体数据
    public HeroNode head=new HeroNode(0,"","");

    //在链表末尾添加新节点
    public void addNode(HeroNode heroNode){
        //因为head不能移动，需要借助辅助指针temp遍历链表
        HeroNode temp=head;
        //将temp移动到当前链表的最后一个节点
        while(true){
            if(temp.next==null){
                break;
            }
            temp=temp.next;
        }
        //将旧链表的尾部next连接到新的节点
        temp.next=heroNode;
    }

    //第二种添加节点方式，按照序号添加
    public void addNodebyOrder(HeroNode heroNode){
        HeroNode temp=head;
        boolean flag=false;  //表示编号是否存在，默认为false
        //找到heroNode要插入的位置
        while(true){
            if(temp.next==null){
                break;
            }
            //temp下一个节点的no大于heroNode代表已经找到插入位置
            if(heroNode.no<temp.next.no){
                break;
            }
            if(heroNode.no==temp.next.no){
                flag=true;
                break;
            }
            temp=temp.next;
        }
        if (flag) {
            System.out.printf("准备插入的英雄编号%d已存在，无法加入\n",heroNode.no);
        }
        else{
            heroNode.next=temp.next;
            temp.next=heroNode;
        }
    }

    //修改节点信息，no不变
    public void update(HeroNode newheroNode){
        HeroNode temp=head.next;
        if(head.next==null){
            System.out.println("链表为空");
            return;
        }
        boolean flag=false; //石佛是否找到newheroNode
        while(true){
            if(temp==null){
                break;  //已经遍历完列表，退出循环
            }
            if(temp.no== newheroNode.no){
                flag=true; //已找到
                break;
            }
            temp=temp.next;
        }
        if(flag) {  //找到节点
            temp.name= newheroNode.name;
            temp.nickname= newheroNode.nickname;
        }
        else{  //没有找到节点
            System.out.printf("该节点序号%d不存在",newheroNode.no);
        }
    }

    //删除节点
    public void deleteNode(int pos){
        if(head.next==null){
            System.out.println("链表为空");
            return;
        }
        HeroNode temp=head;
        boolean flag=false;  //是否找到节点
        while(true){
            if(temp.next==null){
                break;
            }
            if(temp.next.no==pos){
                flag=true;
                break;
            }
            temp=temp.next;
        }
        if(flag){
            temp.next=temp.next.next;
        }
        else{
            System.out.printf("编号%d的节点不存在\n",pos);
        }
    }

    //遍历链表
    public void list(){
        //判断列表是否为空
        if(head.next==null){
            System.out.println("链表为空");
            return;
        }
        HeroNode temp=head.next;
        while(true){
            if(temp==null){
                break;
            }
            System.out.println(temp);
            temp=temp.next;
        }
    }

    //获取链表中节点个数
    public int getLength(){
        HeroNode temp=head;
        int sum=0;
        while(true){
            if(temp.next==null){
                break;
            }
            sum++;
            temp=temp.next;
        }
        return sum;
    }

    //查找单链表中倒数第k个节点
    public HeroNode findLastIndexNode(int k){
        int size=getLength();
        if(k<=0||k>size){
            return null;
        }
        HeroNode temp=head.next;
        for(int i=0;i<size-k;i++){
            temp=temp.next;
        }
        return temp;
    }

    //链表倒序
    public void reverseList(){
        //判断链表是否为空，或链表是否只有一个节点
        if(head.next==null||head.next.next==null){
            System.out.print("链表为空，或链表只有一个节点");
            return;
        }
        //准备工作
        HeroNode temp=head.next;
        HeroNode next=null;
        HeroNode reversehead=new HeroNode(0,"","");
        //开始反转
        while(temp!=null){
            next=temp.next;
            temp.next=reversehead.next;
            reversehead.next=temp;
            temp=next;
        }
        head.next=reversehead.next;
    }

    //从尾到头打印单链表（要求：方式1：反向遍历；2：Stack栈）
    //方式1：反向遍历
    public void reverseErgitoc(){
        if(getLength()==0){
            System.out.println("链表为空");
            return;
        }
       for(int i=1;i<=getLength();i++){
           System.out.println(findLastIndexNode(i));
       }
    }
    //方式2：Stack栈
    public void reverseByStack(){
        if(head.next==null){
            System.out.println("链表为空");
            return;
        }
        Stack<HeroNode> stack=new Stack();
        HeroNode temp=head.next;
        while(true){
            if(temp==null){
                break;
            }
            stack.add(temp);
            temp=temp.next;
        }
        while(stack.size()>0){
            System.out.println(stack.pop());
        }
    }

    //合并两个有序的单链表，合并之后的链表依然有序
    public void combineList(SingleLickedList l){
        HeroNode temp2=l.head.next;
        HeroNode temp1=head;
        HeroNode next=null;
        while(temp2!=null){
            //操作temp1，找到插入位置
            while(true) {
                if(temp1.next==null){
                    break;
                }
                if (temp2.no <= temp1.next.no) {
                    break;
                }
                temp1 = temp1.next;
            }
            //操作temp2，将temp2节点插入定位的位置，并继续向后移动
            if(temp1.next!=null&&temp2.no==temp1.next.no){  //如果链表2最后的数据编号是最大的，原来的链表就会遍历到最后一个，它的next指针为null
                System.out.printf("新链表的编号%d重复，不予合并，",temp2.no);
                temp2=temp2.next;
            }
            else {
                next = temp2.next;
                temp2.next = temp1.next;
                temp1.next = temp2;
                temp2 = next;
            }
        }
    }
}








//先定义节点HeroNode，每个HeroNode对象就是一个节点
class HeroNode{
    public int no;
    public String name;
    public String nickname;
    public HeroNode next;
    //节点的构造器
    public HeroNode(int no,String name,String nickname){
        this.no=no;
        this.name=name;
        this.nickname=nickname;
    }
    //重写toString方法
    @Override
    public String toString(){
        return "HeroNode[no="+no+",name="+name+",nickname="+nickname+"]";
    }
}
