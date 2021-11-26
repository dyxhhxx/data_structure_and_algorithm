package HashTable;

import java.util.Scanner;

public class HashTabDemo {
    public static void main(String[] args) {

        //创建Hash表
        HashTab hashTab=new HashTab(7);

        //写一个简单的菜单：
        String key="";
        Scanner s=new Scanner(System.in);
        while(true){
            System.out.println("add: 添加雇员");
            System.out.println("list: 显示雇员");
            System.out.println("find: 查找雇员");
            System.out.println("exit：退出系统");
            key=s.next();
            switch(key){
                case "add":
                    System.out.println("输入id：");
                    int id=s.nextInt();
                    System.out.println("输入名字：");
                    String name=s.next();
                    Emp emp=new Emp(id,name);
                    hashTab.addEmp(emp);
                    break;
                case "list":
                    hashTab.list();
                    break;
                case "find":
                    System.out.println("请输入id：");
                    id=s.nextInt();
                    hashTab.findEmp(id);
                    break;
                case "exit":
                    s.close();;
                    System.exit(0);
                default:
                    System.out.println("请重新输入");
                    break;
            }

        }

    }
}

//员工对象
class Emp{
    public int id;
    public String name;
    public Emp next;
    public Emp(int id,String name){
        this.id=id;
        this.name=name;
    }

    @Override
    public String toString() {
        return "HashTable.Emp{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", next=" + next +
                '}';
    }
}

//链表
class EmpLinkedList{
    private Emp head;

    //添加雇员到链表
    public void addEmp(Emp emp){
        if(head==null){
            head=emp;
            return;
        }
        //借助辅助指针遍历到最后一个节点
        Emp temp=head;
        while(true){
            if(temp.next==null){
                break;
            }
            temp=temp.next;
        }
        temp.next=emp;
    }

    //遍历链表到雇员信息
    public void list(int no){
        if(head==null){
            System.out.printf("第%d条链表为空\n",no);
            return;
        }
        Emp temp=head;
        while(true){
            System.out.printf("[No：%d,name: %s]\t",temp.id,temp.name);
            temp=temp.next;
            if(temp==null){
                break;
            }
        }
        System.out.println();
    }
    //根据id查找雇员
    public void findEmp(int id){
        if(head==null){
            System.out.println("链表为空");
        }
        Emp temp=head;
        while(true){
            if(temp.id==id){
                System.out.printf("编号为%d的员工姓名是%s\n",temp.id,temp.name);
                break;
            }
            if(temp.next==null){
                System.out.printf("没有找到编号为%d的员工\n",id);
                break;
            }
            temp=temp.next;
        }
    }
}

class HashTab{
    private EmpLinkedList[] EmpLinkedListArr;
    private int size;

    public HashTab(int size){
        this.size=size;
        EmpLinkedListArr=new EmpLinkedList[size];
        //此处是否需要初始化各个链表？
        for(int i=0;i<size;i++){
            EmpLinkedListArr[i]=new EmpLinkedList();
        }
    }

    //向链表中添加雇员
    public void addEmp(Emp emp){
        int no=hashFan(emp.id);
        EmpLinkedListArr[no].addEmp(emp);
    }

    //根据输入的id查找雇员
    public void findEmp(int id){
        int no=hashFan(id);
        EmpLinkedListArr[no].findEmp(id);
    }

    //遍历链表
    public void list(){
        for(int i=0;i<size;i++){
            EmpLinkedListArr[i].list(i);
        }
    }


    //编写散列函数，使用一个简单取模法
    public int hashFan(int id){
        return (id%size);
    }

}