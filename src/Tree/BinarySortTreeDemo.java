package Tree;

public class BinarySortTreeDemo {

    public static void main(String[] args) {
        Node2 root=new Node2(7);
        Node2 node1=new Node2(3);
        Node2 node2=new Node2(10);
        Node2 node3=new Node2(1);
        Node2 node4=new Node2(5);
        Node2 node5=new Node2(9);
        Node2 node6=new Node2(12);
        Node2 node7=new Node2(8);

        root.addNode(node1);
        root.addNode(node2);
        root.addNode(node3);
        root.addNode(node4);
        root.addNode(node5);
        root.addNode(node6);
        root.addNode(node7);

        root.infixOrder();

    }

}

//class BinarySortTree{
//    Node2 root;
//
//    //中序遍历
//    public void infixOrder(){
//        if(root==null){
//            System.out.println("该二叉树为空");
//        }else{
//            root.infixOrder();
//        }
//    }
//
//    //添加节点
//    public void addNode(Node2 node){
//        if(root==null){
//            root=node;
//        }else{
//            root.addNode(node);
//        }
//    }
//}

class Node2 {

    private int value;
    private Node2 left;
    private Node2 right;

    public Node2(int value){
        this.value=value;
    }

    public void setRight(Node2 right) {
        this.right = right;
    }

    public void setLeft(Node2 left) {
        this.left = left;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public Node2 getLeft() {
        return left;
    }

    public Node2 getRight() {
        return right;
    }

    //中序遍历
    public void infixOrder() {
        if (this.left != null) {
            this.left.infixOrder();
        }
        System.out.println(this.value);
        if (this.right != null) {
            this.right.infixOrder();
        }
    }

    //二叉排序树添加节点
    public void addNode(Node2 node){
        if(node==null){
            return;
        }else{
            if(node.value<=this.value){  //向左添加
                if(this.left==null){
                    this.setLeft(node);
                }else{
                    this.left.addNode(node);
                }
            }else{
                if(this.right==null){  //向右添加
                    this.setRight(node);
                }else{
                    this.right.addNode(node);
                }
            }
        }
    }

    //二叉排序树删除节点
    public void deleteNode(int value){
//        if(this.getValue()==value){
//            if(this.getLeft()==null&&this.getRight()==null){
//                this==null;  //无法删除自己，因此要操作左右节点
//            }
//        }
        boolean flag=false;  //标志符：没找到
        if(this.getLeft()!=null&&this.getLeft().getValue()==value){  //找到，要删除左子节点
            flag=true; //标志符：已找到
            if(this.getLeft().getLeft()==null&&this.getLeft().getRight()==null){  //左子节点没有子节点，直接将左节点置空
                this.setLeft(null);
            }
            else if(this.getLeft().getLeft()!=null&&this.getLeft().getRight()==null) {  //左子节点有左节点，没有右节点
                //用左子节点的左节点替换左子节点
                this.setLeft(this.getLeft().getLeft());
            }
            else{  //左子节点只有右节点，或，左子节点有两个子节点：都是用左子节点的右节点替换左子节点
                Node2 temp=this.getLeft().getLeft();
                this.setLeft(this.getLeft().getRight());
                Node2 temp1=this;
                //再将temp放在最左边
                while(temp1.getLeft()!=null){
                    temp1=temp1.getLeft();
                }
                temp1.setLeft(temp);
            }
        }
        else if(this.getLeft()!=null&&this.getLeft().getValue()!=value){
            this.getLeft().deleteNode(value);
        }

        //右子节点的操作同上
        //注意在删除时还要先处理root节点本身，因为上面的方法都是从左子节点和右子节点开始操作的
    }

    //也可以利用searchParent方法，先找到要删除的节点，然后操作该节点
    //用两个方法简化了冗长的寻找代码
}
