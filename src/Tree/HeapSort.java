package Tree;

import java.util.Arrays;

public class HeapSort {

    public static void main(String[] args) {
        int[] arr = {4, 6, 8, 5, 9};
        heapSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    //将无序序列构建成一个堆，根据升降序需求选择大顶堆或小顶堆
    public static void heapSort(int[] arr) {

        //先调整堆结构，
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            adjustHeap(arr, i, arr.length);
        }
        //将堆顶元素与末尾元素交换位置，将最大元素沉到数组末端
        //重新调整结构，时期满足堆结构定义，然后继续交换堆顶元素与当前末尾元素，反复执行调整+交换步骤，直到整个序列有序
        for (int j = arr.length - 1; j > 0; j--) {
            int temp = arr[j];
            arr[j] = arr[0];
            arr[0] = temp;
            //此处的adjustHeap与上面的不同是：
            //上面的是依据树的结构从下往上，从最小树单元开始堆排序，相当于整体都进行了堆排序处理
            //此处是只对根节点进行处理，因为只有根节点发生了变化，只需要处理整体树的一个分支
            //相当于，先整体堆结构化，再在交换节点后针对根节点的一个分支进行堆结构化
            adjustHeap(arr, 0, j);
        }

    }

    /**
     * @param arr    目标数组
     * @param i      当前非叶子节点在数组中的索引
     * @param length 表示对多少个元素进行调整，length是在逐渐的减少
     */
    public static void adjustHeap(int[] arr, int i, int length) {

        //向将当前元素的值保存在temp中
        int temp = arr[i];
        //每次都从左子节点开始
        for (int k = 2 * i + 1; k < length; k = 2 * k + 1) {
            //定位到左子节点和右子节点中较大的位置
            if (k + 1 < length && arr[k] < arr[k + 1]) {
                k++;
            }
            //开始比较当前的三节点小树，将其调整为大顶堆结构
            if (arr[k] > temp) {
                arr[i] = arr[k];
                i = k;
                //此处没有对k位置进行赋值操作，因为即便将temp的值放在了k位置，接下来比较时用的仍然是temp的值，因此在for循环完成（找到temp应该在的位置）时，再进行赋值操作
            } else {  //此时说明下面的子树都按照堆结构排列
                break;
            }
        }
        //将temp的值，放到它应该在的地方
        arr[i] = temp;

    }

}

