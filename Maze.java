import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Maze {
    private static Node[][] resultArr;
    private static final int[] dx = {1, -1, 0, 0};
    private static final int[] dy = {0, 0, 1, -1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int height = Integer.parseInt(st.nextToken());
        int width = Integer.parseInt(st.nextToken());

        initResultArr(height, width);

        for(int i = 0; i < height; i++) {
            String[] infoArr = br.readLine().split("");
            mkResultArr(infoArr, i);
        }

        dfs(resultArr[0][0]);

        System.out.println(resultArr[height-1][width-1].getDepth());
    }

    private static void initResultArr(int height, int width) {
        resultArr = new Node[height][width];
    }

    private static void mkResultArr(String[] infoArr, int height) {
        for(int i = 0; i < infoArr.length; i++) {
            int value = Integer.parseInt(infoArr[i]);
            resultArr[height][i] = new Node(i, height, value);
        }
    }

    private static void dfs(Node startNode) {
        Queue<Node> resultQueue = new LinkedList<>();
        startNode.setDepth(1);
        startNode.visitNode();
        resultQueue.offer(startNode);

        while(!resultQueue.isEmpty()) {
            Node node = resultQueue.poll();

            int x = node.getX();
            int y = node.getY();
            int depth = node.getDepth();

            for(int i = 0; i < 4; i++) {
                int xValue = x + dx[i];
                int yValue = y + dy[i];

                if(xValue < 0 || yValue < 0 || xValue >= resultArr[0].length || yValue >= resultArr.length) continue;

                Node nextNode = resultArr[yValue][xValue];

                if(nextNode.isVisit()) continue;
                if(nextNode.getValue() == 0) continue;
                if(nextNode.getDepth() < depth + 1) continue;

                nextNode.setDepth(depth + 1);
                nextNode.visitNode();

                resultQueue.offer(nextNode);
            }
        }
    }

    private static class Node {
        private int x;
        private int y;
        private int value;
        private int depth;
        private boolean visit;

        public Node(int x, int y, int value) {
            this.x = x;
            this.y = y;
            this.value = value;
            this.depth = Integer.MAX_VALUE;
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

        public int getDepth() {
            return this.depth;
        }

        public boolean isVisit() {
            return this.visit;
        }

        public void visitNode() {
            this.visit = true;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public void setDepth(int depth) {
            this.depth = depth;
        }
    }
}
