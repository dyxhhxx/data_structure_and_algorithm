package Tree;


//AVL Tree，平衡二叉树，具有以下特点：它是一颗空树 或 它的左右两个子树的高度差绝对值不超过1，并且左右两个子树都是一棵平衡二叉树
//AVL Tree查询效率较高
public class AVLTreeDemo {

    public static void main(String[] args) {
//        int[] arr={4,3,6,5,7};
//        Node3 root=new Node3(arr[0]);
//        for(int i=1;i<arr.length;i++){
//            root.addNode(new Node3(arr[i]));
//        }
//        System.out.println("中序遍历");
//        root.infixOrder();
//        System.out.println("前序遍历：");
//        root.preOrder();
//
//        root.addNode(new Node3(8));
//        System.out.println();
//        root.infixOrder();
//        System.out.println();
//        root.preOrder();
//
//        root.leftRotate();
//        System.out.println("左旋转后：");
//        System.out.println();
//        root.infixOrder();
//        System.out.println();
//        root.preOrder();

        int[] arr1={10,11,7,6,8,9};
        Node3 root1=new Node3(arr1[0]);
        for(int i=1;i<arr1.length;i++){
            root1.addNode(new Node3(arr1[i]));
        }
        System.out.println("中序遍历");
        root1.infixOrder();
        System.out.println("前序遍历");
        root1.preOrder();
    }

}

class Node3{
    int value;
    Node3 left;
    Node3 right;

    public Node3(int value){
        this.value=value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void setLeft(Node3 left) {
        this.left = left;
    }

    public void setRight(Node3 right) {
        this.right = right;
    }

    public int getValue() {
        return value;
    }

    public Node3 getLeft() {
        return left;
    }

    public Node3 getRight() {
        return right;
    }

    @Override
    public String toString() {
        return "Node3{" +
                "value=" + value +
                '}';
    }

    //中序遍历
    public void infixOrder(){
        if(this.left!=null){
            this.left.infixOrder();
        }
        System.out.println(this);
        if(this.right!=null){
            this.right.infixOrder();
        }
    }

    //前序遍历
    public void preOrder(){
        System.out.println(this);
        if(this.left!=null){
            this.left.preOrder();
        }
        if(this.right!=null){
            this.right.preOrder();
        }
    }

    //添加节点
    public void addNode(Node3 node){
        if(this==null){
            return;
        }else{
            if(this.value>node.getValue()){
                if(this.left==null){
                    this.left=node;
                }else{
                    this.left.addNode(node);
                }
            }else{
                if(this.right==null){
                    this.right=node;
                }else{
                    this.right.addNode(node);
                }
            }
        }
        this.doubleRotate();
//        if(rightHeight()-leftHeight()>1){
//            //分为两种情况：只需要左旋转；需要双旋转
//            if(right!=null&&right.leftHeight()>right.rightHeight()){  //此时需要进行双旋转
//                right.rightRotate();
//                leftRotate();
//            }else{  //此时只需要进行左旋转
//                leftRotate();
//            }
//            return;
//        }
//
//        if(leftHeight()-rightHeight()>1){
//            if(left!=null&&left.rightHeight()>left.leftHeight()){
//                left.leftRotate();
//                rightRotate();
//            }else{
//                rightRotate();
//            }
//        }
    }

    //左旋转方法，旋转后当前接待你仍然是子树的根节点，当前节点的右子节点被舍弃（被新节点代替）
    public void leftRotate(){
        //创建新的节点
        Node3 newNode=new Node3(this.getValue());
        //把新的节点的左子树设置成当前节点的左子树
        newNode.setLeft(this.getLeft());
        //把新的节点的右子树设置成当前节点的右子树的左子树
        newNode.setRight(this.getRight().getLeft());
        //把当前节点的值替换成右子节点的值
        this.setValue(this.getRight().getValue());
        //把当前节点的左子节点指向新的节点
        this.setLeft(newNode);
        //把当前节点的右子节点指向当前节点右子节点的右子节点
        this.setRight(this.getRight().getRight());
    }

    //右旋转方法，旋转后当前节点仍然是子树的根节点，当前节点的左子节点被舍弃
    public void rightRotate(){
        //创建新的节点
        Node3 newNode=new Node3(this.getValue());
        //把新节点的右子树设置成当前节点的右子树
        newNode.setRight(this.getRight());
        //新节点的左子树设置成当前节点左子树的右子树
        newNode.setLeft(this.getLeft().getRight());
        //把当前节点的值替换成当前节点的左子节点的值
        this.setValue(this.getLeft().getValue());
        //把当前节点的左子节点替换成当前节点的左子节点的左子节点
        this.setLeft(this.getLeft().getLeft());
        //把当前节点的右子节点替换成新节点
        this.setRight(newNode);
    }

    //双旋转（当当前节点的左子节点的右子树高于左子树时进行）
    //方法：先对当前节点在左子节点进行左旋转，再对当前节点进行右旋转
    //先写两个方法，获取节点的左右子树高度

    //获取左子树高度
    public int leftHeight(){
        if(left==null){
            return 0;
        }
        return left.height();
    }

    //获取右子树高度
    public int rightHeight(){
        if(right==null){
            return 0;
        }
        return right.height();
    }

    public int height(){
        return Math.max(left==null?0:left.height(),right==null?0:right.height())+1;  //+1很重要！！！
    }

    //双旋转实现(该方法是针对递归添加节点，且每添加一次就判断一次的情况，因此该方法实际调用是在回溯时，且一定可以找到非平衡二叉树的最下面节点)
    public void doubleRotate(){
        //当添加完一个节点，如果：（右子树的高度-左子树的高度）> 1 ，左旋转
        if(this.rightHeight()-this.leftHeight()>1){
            //分为两种情况：只需要左旋转；需要双旋转
            if(right!=null&&right.leftHeight()>right.rightHeight()){  //此时需要进行双旋转
                right.rightRotate();
                this.leftRotate();
            }else{  //此时只需要进行左旋转
                this.leftRotate();
            }
        }

        if(this.leftHeight()-this.rightHeight()>1){
            if(left!=null&&left.rightHeight()>left.leftHeight()){
                left.leftRotate();
                rightRotate();
            }else{
                rightRotate();
            }
        }
    }


}
