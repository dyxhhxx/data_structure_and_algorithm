package Tree;

public class ArrBinaryTreeDemo {

    //将一个数组以二叉树前序遍历的方式进行遍历

    public static void main(String[] args) {
        int[] arr={1,2,3,4,5,6,7};
        ArrayBinaryTree abt=new ArrayBinaryTree(arr);
        abt.preOrder();
        System.out.println();
        abt.infixOrder(0);
        System.out.println();
        abt.postOrder(0);;
        System.out.println();
    }

}

class ArrayBinaryTree{

    private int[] arr;

    public ArrayBinaryTree(int[] arr){
        this.arr=arr;
    }

    //重载
    public void preOrder(){
        preOrder(0);
    }

    /**前序遍历
     *
     * @param index 遍历起点的数组下标
     */
    public void preOrder(int index){
        if(arr==null||arr.length==0){
            System.out.println("数组为空");
            return;
        }
        System.out.print(arr[index]+" ");
        if((index*2+1)<arr.length){
            preOrder(2*index+1);
        }
        if((2*index+2)<arr.length){
            preOrder(2*index+2);
        }
    }

    /**中序遍历
     *
     * @param index
     */
    public void infixOrder(int index){
        if(arr==null||arr.length==0){
            System.out.println("数组为空");
            return;
        }
        if((2*index+1)<arr.length){
            infixOrder(2*index+1);
        }
        System.out.print(arr[index]+" ");
        if((2*index+2)<arr.length){
            infixOrder(2*index+2);
        }
    }

    /**后序遍历
     *
     * @param index
     */
    public void postOrder(int index){
        if(arr==null||arr.length==0){
            System.out.println("数组为空");
            return;
        }
        if((2*index+1)<arr.length){
            postOrder(2*index+1);
        }
        if((2*index+2)<arr.length){
            postOrder(2*index+2);
        }
        System.out.print(arr[index]+" ");
    }
}