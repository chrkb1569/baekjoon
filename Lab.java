import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Lab {
    public static int[][] resultArr;
    public static Queue<Node> resultQueue;
    public static int[] dx = {1, 0, -1, 0};
    public static int[] dy = {0, 1, 0, -1};
    public static int maxArea = Integer.MIN_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] infoArr = br.readLine().split(" ");

        int height = Integer.parseInt(infoArr[0]);
        int width = Integer.parseInt(infoArr[1]);

        initResultArr(height, width);

        for(int i = 0; i < height; i++) {
            infoArr = br.readLine().split(" ");
            mkResultArr(infoArr, i);
        }

        doProcess(0);

        System.out.println(maxArea);
    }

    public static void doProcess(int wallCount) {
        if(wallCount == 3) {
            int[][] arr = cpyArr();
            bfs(arr);
            maxArea = Math.max(maxArea, getArea(arr));
            return;
        }

        for(int i = 0; i < resultArr.length; i++) {
            for(int j = 0; j < resultArr[i].length; j++) {
                if(resultArr[i][j] != 0) continue;

                resultArr[i][j] = 1;
                doProcess(wallCount + 1);
                resultArr[i][j] = 0;
            }
        }
    }

    public static void bfs(int[][] arr) {
        initResultQueue(arr);

        while(!resultQueue.isEmpty()) {
            Node node = resultQueue.poll();
            int xValue = node.getX();
            int yValue = node.getY();

            for(int i = 0; i < 4; i++) {
                int x = dx[i] + xValue;
                int y = dy[i] + yValue;

                if(x < 0 || y < 0 || x >= resultArr[0].length || y >= resultArr.length) continue;
                if(arr[y][x] == 0) {
                    arr[y][x] = 2;
                    resultQueue.offer(new Node(x, y));
                }
            }
        }
    }

    public static int getArea(int[][] arr) {
        int area = 0;

        for(int i = 0; i < arr.length; i++) {
            for(int j = 0; j < arr[i].length; j++) {
                if(arr[i][j] == 0) area++;
            }
        }

        return area;
    }

    public static int[][] cpyArr() {
        int[][] arr = new int[resultArr.length][resultArr[0].length];

        for(int i = 0; i < resultArr.length; i++) {
            arr[i] = resultArr[i].clone();
        }

        return arr;
    }

    public static void mkResultArr(String[] infoArr, int height) {
        for(int i = 0; i < infoArr.length; i++) {
            resultArr[height][i] = Integer.parseInt(infoArr[i]);
        }
    }

    public static void initResultQueue(int[][] arr) {
        resultQueue = new LinkedList<>();

        for(int i = 0; i < arr.length; i++) {
            for(int j = 0; j < arr[i].length; j++) {
                if(arr[i][j] == 2) resultQueue.offer(new Node(j, i));
            }
        }
    }

    public static void initResultArr(int height, int width) {
        resultArr = new int[height][width];
    }

    public static class Node {
        private int x;
        private int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return this.x;
        }

        public int getY() {
            return this.y;
        }
    }
}

//7 7
//2 0 0 0 1 1 0
//0 0 1 0 1 2 0
//0 1 1 0 1 0 0
//0 1 0 0 0 0 0
//0 0 0 0 0 1 1
//0 1 0 0 0 0 0
//0 1 0 0 0 0 0

//        27

//        4 6
//        0 0 0 0 0 0
//        1 0 0 0 0 2
//        1 1 1 0 0 2
//        0 0 0 0 0 2
//
//        9

//        8 8
//        2 0 0 0 0 0 0 2
//        2 0 0 0 0 0 0 2
//        2 0 0 0 0 0 0 2
//        2 0 0 0 0 0 0 2
//        2 0 0 0 0 0 0 2
//        0 0 0 0 0 0 0 0
//        0 0 0 0 0 0 0 0
//        0 0 0 0 0 0 0 0
//
//        3