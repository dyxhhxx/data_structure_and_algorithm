package Queue;

import com.sun.security.jgss.GSSUtil;

import java.util.Scanner;

public class testArrayQueue {
    public static void main(String[] args) {
        //创建一个队列
        ArrayQueue queue=new ArrayQueue(3);
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
                    queue.showQueue();
                    break;
                case 'a':
                    System.out.println("请输入一个数据：");
                    int n=s.nextInt();
                    queue.addQueue(n);
                    break;
                case 'g':
                    try{
                        int res=queue.getQueue();
                        System.out.printf("取出的数据是%d\n",res);
                    }catch(Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h':
                    try{
                        int res=queue.headQueue();
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

//模拟数组队列，编写ArrayQueue类
class ArrayQueue{
    private int maxSize;
    private int front;
    private int rear;
    private int[] arr;

    //初始化队列
    public ArrayQueue(int maxArraySize){
        maxSize=maxArraySize;
        front=-1;
        rear=-1;
        arr=new int[maxSize];
    }

    //判断队列是否满
    public boolean isFull(){
        return rear==maxSize-1;
    }

    //判断队列是否为空
    public boolean isEmpty(){
        return front==rear;
    }

    //在数组尾部中添加一个元素 add
    public void addQueue(int n){
        if (isFull()) {
            System.out.println("队列已满");
            return;
        }
        else{arr[++front]=n;}
    }

    //从数组头部取出一个元素 get
    public int getQueue(){
        if(isEmpty()){
            //抛出异常
            throw new RuntimeException("队列空，无法取出数据");
        }
        else{
            int res=arr[++rear];
            return res;
        }
    }

    //查看数组头部数据 h
    public int headQueue(){
        if(isEmpty()){
            throw new RuntimeException("队列空，无法查看队列头部数据");
        }
        else{
            int res=arr[++front];
            return res;
        }
    }

    //输出数组（遍历）show
    public void showQueue(){
        if(isEmpty()){
            System.out.println("队列为空，没有数据");
            return;
        }else{
            for(int i=0;i<arr.length;i++){
                System.out.printf("arr[%d]=%d\n",i,arr[i]);
            }
        }
    }
}
