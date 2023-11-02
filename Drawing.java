import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Queue;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Drawing {
    public static Node[][] resultArr;
    public static int maxArea = Integer.MIN_VALUE;
    public static int drawingCount = 0;
    public static int[] dx = {1, -1, 0, 0};
    public static int[] dy = {0, 0, 1, -1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        String[] infoArr = br.readLine().split(" ");

        int height = Integer.parseInt(infoArr[0]);
        int width = Integer.parseInt(infoArr[1]);

        initResultArr(height, width);

        for(int i = 0; i < height; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            mkResultArr(st, i);
        }

        getResult();

        System.out.println(drawingCount);
        System.out.println(maxArea);
    }

    public static void getResult() {
        for(int i = 0; i < resultArr.length; i++) {
            for(int j = 0; j < resultArr[i].length; j++) {
                Node node = resultArr[i][j];

                if(node.isVisit()) continue;
                if(node.getValue() == 0) continue;

                drawingCount++;
                maxArea = Math.max(bfs(node), maxArea);
            }
        }

        if(drawingCount == 0) maxArea = 0;
    }

    public static int bfs(Node node) {
        Queue<Node> resultQueue = new LinkedList<>();
        resultQueue.offer(node);
        int area = 0;

        while(!resultQueue.isEmpty()) {
            Node point = resultQueue.poll();

            if(point.isVisit()) continue;
            if(point.getValue() == 0) continue;

            point.visitNode();
            area++;

            int xValue = point.getX();
            int yValue = point.getY();

            for(int i = 0; i < 4; i++) {
                int x = dx[i] + xValue;
                int y = dy[i] + yValue;

                if(x < 0 || y < 0 || x >= resultArr[0].length || y >= resultArr.length) continue;

                resultQueue.offer(resultArr[y][x]);
            }
        }

        return area;
    }

    public static void mkResultArr(StringTokenizer st, int height) {
        int numValue = 0;

        for(;st.hasMoreTokens();) {
            resultArr[height][numValue] = new Node(numValue, height, Integer.parseInt(st.nextToken()));
            numValue++;
        }
    }

    public static void initResultArr(int height, int width) {
        resultArr = new Node[height][width];
    }

    public static class Node {
        private int x;
        private int y;
        private int value;
        private boolean visit;

        public Node(int x, int y, int value) {
            this.x = x;
            this.y = y;
            this.value = value;
            this.visit = false;
        }

        public int getX() {
            return this.x;
        }

        public int getY() {
            return this.y;
        }

        public int getValue() {
            return this.value;
        }

        public boolean isVisit() {
            return this.visit;
        }

        public void visitNode() {
            this.visit = true;
        }
    }
}
