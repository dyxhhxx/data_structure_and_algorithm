package Graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class Graph {

    private ArrayList<String> vertexList;  //存储顶点集合
    private int[][] edges;  //存储
    private int numOfEdges;  //表示边的数目
    private boolean[] isVisited;

    public static void main(String[] args) {
        int n = 5;
        String vertexs[] = {"A", "B", "C", "D", "E"};
        Graph graph = new Graph(n);
        //添加顶点
        for(String value:vertexs){
            graph.addVertex(value);
        }
        //添加边
        graph.addEdge(0,1,1);
        graph.addEdge(0,2,1);
        graph.addEdge(1,2,1);
        graph.addEdge(1,3,1);
        graph.addEdge(1,4,1);

        graph.showGraph();

        System.out.println("深度遍历dfs：");
        graph.dfs();
    }

    public Graph(int n) {
        //初始化矩阵和vertexList
        edges = new int[n][n];
        vertexList = new ArrayList<String>(n);
        numOfEdges = 0;
        isVisited=new boolean[n];
    }

    //得到第一个邻接节点的下标
    public int getFirstNeighbor(int index){
        for(int i=0;i<vertexList.size();i++){
            if(edges[index][i]>0){
                return i;
            }
        }
        return -1;
    }

    //根据前一个邻接节点的下标,获取下一个邻接节点
    public int getNextNeighbor(int v1,int v2){
        for(int i=v2+1;i<vertexList.size();i++){
            if(edges[v1][i]>0){
                return i;
            }
        }
        return -1;
    }

    //深度优先dfs遍历算法
    public void dfs(boolean[] isVisited,int i){
        System.out.println(getValueByIndex(i)+"->");
        isVisited[i]=true;  //记录该节点已经被访问过
        int w = getFirstNeighbor(i);
        while (w!=-1){  //说明存在邻接节点
            if(!isVisited[w]){  //说明该邻接节点还没有被访问过
                dfs(isVisited,w);  //对该邻接节点进行dfs遍历
            }
            w=getNextNeighbor(i,w);  //下一个邻接节点（递归终止条件即为：邻接节点值为-1，退出while循环）
        }
    }

    //对dfs进行重载,遍历所有节点，并对每个节点进行dfs遍历
    public void dfs(){
        for(int i=0;i<vertexList.size();i++){
            if(!isVisited[i]){
                dfs(isVisited,i);
            }
        }
    }

    //对一个节点进行广度优先遍历的算法bfs
    public void bfs(boolean[] isVisited,int i){
        int u;  //表示头节点对应的下标
        int w;  //邻接节点w
        //用队列记录节点访问的顺序
        LinkedList<Integer> queue = new LinkedList<>();
        System.out.println(getValueByIndex(i)+"->");
        isVisited[i]=true;
        queue.addLast(i);

        while(!queue.isEmpty()){
            //取出队列的头节点下标
            u = (Integer) queue.removeFirst();
            //得到第一个邻接节点的下标
            w = getFirstNeighbor(u);
            while (w!=-1){  //将u的所有邻接节点放进队列
                if(!isVisited[w]){
                    System.out.println(getValueByIndex(w)+"->");
                    //标记已经访问
                    isVisited[w]=true;
                    //入队
                    queue.addLast(w);
                }
                //以u为前驱节点，找w后面的下一个邻接节点
                w=getNextNeighbor(u,w);  //真正体现广度优先
            }
        }
    }

    //图中常用的方法
    //返回节点的个数
    public int getNumOfVertex() {
        return vertexList.size();
    }

    //返回边的个数
    public int getNumOfEdges() {
        return numOfEdges;
    }

    //返回节点i对应的数据 0->A,2->B,3->C...
    public String getValueByIndex(int i) {
        return vertexList.get(i);
    }

    //返回v1和v2的权值
    public int getWeight(int v1, int v2) {
        return edges[v1][v2];
    }

    //显示图对应的矩阵
    public void showGraph() {
        for (int[] link : edges) {
//            for(int i:link){
//                System.out.println(i+" ");
//            }
//            System.out.println();
            System.out.println(Arrays.toString(link));
        }
    }

    //插入顶点
    public void addVertex(String vertex) {
        vertexList.add(vertex);
    }

    //添加边
    /**
     * @param v1     点的下标即第几个顶点，即A-0，B-1
     * @param v2     第二个顶点
     * @param weight 权值，1表示直接连接，0表示没有直接连接
     */
    public void addEdge(int v1, int v2, int weight) {
        edges[v1][v2] = weight;
        edges[v2][v1] = weight;
    }

}
