package Tree.ThreadedBinaryTreeDemo;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class threadedBinaryTreeDemo {

    public static void main(String[] args) {
        HeroNode h1=new HeroNode(1,"宋江");
        HeroNode h2=new HeroNode(2,"吴用");
        HeroNode h3=new HeroNode(3,"卢俊义");
        HeroNode h4=new HeroNode(4,"林冲");
        HeroNode h5=new HeroNode(5,"关胜");
        HeroNode h6=new HeroNode(6,"王六");
        HeroNode h7=new HeroNode(7,"张七");

        ThreadedBinaryTree threadedbinaryTree=new ThreadedBinaryTree();
        threadedbinaryTree.setRoot(h1);
        h1.setLeft(h2);
        h1.setRight(h3);
        h2.setLeft(h4);
        h2.setRight(h5);
        h3.setLeft(h6);
        h3.setRight(h7);

//        threadedbinaryTree.infixThreadedNodes();
//        System.out.println(h4.getRight()==h2);
//        System.out.println(h5.getRight()==h1);
//        System.out.println(h5.getLeft()==h2);
//        threadedbinaryTree.ThreadedList();

//        threadedbinaryTree.preThreadedNodes();
//        threadedbinaryTree.preThreadedList();

        threadedbinaryTree.postThreadedNodes();
        threadedbinaryTree.postThreadedList();


    }

}

//定义二叉树
class ThreadedBinaryTree{
    private HeroNode root;

    private HeroNode pre=null;  //借助pre定位上一个节点，来遍历；实际上还是操作当前节点的左右指针

    public void infixThreadedNodes(){
        infixThreadedNodes(root);
    }

    public void preThreadedNodes(){
        preThreadedNodes(root);
    }

    public void postThreadedNodes(){
        postThreadedNodes(root);
    }


    //中序线索化的方法
    public void infixThreadedNodes(HeroNode node){
        if(node==null){
            return;
        }

        //线索化左子树
        infixThreadedNodes(node.getLeft());

        //线索化当前节点
        //处理当前节点的前驱节点
        if(node.getLeft()==null){
            node.setLeft(pre);
            node.setLeftType(1);   //最左边的叶子节点（前序遍历的第一个节点）的LeftNode虽然还是null，但其LeftType应该为1
        }
        //处理上一个节点的后继节点,相当于当前节点让当前节点的后继节点处理它们之间的关系
        if(pre!=null&&pre.getRight()==null){
            pre.setRight(node);
            pre.setRightType(1);
        }
        pre=node;

        //线索化右子树
        infixThreadedNodes(node.getRight());

    }

    //线索化后遍历方法
    public void ThreadedList(){
        HeroNode node=root;
        while(node!=null){
            //找到第一个节点，即第一个LeftType为1的节点
            while(node.getLeftType()==0){
                node=node.getLeft();
            }
            System.out.println(node);
            //当node的rightType为1时，即右节点指向后继节点时，向后遍历
            while(node.getRightType()==1){
                node=node.getRight();
                System.out.println(node);
            }
            //此时当前节点的右指针指向的不一定是后继节点（type不为1，可能是，也可能不是），需要手动向下遍历，然后下一个循环重新找真正的后续节点
            node=node.getRight();
        }
    }

    //前序线索化
    public void preThreadedNodes(HeroNode node){
        if(node==null){
            return;
        }
        if(node.getLeft()==null){
            node.setLeft(pre);
            node.setLeftType(1);
        }
        if(pre!=null&&pre.getRight()==null){
            pre.setRight(node);
            pre.setRightType(1);
        }
        pre=node;
        //处理过就不用回去了
        //因为不论是前中后序，在处理当前节点时，都只添加了当前节点的左节点（和前序节点的右节点），所以如果在下面的递归中，对当前节点的左节点有判断，需要加上type
        //但只有前序，左节点的添加会在左节点的判断之前，所以要格外注意
        if(node.getLeft()!=null&&node.getLeftType()==0){
           preThreadedNodes(node.getLeft());
        }
        if(node.getRight()!=null&&node.getRightType()==0){
            preThreadedNodes(node.getRight());
        }
    }

    //前序线索化遍历
    public void preThreadedList(){
        HeroNode node=root;
        while(node!=null){
            while(node.getLeftType()==0){
                System.out.println(node);
                node=node.getLeft();
            }
            System.out.println(node);
            while(node.getRight()!=null&&node.getRightType()==1){
                node=node.getRight();
                System.out.println(node);
            }
            if(node.getLeftType()==0){
                node=node.getLeft();
            }else{
                node=node.getRight();
            }
        }
    }

    //后续线索化
    public void postThreadedNodes(HeroNode node){
        if(node==null){
            return;
        }
        postThreadedNodes(node.getLeft());
        postThreadedNodes(node.getRight());
        if(node.getLeft()==null){
            node.setLeft(pre);
            node.setLeftType(1);
        }
        if(pre!=null&&pre.getRight()==null){
            pre.setRight(node);
            pre.setRightType(1);
        }
        pre=node;
    }

    //后续线索化遍历,因为2到6没有指针，而6到2有指针，因此将本来的前后中，改为中后前，将结果存在集合中再反向取出
    public void postThreadedList(){
        Stack<HeroNode> reversenode=new Stack<>();
        HeroNode node=root;
        while(node!=null){
            while(node.getRightType()==0){
                reversenode.push(node);
                node=node.getRight();
            }
            reversenode.push(node);
            while(node.getLeft()!=null&&node.getLeftType()==1){
                node=node.getLeft();
                reversenode.push(node);
            }
            if(node.getRightType()==0){
                node=node.getRight();
            }else{
                node=node.getLeft();
            }
        }
        while(reversenode.size()>0){
            System.out.println(reversenode.pop());
        }
    }



    public void setRoot(HeroNode root) {
        this.root = root;
    }

    //前序遍历
    public void preOrder(){
        if(root!=null){
            root.preOrder();
        }else{
            System.out.println("二叉树为空，无法遍历");
        }
    }

    //中序遍历
    public void infixOrder(){
        if(root!=null){
            root.infixOrder();
        }else{
            System.out.println("二叉树为空，无法遍历");
        }
    }

    //后序遍历
    public void postOrder(){
        if(root!=null){
            root.postOrder();
        }else{
            System.out.println("二叉树为空，无法遍历");
        }
    }

    //前序查找
    public HeroNode preOrderSearch(int id){
        if(root!=null) {
            return this.root.preOrderSearch(id);
        }
        else{
            System.out.println("二叉树为空");
            return null;
        }
    }

    //中序查找
    public HeroNode infixOrderSearch(int id){
        if(root!=null) {
            return this.root.infixOrderSearch(id);
        }
        else{
            System.out.println("二叉树为空");
            return null;
        }
    }

    //后序查找
    public HeroNode postOrderSearch(int id){
        if(root!=null) {
            return this.root.postOrderSearch(id);
        }
        else{
            System.out.println("二叉树为空");
            return null;
        }
    }

    //删除节点
    public void delete(int id){
        if(root==null){
            System.out.println("二叉树为空");
        }else {
            if (root.getId() == id) {
                root = null;
                return;
            }
            if (root.getLeft() != null) {
                root.delete(id);
            }
            if (root.getRight() != null) {
                root.delete(id);
            }
        }
    }

    public void deletepro(int id){
        if(root==null){
            System.out.println("二叉树为空");
        }else {
            if (root.getId() == id) {
                if(root.getLeft()==null){
                    root=root.getRight();
                }else{
                    HeroNode tempright=root.getRight();
                    root=root.getLeft();
                    HeroNode temp=root;
                    if(temp.getRight()!=null){
                        temp=temp.getRight();
                    }
                    temp.setRight(tempright);
                }
                return;
            }
            if (root.getLeft() != null) {
                root.deletepro(id);
            }
            if (root.getRight() != null) {
                root.deletepro(id);
            }
        }
    }


}


class HeroNode{

    static int count1=0;
    static int count2=0;
    static int count3=0;

    private int id;
    private String name;
    private HeroNode left;
    private HeroNode right;

    //线索化后并非所有节点的左右子节点都指向前驱和后继节点，因此需要添加Type标记真正线索化的节点
    //leftType==0表示指向的是左子树，为1表示为前驱节点
    //rightType==0表示指向的是右子树，为1表示为后继节点
    private int leftType;
    private int rightType;

    public HeroNode(int id,String name){
        this.id=id;
        this.name=name;
    }

    public int getLeftType() {
        return leftType;
    }

    public void setLeftType(int leftType) {
        this.leftType = leftType;
    }

    public int getRightType() {
        return rightType;
    }

    public void setRightType(int rightType) {
        this.rightType = rightType;
    }

    public HeroNode getLeft() {
        return left;
    }

    public HeroNode getRight() {
        return right;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLeft(HeroNode left) {
        this.left = left;
    }

    public void setRight(HeroNode right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    //----------------------------------遍历----------------------------------------

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

    //后序遍历
    public void postOrder(){
        if(this.left!=null){
            this.left.postOrder();
        }
        if(this.right!=null){
            this.right.postOrder();
        }
        System.out.println(this);
    }

    //-------------------------------------查找-------------------------------------------

    //前序查找，注意：顺序的实现通过return处来体现
    public HeroNode preOrderSearch(int id){
        count1++;
        if(this.id==id){
            return this;
        }
       HeroNode res=null;
        if(this.left!=null){
            res=this.left.preOrderSearch(id);
            if(res!=null){
                return res;
            }
        }
        if(this.right!=null){
            res=this.right.preOrderSearch(id);
        }
        return res;
    }

    //中序查找
    public HeroNode infixOrderSearch(int id){
        count2++;
        HeroNode res=null;
        if(this.left!=null){
            res=this.left.infixOrderSearch(id);
            if(res!=null){
                return res;
            }
        }
        if(this.id==id) {
            return this;
        }
        if(this.right!=null){
            res=this.right.infixOrderSearch(id);
        }
        return res;
    }

    //后序查找
    public HeroNode postOrderSearch(int id){
        count3++;
        HeroNode res=null;
        if(this.left!=null){
            res=this.left.postOrderSearch(id);
            if(res!=null){
                return res;
            }
        }
        if(this.right!=null){
            res=this.right.postOrderSearch(id);
            if(res!=null){
                return res;
            }
        }
        if(this.id==id){
            return this;
        }
        return res;
    }

    //---------------------------------------删除--------------------------------------------
    //此处的删除方式为：若要删除的节点是叶子节点，将节点删除；若要删除的节点是非叶子节点，将整个子树删除
    public void delete(int id){
        //不能删除自己
//       if(this.id==id){
//           this=null;
//       }
        if(this.left!=null&&this.left.id==id){
            this.left=null;
            return;
        }
        if(this.right!=null&&this.right.id==id){
            this.right=null;
            return;
        }
        if(this.left!=null){
            this.left.delete(id);
        }
        if(this.right!=null){
            this.right.delete(id);
        }
    }

    //正常的删除策略：没有子树就直接删除；有一个子树，将该子树放在被删除的节点上；有两个子树，将左子树放在被删除的节点上，再将右节点放在左子树最右边的叶子节点的右节点上
    public void deletepro(int id){
        if(this.left!=null&&this.left.id==id){
            if(this.left.left==null&&this.left.right==null){   //没有子树
                this.left=null;
            }
            else if(this.left.left==null&&this.left.right!=null){  //该节点只有一个子树，就让该子树直接替代该子节点
                this.left=this.left.right;
            }
            else{   //只有左子树，或有两个子树，都是将左子树放在原节点
                HeroNode tempright=this.left.right;
                this.left=this.left.left;
                HeroNode temp=this.left;
                while(temp.right!=null){
                    temp=temp.right;
                }
                temp.right=tempright;
            }

        }
        if(this.right!=null&&this.right.id==id){
            if(this.right.left==null&&this.right.right==null){   //没有子树
                this.right=null;
            }
            else if(this.right.left==null&&this.right.right!=null){  //该节点只有一个子树，就让该子树直接替代该子节点
                this.right=this.right.right;
            }
            else{   //只有左子树，或有两个子树，都是将左子树放在原节点
                HeroNode tempright=this.right.right;
                this.right=this.right.left;
                HeroNode temp=this.right;
                while(temp.right!=null){
                    temp=temp.right;
                }
                temp.right=tempright;
            }

        }
        if(this.left!=null){
            this.left.deletepro(id);
        }
        if(this.right!=null){
            this.right.deletepro(id);
        }
    }




}
