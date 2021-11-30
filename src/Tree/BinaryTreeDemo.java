package Tree;

public class BinaryTreeDemo {

    public static void main(String[] args) {
        //手动创建一个二叉树
        HeroNode h1=new HeroNode(1,"宋江");
        HeroNode h2=new HeroNode(2,"吴用");
        HeroNode h3=new HeroNode(3,"卢俊义");
        HeroNode h4=new HeroNode(4,"林冲");
        HeroNode h5=new HeroNode(5,"关胜");
        HeroNode h6=new HeroNode(6,"王六");
        HeroNode h7=new HeroNode(7,"张七");

        BinaryTree binaryTree=new BinaryTree();
        binaryTree.setRoot(h1);
        h1.setLeft(h2);
        h1.setRight(h3);
        h2.setLeft(h4);
        h2.setRight(h5);
        h3.setLeft(h6);
        h3.setRight(h7);

        System.out.println("前序遍历：");
        binaryTree.preOrder();   //1 2 3 4 5
        System.out.println("中序遍历：");
        binaryTree.infixOrder();   //2 1 4 3 5
        System.out.println("后序遍历：");
        binaryTree.postOrder();   // 2 4 3 5 1

        System.out.println("前序查找：");
        System.out.println(binaryTree.preOrderSearch(6));
        System.out.println("查询次数："+ HeroNode.count1);
        System.out.println("中序查找：");
        System.out.println(binaryTree.infixOrderSearch(6));
        System.out.println("查询次数："+ HeroNode.count2);
        System.out.println("后序查找：");
        System.out.println(binaryTree.postOrderSearch(6));
        System.out.println("查询次数："+ HeroNode.count3);

        binaryTree.deletepro(3);
        System.out.println("删除4号节点后的二叉树为：");
        binaryTree.preOrder();

    }

}

//定义二叉树
class BinaryTree{
    private HeroNode root;

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



//定义节点
class HeroNode{

    static int count1=0;
    static int count2=0;
    static int count3=0;

   private int id;
   private String name;
   private HeroNode left;
   private  HeroNode right;

   public HeroNode(int id,String name){
       this.id=id;
       this.name=name;
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