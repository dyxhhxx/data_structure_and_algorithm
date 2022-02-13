package Tree;

import java.nio.charset.StandardCharsets;
import java.util.*;

public class HuffmanCode {

    public static void main(String[] args) {

        String content="i like like like java do you like a java";
        byte[] contentBytes=content.getBytes();
        System.out.println(contentBytes.length);

        List<Node1> nodes=getNodes(contentBytes);
        System.out.println(nodes);

        Node1 root=Huffman(nodes);
        root.preOrder();

    }

    /**
     * 将目标字节数组中的元素转化成键值对，并将每一个Byte最终转化成Node对象保存在List中
     * @param bytes 目标字节数组
     * @return 统计后的Node列表
     */
    private static List<Node1> getNodes(byte[] bytes){
        ArrayList<Node1> nodes=new ArrayList<>();
        Map<Byte,Integer> counts=new HashMap<>();
        //统计每一个byte出现的次数，并将结果存放在一个Map中
        for(byte b:bytes){
            Integer count=counts.get(b);
            if(count==null){
                counts.put(b,1);
            }else{
                counts.put(b,count+1);
            }
        }
        //把Map中的键值对转化成Node对象，并存放在List中
        //遍历Map
        for (Map.Entry<Byte,Integer> entry:counts.entrySet()){
            nodes.add(new Node1(entry.getKey(),entry.getValue()));
        }
        return nodes;
    }

    /**
     * 将Nodes列表转化为一颗赫夫曼树
     * @param nodes 目标列表
     * @return 赫夫曼树的根节点
     */
    private static Node1 Huffman (List<Node1> nodes){
        while(nodes.size()>1){
            Collections.sort(nodes);
            Node1 left=nodes.get(0);
            Node1 right=nodes.get(1);
            Node1 root=new Node1(null,left.weight+right.weight);
            root.left=left;
            root.right=right;
            nodes.remove(left);
            nodes.remove(right);
            nodes.add(root);
        }
        return nodes.get(0);
    }



}

class Node1 implements Comparable<Node1>{

    Byte data;
    int weight;
    Node1 left;
    Node1 right;

    public Node1(Byte data,int weight){
        this.data=data;
        this.weight=weight;
    }

    @Override
    public int compareTo(Node1 o) {
        return this.weight-o.weight;
    }

    @Override
    public String toString() {
        return "Node{" +
                "data=" + data +
                ", weight=" + weight +
                '}';
    }

    public void preOrder(){
        System.out.println(this);
        if(this.left!=null){
            this.left.preOrder();
        }
        if(this.right!=null){
            this.right.preOrder();
        }
    }


}
