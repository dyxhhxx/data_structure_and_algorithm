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

        getCode(root);
        System.out.println(huffmanCodes);

        byte[] result=zip(contentBytes,huffmanCodes);
        System.out.println(new String(result));

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

    //生成赫夫曼树对应的赫夫曼编码

    //创建静态成员变量是因为：方法中存在递归，不能在方法中创建
    static StringBuilder stringBuilder = new StringBuilder();
    static Map<Byte,String> huffmanCodes=new HashMap<>();

    //重载getCode方法
    public static Map<Byte,String> getCode(Node1 root){
        if(root==null){
            System.out.println("根节点为空");
            return null;
        }else{
            getCode(root,"",stringBuilder);
        }
        return huffmanCodes;
    }

    /**
     * 将传入的node节点的所有叶子节点的赫夫曼编码得到，并放入到集合中
     * @param node
     * @param code
     * @param stringBuilder
     */
    private static void getCode(Node1 node,String code,StringBuilder stringBuilder){
        StringBuilder stringBuilder1=new StringBuilder(stringBuilder);
        stringBuilder1.append(code);
        if (node != null) {
            if(node.data==null){  //说明是非叶子结点
                getCode(node.left,"0",stringBuilder1);
                getCode(node.right,"1",stringBuilder1);
            }else{  //说明是叶子结点
                huffmanCodes.put(node.data,stringBuilder1.toString());
            }
        }else{
            return;
        }
    }

    //将字符串对应的byte[]数组，通过生成的赫夫曼编码表，返回一个赫夫曼压缩后的byte[]数组

    /**
     *
     * @param bytes 原始的byte数组
     * @param huffmanCode  赫夫曼编码表
     * @return  根据赫夫曼编码表压缩后的byte数组
     */
    public static byte[] zip(byte[] bytes,Map<Byte,String> huffmanCode){
        StringBuilder stringBuilder=new StringBuilder();
        for(byte b:bytes){
            stringBuilder.append(huffmanCode.get(b));
        }
        int len=(stringBuilder.length()+7)/8;
//        if(stringBuilder.length()%8==0){
//            len=stringBuilder.length()/8;
//        }else{
//            len=stringBuilder.length()/8+1;
//        }
        //创建存储压缩后的byte数组
        byte[] huffmanCodeByte=new byte[len];
        int index=0;
        for(int i=0;i<stringBuilder.length();i+=8){
            String strByte;
            if(i+8>stringBuilder.length()){
                strByte=stringBuilder.substring(i);
            }else{
                strByte=stringBuilder.substring(i,i+8);
            }
            huffmanCodeByte[index]=(byte)Integer.parseInt(strByte);
            index++;
        }
        return huffmanCodeByte;
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
