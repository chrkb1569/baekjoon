import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class TreasureIsland {
    private static Node[][] resultArr;
    private static int maxLength = Integer.MIN_VALUE;
    private static final int[] dx = {1, -1, 0, 0};
    private static final int[] dy = {0, 0, 1, -1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] infoArr = br.readLine().split(" ");

        int height = Integer.parseInt(infoArr[0]);
        int width = Integer.parseInt(infoArr[1]);

        initResultArr(height, width);

        for(int i = 0; i < height; i++) {
            infoArr = br.readLine().split("");
            mkResultArr(infoArr, i);
        }

        for(int i = 0; i < resultArr.length; i++) {
            for(int j = 0; j < resultArr[i].length; j++) {
                Node node  = resultArr[i][j];

                if(!node.isLand()) continue;
                int dfsResult = bfs(node);
                maxLength = Math.max(maxLength, dfsResult);
                initArr();
            }
        }

        System.out.println(maxLength);
    }

    private static void initResultArr(int height, int width) {
        resultArr = new Node[height][width];
    }

    private static void mkResultArr(String[] infoArr, int height) {
        for(int i = 0; i < infoArr.length; i++) {
            String value = infoArr[i];

            if(value.equals("L")) {
                resultArr[height][i] = new Node(i, height, true);
                continue;
            }

            resultArr[height][i] = new Node(i, height, false);
        }
    }

    private static int bfs(Node startNode) {
        Queue<Node> resultQueue = new LinkedList<>();
        startNode.setWeight(0);
        resultQueue.offer(startNode);
        int resultWeight = 0;

        while(!resultQueue.isEmpty()) {
            Node node = resultQueue.poll();

            if(node.isVisit()) continue;

            int x = node.getX();
            int y = node.getY();
            int weight = node.getWeight();

            node.visitNode();
            resultWeight = Math.max(resultWeight, weight);

            for(int i = 0; i < 4; i++) {
                int xValue = x + dx[i];
                int yValue = y + dy[i];

                if(xValue < 0 || yValue < 0 || xValue >= resultArr[0].length || yValue >= resultArr.length) continue;

                Node offerValue = resultArr[yValue][xValue];
                int nodeWeight = offerValue.getWeight();

                if(!offerValue.isLand()) continue;
                if(nodeWeight < weight + 1) continue;

                offerValue.setWeight(weight + 1);
                resultQueue.offer(offerValue);
            }
        }

        return resultWeight;
    }

    private static void initArr() {
        for(Node[] arr : resultArr) {
            for(Node node : arr) {
                node.initNode();
                node.setWeight(Integer.MAX_VALUE);
            }
        }
    }

    private static class Node {
        private int x;
        private int y;
        private int weight;
        private boolean visit;
        private boolean land;

        public Node(int x, int y, boolean land) {
            this.x = x;
            this.y = y;
            this.weight = Integer.MAX_VALUE;
            this.land = land;
            this.visit = false;
        }

        public int getX() {
            return this.x;
        }

        public int getY() {
            return this.y;
        }

        public int getWeight() {
            return this.weight;
        }

        public boolean isVisit() {
            return this.visit;
        }

        public boolean isLand() {
            return this.land;
        }

        public void setWeight(int weight) {
            this.weight = weight;
        }

        public void visitNode() {
            this.visit = true;
        }

        public void initNode() {
            this.visit = false;
        }
    }
}
