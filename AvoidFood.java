import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class AvoidFood {
    public static Node[][] resultArr;
    public static int maxSize = Integer.MIN_VALUE;
    public static int[] dx = {1, -1, 0, 0};
    public static int[] dy = {0, 0, 1, -1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] infoArr = br.readLine().split(" ");

        int height = Integer.parseInt(infoArr[0]);
        int width = Integer.parseInt(infoArr[1]);
        int food = Integer.parseInt(infoArr[2]);

        initResultArr(height, width);

        for(int i = 0; i < food; i++) {
            infoArr = br.readLine().split(" ");

            int yIndex = Integer.parseInt(infoArr[0]) - 1;
            int xIndex = Integer.parseInt(infoArr[1]) - 1;

            resultArr[yIndex][xIndex].setFood();
        }

        findMaximumFoodSize();

        System.out.println(maxSize);
    }

    public static void initResultArr(int height, int width) {
        resultArr = new Node[height][width];

        for(int i = 0; i < height; i++) {
            for(int j = 0; j < width; j++) {
                resultArr[i][j] = new Node(j, i);
            }
        }
    }

    public static void findMaximumFoodSize() {
        for(int i = 0; i < resultArr.length; i++) {
            for(int j = 0; j < resultArr[i].length; j++) {
                Node node = resultArr[i][j];

                if(node.isVisit() || !node.isFood()) continue;

                int foodSize = getFoodSize(node);
                maxSize = Math.max(maxSize, foodSize);
            }
        }
    }

    public static int getFoodSize(Node startNode) {
        Queue<Node> resultQueue = new LinkedList<>();
        int size = 0;
        resultQueue.offer(startNode);

        while(!resultQueue.isEmpty()) {
            Node nodeValue = resultQueue.poll();

            if(nodeValue.isVisit() || !nodeValue.isFood()) continue;

            nodeValue.visitNode();
            size++;

            int xValue = nodeValue.getX();
            int yValue = nodeValue.getY();

            for(int i = 0; i < 4; i++) {
                int xIndex = xValue + dx[i];
                int yIndex = yValue + dy[i];

                if(xIndex < 0 || yIndex < 0 || xIndex >= resultArr[0].length || yIndex >= resultArr.length) continue;

                resultQueue.offer(resultArr[yIndex][xIndex]);
            }
        }

        return size;
    }

    private static class Node {
        private int x;
        private int y;
        private boolean visit;
        private boolean food;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
            this.visit = false;
            this.food = false;
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

        public boolean isFood() {
            return this.food;
        }

        public void visitNode() {
            this.visit = true;
        }

        public void setFood() {
            this.food = true;
        }
    }
}
