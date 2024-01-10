import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class IslandCount {
    private static Node[][] resultArr;
    private static StringBuilder sb = new StringBuilder();
    private static final int[] dx = {1, -1, 0, 0, 1, 1, -1, -1};
    private static final int[] dy = {0, 0, 1, -1, 1, -1, 1, -1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        while(true) {
            st = new StringTokenizer(br.readLine(), " ");

            int width = Integer.parseInt(st.nextToken());
            int height = Integer.parseInt(st.nextToken());

            if(width == 0 && height == 0) break;

            initResultArr(height, width);

            for(int i = 0; i < height; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                mkResultArr(st, i);
            }

            int islandCount = 0;

            for(int i = 0; i < resultArr.length; i++) {
                for(int j = 0; j < resultArr[0].length; j++) {
                    Node node = resultArr[i][j];

                    if(node.getValue() == 0) continue;
                    if(node.isVisit()) continue;

                    bfs(node);
                    islandCount++;
                }
            }

            sb.append(islandCount).append("\n");
        }

        System.out.println(sb);
    }

    private static void initResultArr(int height, int width) {
        resultArr = new Node[height][width];
    }

    private static void mkResultArr(StringTokenizer st, int height) {
        int index = 0;

        for(;st.hasMoreTokens();) {
            resultArr[height][index] = new Node(index, height, Integer.parseInt(st.nextToken()));
            index++;
        }
    }

    private static void bfs(Node startNode) {
        Queue<Node> resultQueue = new LinkedList<>();
        startNode.visitNode();
        resultQueue.offer(startNode);

        while(!resultQueue.isEmpty()) {
            Node node = resultQueue.poll();

            int x = node.getX();
            int y = node.getY();

            for(int i = 0; i < 8; i++) {
                int xValue = x + dx[i];
                int yValue = y + dy[i];

                if(xValue < 0 || yValue < 0 || xValue >= resultArr[0].length || yValue >= resultArr.length) continue;

                Node nextNode = resultArr[yValue][xValue];

                if(nextNode.isVisit()) continue;
                if(nextNode.getValue() == 0) continue;

                nextNode.visitNode();
                resultQueue.offer(nextNode);
            }
        }
    }

    private static class Node {
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
