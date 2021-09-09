package LinkedList;

public class SingleLinkedListDemo {

    //定义SingleLickedList,管理英雄
    class SingleLickedList{
        //先初始化一个头节点，头节点不动，不存放具体数据
        private HeroNode head=new HeroNode(0,"","");
        //在链表末尾添加新节点
        public void addNode(HeroNode heroNode){
            HeroNode temp=head;
            while(true){

            }
        }
    }

    //先定义节点HeroNode，每个HeroNode对象就是一个节点
    class HeroNode{
        int no;
        String name;
        String nickname;
        HeroNode next;
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
}
