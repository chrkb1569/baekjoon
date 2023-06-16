import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;
import java.util.Queue;

public class Safe_Area {

    public static class Node {
        private int x;
        private int y;
        private boolean sink;
        private boolean visit;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
            this.sink = false;
            this.visit = false;
        }

        public int getX() {
            return this.x;
        }

        public int getY() {
            return this.y;
        }

        public boolean isSink() {
            return this.sink;
        }

        public boolean isVisit() {
            return this.visit;
        }

        public void setSink() {
            this.sink = true;
        }

        public void visit() {
            this.visit = true;
        }
    }

    public static int[][] resultArr;
    public static Node[][][] visitArr;
    public static int maxHeight = Integer.MIN_VALUE;
    public static Queue<Node> resultQueue;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        initResultArr(N);

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            mkResultArr(st, i);
        }

        initVisitArr(N);
        mkVisitArr();

        System.out.println(getResultCount());
    }

    public static int getResultCount() {
        int count = 0;
        int resultCount = Integer.MIN_VALUE;
        for(int i = 0; i < maxHeight; i++) {
            initResultQueue();
            count = 0;

            for(int j = 0; j < resultArr.length; j++) {
                for(int k = 0; k < resultArr.length; k++) {
                    Node node = visitArr[i][j][k];

                    if(!node.isSink()) continue;
                    if(node.isVisit()) continue;
                    count++;
                    resultQueue.offer(node);

                    while(!resultQueue.isEmpty()) {
                        Node value = resultQueue.poll();

                        if(value.isVisit()) continue;
                        if(!value.isSink()) continue;

                        value.visit();

                        int x = value.getX();
                        int y = value.getY();

                        if(x > 0) resultQueue.offer(visitArr[i][y][x-1]);
                        if(x < resultArr.length - 1) resultQueue.offer(visitArr[i][y][x+1]);
                        if(y > 0) resultQueue.offer(visitArr[i][y-1][x]);
                        if(y < resultArr.length - 1) resultQueue.offer(visitArr[i][y+1][x]);
                    }
                }
            }
            resultCount = Math.max(resultCount, count);
        }

        return (resultCount == 0)? 1 : resultCount;
    }

    public static void initVisitArr(int N) {
        visitArr = new Node[maxHeight][N][N];
    }

    public static void initResultQueue() {
        resultQueue = new LinkedList<>();
    }

    public static void mkVisitArr() {
        int length = resultArr.length;

        for(int i = 0; i < length; i++) {
            for(int j = 0; j < length; j++) {
                int value = resultArr[i][j];

                for(int h = 0; h < value - 1; h++) {
                    visitArr[h][i][j] = new Node(j, i);
                    visitArr[h][i][j].setSink();
                }

                for(int h = value - 1; h < maxHeight; h++) {
                    visitArr[h][i][j] = new Node(j, i);
                }
            }
        }
    }

    public static void mkResultArr(StringTokenizer st, int index) {
        int numValue = 0;

        for(;st.hasMoreTokens();) {
            int value = Integer.parseInt(st.nextToken());
            maxHeight = Math.max(maxHeight, value);
            resultArr[index][numValue++] = value;
        }
    }

    public static void initResultArr(int N) {
        resultArr = new int[N][N];
    }
}
