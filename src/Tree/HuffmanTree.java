package Tree;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class HuffmanTree {

    public static void main(String[] args) {

        int[] arr={13,7,8,3,29,6,1};
        preOrder(createHuffmanTree(arr));


    }

    //创建赫夫曼树的方法(返回值为最终创建的赫夫曼树的根节点)
    public static Node createHuffmanTree(int[] arr){
        //先创建一个集合，存放各个节点数据
        ArrayList<Node> nodes=new ArrayList<>();
        for(int num:arr){
            nodes.add(new Node(num));
        }
        //循环终点的判断依据是：集合中只剩下最后一个整合了所有数据的根节点,即size>0
        while(nodes.size()>1){
            //先排序
            Collections.sort(nodes);
//            System.out.println("nodes="+nodes);
            //取出当前集合中权值最小的两颗二叉树
            Node left=nodes.get(0);
            Node right=nodes.get(1);
            //将这两颗最小的二叉树合并成新的二叉树
            Node root=new Node(left.value+ right.value);
            root.left=left;
            root.right=right;
            //将新的节点放回集合中，并删除刚刚处理的两个最小二叉树，排序工作交给下一个循环
            nodes.add(root);
            //不能通过index的方法删除节点，因为remove方法会使节点向前移动，此时删除的index=1的节点，实际上是index=2的节点
//            nodes.remove(0);
//            nodes.remove(1);
            //可以通过制定对象的方法删除节点
            nodes.remove(left);
            nodes.remove(right);
        }

        return nodes.get(0);

    }

    //前序遍历赫夫曼树的方法
    public static void preOrder(Node root){
        if(root!=null){
            root.preOrder();
        }else{
            System.out.println("该树为空");
        }
    }

}

//创建Node节点
class Node implements Comparable<Node>{

    int value;
    Node left;
    Node right;

    public Node(int value){
        this.value=value;
    }

    //前序遍历方法
    public void preOrder(){
        System.out.println(this);
        if(this.left!=null){
            this.left.preOrder();
        }
        if(this.right!=null){
            this.right.preOrder();
        }
    }

    @Override
    public int compareTo(Node o) {
        //下面的表示说明升序排列
        return this.value-o.value;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }
}
