import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Sheep {
    private static Node[][] resultArr;
    private final static int[] dx = {1, -1, 0, 0};
    private final static int[] dy = {0, 0, 1, -1};

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

        int sheepCount = 0;
        int wolfCount = 0;

        for(int i = 0; i < resultArr.length; i++) {
            for(int j = 0; j < resultArr[0].length; j++) {
                Node node = resultArr[i][j];

                if(node.isVisit()) continue;
                if(node.getValue().equals("#")) continue;

                Result result = bfs(node);

                sheepCount += result.getSheepCount();
                wolfCount += result.getWolfCount();
            }
        }

        System.out.println(sheepCount + " " + wolfCount);
    }

    private static void initResultArr(int height, int width) {
        resultArr = new Node[height][width];
    }

    private static void mkResultArr(String[] valueArr, int height) {
        for(int i = 0; i < valueArr.length; i++) {
            resultArr[height][i] = new Node(i, height, valueArr[i]);
        }
    }

    private static Result bfs(Node startNode) {
        Queue<Node> resultQueue = new LinkedList<>();
        startNode.visitNode();
        resultQueue.offer(startNode);
        int sheepCount = 0;
        int wolfCount = 0;

        while(!resultQueue.isEmpty()) {
            Node node = resultQueue.poll();

            int x = node.getX();
            int y = node.getY();
            String value = node.getValue();

            if(value.equals("o")) {
                sheepCount++;
            }

            if(value.equals("v")) {
                wolfCount++;
            }

            for(int i = 0; i < 4; i++) {
                int xValue = x + dx[i];
                int yValue = y + dy[i];

                if(xValue < 0 || yValue < 0 || xValue >= resultArr[0].length || yValue >= resultArr.length) continue;

                Node nextNode = resultArr[yValue][xValue];

                if(nextNode.isVisit()) continue;
                if(nextNode.getValue().equals("#")) continue;

                nextNode.visitNode();
                resultQueue.offer(nextNode);
            }
        }

        if(sheepCount > wolfCount) wolfCount = 0;
        if(sheepCount <= wolfCount) sheepCount = 0;

        return new Result(sheepCount, wolfCount);
    }

    private static class Node {
        private int x;
        private int y;
        private String value;
        private boolean visit;

        public Node(int x, int y, String value) {
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

        public String getValue() {
            return this.value;
        }

        public boolean isVisit() {
            return this.visit;
        }

        public void visitNode() {
            this.visit = true;
        }
    }

    private static class Result {
        private int sheepCount;
        private int wolfCount;

        public Result(int sheepCount, int wolfCount) {
            this.sheepCount = sheepCount;
            this.wolfCount = wolfCount;
        }

        public int getSheepCount() {
            return this.sheepCount;
        }

        public int getWolfCount() {
            return this.wolfCount;
        }
    }
}
