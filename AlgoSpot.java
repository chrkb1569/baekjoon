import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class AlgoSpot {
    private static int[][] resultArr;
    private static final int[] dx = {1, -1, 0, 0};
    private static final int[] dy = {0, 0, 1, -1};
    private static final int EMPTY_SPACE = 0;
    private static final int WALL_SPACE = 1;
    private static int resultPoint = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int M = Integer.parseInt(st.nextToken()); // width
        int N = Integer.parseInt(st.nextToken()); // height

        initResultArr(N, M);

        for(int height = 0; height < N; height++) {
            String[] infoArr = br.readLine().split("");
            mkResultArr(infoArr, height);
        }

        startSearch();

        System.out.println(resultPoint);
    }

    private static void initResultArr(int height, int width) {
        resultArr = new int[height][width];
    }

    private static void mkResultArr(String[] infoArr, int height) {
        for(int index = 0; index < infoArr.length; index++) {
            resultArr[height][index] = Integer.parseInt(infoArr[index]);
        }
    }

    private static void startSearch() {
        Deque<Node> resultQueue = new LinkedList<>();
        resultQueue.add(new Node(0, 0, 0));

        while(!resultQueue.isEmpty()) {
            Node node = resultQueue.poll();

            int x = node.getX();
            int y = node.getY();
            int value = node.getValue();

            if(x == resultArr[0].length-1 && y == resultArr.length-1) {
                resultPoint = value;
                break;
            }

            for(int i = 0; i < 4; i++) {
                int xValue = x + dx[i];
                int yValue = y + dy[i];

                if(xValue < 0 || yValue < 0 || xValue >= resultArr[0].length || yValue >= resultArr.length) continue;
                if(resultArr[yValue][xValue] == -1) continue;

                int weight = resultArr[yValue][xValue];
                resultArr[yValue][xValue] = -1;

                if(weight == EMPTY_SPACE) {
                    resultQueue.addFirst(new Node(xValue, yValue, value));
                    continue;
                }

                if(weight == WALL_SPACE) resultQueue.addLast(new Node(xValue, yValue, value + 1));
            }
        }
    }

    private static class Node {
        private int x;
        private int y;
        private int value;

        public Node(int x, int y, int value) {
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

        public int getValue() {
            return this.value;
        }
    }
}
