import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class ColorBlindness {
    private static Queue<Node> resultQueue = new LinkedList<>();
    private static Node[][] resultArr;
    private static boolean[][] visitArr;
    private static int[] dx = {1, -1, 0, 0};
    private static int[] dy = {0, 0, 1, -1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        initResultArr(N);
        initVisitArr(N);

        for(int i = 0; i < N; i++) {
            String[] infoArr = br.readLine().split("");
            mkResultArr(infoArr, i);
        }

        int areaOfNormal = getAreaOfNormal();

        initVisitArr(N);

        int areaOfBlindness = getAreaOfBlindness();

        System.out.println(areaOfNormal + " " + areaOfBlindness);
    }

    private static void initResultArr(int length) {
        resultArr = new Node[length][length];
    }

    private static void initVisitArr(int length) {
        visitArr = new boolean[length][length];
    }

    private static void mkResultArr(String[] infoArr, int height) {
        for(int i = 0; i < infoArr.length; i++) {
            resultArr[height][i] = new Node(i, height, infoArr[i]);
        }
    }

    private static int getAreaOfNormal() {
        int area = 0;

        for(int i = 0; i < resultArr.length; i++) {
            for(int j = 0; j < resultArr.length; j++) {
                if(visitArr[i][j]) continue;
                area++;
                normalBfs(resultArr[i][j]);
            }
        }

        return area;
    }

    private static int getAreaOfBlindness() {
        int area = 0;

        for(int i = 0; i < resultArr.length; i++) {
            for(int j = 0; j < resultArr.length; j++) {
                if(visitArr[i][j]) continue;
                area++;
                blindBfs(resultArr[i][j]);
            }
        }

        return area;
    }

    private static void normalBfs(Node startNode) {
        resultQueue.offer(startNode);
        String color = startNode.getValue();

        while(!resultQueue.isEmpty()) {
            Node node = resultQueue.poll();

            int x = node.getX();
            int y = node.getY();

            for(int i = 0; i < 4; i++) {
                int xValue = x + dx[i];
                int yValue = y + dy[i];

                if(xValue < 0 || yValue < 0 || xValue >= resultArr.length || yValue >= resultArr.length) continue;
                if(visitArr[yValue][xValue]) continue;
                if(!resultArr[yValue][xValue].getValue().equals(color)) continue;
                visitArr[yValue][xValue] = true;
                resultQueue.offer(resultArr[yValue][xValue]);
            }
        }
    }

    private static void blindBfs(Node startNode) {
        resultQueue.offer(startNode);
        String color = startNode.getValue();

        while(!resultQueue.isEmpty()) {
            Node node = resultQueue.poll();

            int x = node.getX();
            int y = node.getY();

            for(int i = 0; i < 4; i++) {
                int xValue = x + dx[i];
                int yValue = y + dy[i];

                if(xValue < 0 || yValue < 0 || xValue >= resultArr.length || yValue >= resultArr.length) continue;
                if(visitArr[yValue][xValue]) continue;
                if(color.equals("B") && resultArr[yValue][xValue].getValue().equals("B")) {
                    visitArr[yValue][xValue] = true;
                    resultQueue.offer(resultArr[yValue][xValue]);
                    continue;
                }
                if(!color.equals("B") && !resultArr[yValue][xValue].getValue().equals("B")) {
                    visitArr[yValue][xValue] = true;
                    resultQueue.offer(resultArr[yValue][xValue]);
                }
            }
        }
    }

    private static class Node {
        private int x;
        private int y;
        private String value;

        public Node(int x, int y, String value) {
            this.x = x;
            this.y = y;
            this.value = value;
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
    }
}
