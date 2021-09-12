package LinkedList;

public class DoubleLinkedListDemo {

    public static void main(String[] args) {
        //定义节点和链表
        HeroNode2 hero1=new HeroNode2(1,"宋江","及时雨");
        HeroNode2 hero2=new HeroNode2(3,"卢俊义","玉麒麟");
        HeroNode2 hero3=new HeroNode2(6,"吴用","智多星");
        HeroNode2 hero4=new HeroNode2(9,"林冲","豹子头");
        //创建链表
        DoubleLinkedList dll=new DoubleLinkedList();
        //添加节点
        dll.addNode(hero1);
        dll.addNode(hero2);
        dll.addNode(hero3);
        dll.addNode(hero4);
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
        HeroNode2 temp=head.next;
        while(temp!=null){
            System.out.println(temp);
            temp=temp.next;
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
