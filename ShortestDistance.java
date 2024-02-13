import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class ShortestDistance {
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
            st = new StringTokenizer(br.readLine(), " ");
            mkResultArr(st, i);
        }

        for(int i = 0; i < resultArr.length; i++) {
            for(int j = 0; j < resultArr[0].length; j++) {
                Node node = resultArr[i][j];

                if(node.getValue() != 2) continue;

                findDistance(node);
            }
        }

        printResult();
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

    private static void findDistance(Node startNode) {
        Queue<Node> resultQueue = new LinkedList<>();
        startNode.setDepth(0);
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
                if(nextNode.getValue() != 1) continue;
                if(nextNode.getDepth() <= depth + 1) continue;

                nextNode.setDepth(depth + 1);
                nextNode.visitNode();
                resultQueue.add(nextNode);
            }
        }
    }

    private static void printResult() {
        StringBuilder sb = new StringBuilder();

        for(Node[] nodeArr : resultArr) {
            for(Node node : nodeArr) {
                int value = node.getValue();
                int depth = node.getDepth();

                if(value == 0) {
                    sb.append("0").append(" ");
                    continue;
                }

                if(value == 1 && depth == Integer.MAX_VALUE) {
                    sb.append(-1).append(" ");
                    continue;
                }

                sb.append(depth).append(" ");
            }

            sb.append("\n");
        }

        System.out.println(sb);
    }

    private static class Node {
        private int x;
        private int y;
        private boolean visit;
        private int value;
        private int depth;

        public Node(int x, int y, int value) {
            this.x = x;
            this.y = y;
            this.visit = false;
            this.value = value;
            this.depth = Integer.MAX_VALUE;
        }

        public int getX() {
            return this.x;
        }

        public int getY() {
            return this.y;
        }

        public boolean isVisit() {
            return this.visit;
        }

        public int getValue() {
            return this.value;
        }

        public int getDepth() {
            return this.depth;
        }

        public void setDepth(int depth) {
            this.depth = depth;
        }

        public void visitNode() {
            this.visit = true;
        }
    }
}
