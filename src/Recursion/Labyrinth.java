package Recursion;

public class Labyrinth {

    public static void main(String[] args) {

        //创建地图
        int map[][] = new int[8][7];
        for (int i = 0; i < 7; i++) {
            map[0][i] = 1;
            map[7][i] = 1;
        }
        for (int i = 0; i < 8; i++) {
            map[i][0] = 1;
            map[i][6] = 1;
        }
        map[3][1] = 1;
        map[3][2] = 1;
        //检查地图
        System.out.println("迷宫地图：");
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
        //从[1][1]开始走
        if (findPath(map, 1, 1)) {
            System.out.println("找到通路，路线如下");
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 7; j++) {
                    System.out.print(map[i][j] + " ");
                }
                System.out.println();
            }
        }
    }

    //使用递归回溯给小球找路
    //通路走过的点为2，走不通的点为3，map[6][5]为2时说明通路已经找到
    //走迷宫时的策略为：下--右--上--左
    public static boolean findPath(int[][] map, int i, int j) {
        if (map[6][5] == 2) {   //已经成功走到终点，通路找到
            return true;
        } else {
            if (map[i][j] == 0) {   //如果[i][j]点还没走过，就走到该点并按照策略尝试
                map[i][j] = 2;
                if (findPath(map, i + 1, j)) {  //下
                    return true;
                }  if (findPath(map, i, j + 1)) {  //右 上
                    return true;
                }  if (findPath(map, i-1 , j)) {  //上 右
                    return true;
                }  if (findPath(map, i, j -   1)) {  //左
                    return true;
                } else {  //下、右、上、左都走不通，说明该点是死路，置为3
                    map[i][j] = 3;
                    return false;
                }
            } else {   //该点已走过，或是墙1，或是重复路线2，或是死路3，因此直接返回false
                return false;
            }
        }
    }

}
