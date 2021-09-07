package Queue;

import java.util.Scanner;

public class CircleArrayQueue {

    //定义环形队列属性：包括头部指针，尾部指针，队列数组
    int[] caq;
    int front;
    int rear;
    int maxsize;

    //定义队列构造器（构造方法）
    public CircleArrayQueue(int maxSize){
        maxsize=maxSize;
        caq=new int[maxSize];
        front=0;
        rear=0;
    }

    //判断环形队列是否已满
    public boolean isFull(){
        return (rear+1)%maxsize==front;
    }

    //判断环形队列是否为空
    public boolean isEmpty(){
        return rear==front;
    }

    //在环形队列中增加数据add
    public void addQueue(int value){
        if(isFull()){
            System.out.println("队列已满");
            return;
        }
        else{
            caq[rear]=value;
            rear=(rear+1)%maxsize;
        }
    }

    //从环形队列头部取出数据get
    public int getQueue(){
        if(isEmpty()){
            throw new RuntimeException("队列为空");
        }
        else{
            int res=caq[front];
            caq[front]=0;
            front=(front+1)%maxsize;
            return res;
        }
    }

    //显示环形队列头部数据head
    public int headQueue(){
        if(isEmpty()){
            throw new RuntimeException("队列为空");
        }
        else{
            return caq[front];
        }
    }

    //得到队列长度
    public int sizeQueue(){
        if(isEmpty()){
            throw new RuntimeException("队列为空");
        }
        int size=(rear+maxsize-front)%maxsize;
        return size;
    }

    //显示环形队列（遍历）show，只显示有的数据
    public void showQueue(){
        if(isEmpty()){
            System.out.println("队列为空");
            return;
        }
        for(int i=front;i<front+sizeQueue();i++){
            System.out.printf("第%d位的值是%d\n",i%maxsize,caq[i%maxsize]);
        }
    }

    public static void main(String[] args) {

        ////队列中始终有一位是缓存区（缓存区位置不固定，队列满时就是rear+1），rear指向队列末尾元素的后面，
        //即永远指向空位（队列满时该空位就是缓存区）。思考empty时，不需要考虑rear的位置，因为取数据时只有front运动，
        //且front永远指向队列头部，当front运动到rear的位置时，即头部指针指向空位是，队列自然为empty。
        //因为空位的存在，队列实际存储数据的最大长度为maxsize-1
        CircleArrayQueue test=new CircleArrayQueue(4);  //队列有效长度为3
        Scanner s=new Scanner(System.in);
        char ins=' ';
        boolean loop=true;
        while(loop){
            System.out.println("s(show):显示队列");
            System.out.println("e(exit):退出程序");
            System.out.println("a(add):添加数据到队列");
            System.out.println("g(get):从队列头部取出数据");
            System.out.println("h(head):查看队列头部数据");
            System.out.println("请输入一个指令：");
            ins=s.next().charAt(0);
            switch(ins){
                case 's':
                    test.showQueue();
                    break;
                case 'a':
                    if(test.isFull()){
                        System.out.println("队列已满");
                        break;
                    }
                    System.out.println("请输入一个数据：");
                    int n=s.nextInt();
                    test.addQueue(n);
                    break;
                case 'g':
                    try{
                        int res=test.getQueue();
                        System.out.printf("取出的数据是%d\n",res);
                    }catch(Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h':
                    try{
                        int res=test.headQueue();
                        System.out.printf("队列头部数据为%d\n",res);
                    }catch(Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'e':
                    loop=false;
                    s.close();
                default: break;
            }
            System.out.println("--------------------------");
        }
        System.out.println("程序退出");
    }
}



