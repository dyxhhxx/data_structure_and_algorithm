package LinkedList;

public class DoubleLinkedListDemo {

    public static void main(String[] args) {
        //定义节点和链表
        HeroNode2 hero1=new HeroNode2(1,"宋江","及时雨");
        HeroNode2 hero2=new HeroNode2(2,"卢俊义","玉麒麟");
        HeroNode2 hero3=new HeroNode2(3,"吴用","智多星");
        HeroNode2 hero4=new HeroNode2(4,"林冲","豹子头");
        //创建链表
        DoubleLinkedList dll=new DoubleLinkedList();
//        DoubleLinkedList dll1=new DoubleLinkedList();
        //添加节点(按照添加顺序）
        dll.addNode(hero1);
        dll.addNode(hero2);
        dll.addNode(hero3);
        dll.addNode(hero4);
        System.out.println("按照添加顺序添加节点：");
        dll.showList();
        //修改节点
        HeroNode2 hero3_=new HeroNode2(3,"鲁智深","花和尚");
        dll.updateNode(hero3_);
        System.out.println("修改节点后的链表为：");
        dll.showList();
        //删除节点
        dll.deleteNode(4);
        System.out.println("删除节点后的链表为：");
        dll.showList();


    }

}

//定义DoubleLinkedList，即双向链表对象
class DoubleLinkedList{
    HeroNode2 head=new HeroNode2(0,"","");

    //增加节点
    public void addNode(HeroNode2 hero){
        HeroNode2 temp=head;
        while(temp.next!=null){
            temp=temp.next;
        }
        temp.next=hero;
        hero.pre=temp;
    }

    //遍历链表
    public void showList(){
        if(head.next==null){
            System.out.println("链表为空");
            return;
        }
        HeroNode2 temp=head.next;
        while(temp!=null){
            System.out.println(temp);
            temp=temp.next;
        }
    }

    //修改节点内容
    public void updateNode(HeroNode2 hero){
        HeroNode2 temp=head.next;
        boolean flag=false;
        if(temp==null){
            System.out.println("链表为空");
            return;
        }
        while(true){
            if(temp==null){
                break;
            }
            if(temp.no==hero.no){
                flag=true;
                break;
            }
            temp=temp.next;
        }
        if(flag){
            temp.name=hero.name;
            temp.nickname=hero.nickname;
        }
        else{
            System.out.println("该编号不存在");
        }
    }

    //从双向链表中删除一个节点
    public void deleteNode(int no){
        HeroNode2 temp=head.next;  //双向链表可以自我删除，不需要接触next指针
        boolean flag=false;
        if(temp==null){
            System.out.println("链表为空");
            return;
        }
        while(true){
            if(temp==null){
                break;
            }
            if(temp.no==no){
                flag=true;
                break;
            }
            temp=temp.next;
        }
        if(flag){
//            temp.next=temp.next.next;   //单链表语句
            temp.pre.next=temp.next;
            if(temp.next!=null){
                temp.next.pre=temp.pre;
            }
        }
        else{
            System.out.println("未找到该编号");
        }
    }
}

//定义HeroNode2，每个HeroNode节点就是一个对象
class HeroNode2{
    int no;
    String name;
    String nickname;
    HeroNode2 next;
    HeroNode2 pre;

    public HeroNode2(int no,String name,String nickname){
        this.no=no;
        this.name=name;
        this.nickname=nickname;
    }

    public String toString(){
        return "HeroNode[no="+no+",name="+name+",nickname="+nickname+"]";
    }
}
