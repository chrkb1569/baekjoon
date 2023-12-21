import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Cabbage {
    private static Node[][] resultArr;
    private static boolean[][] visitArr;
    private static final int[] dx = {1, -1, 0, 0};
    private static final int[] dy = {0, 0, 1, -1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int testCase = Integer.parseInt(br.readLine());

        for(int i = 0; i < testCase; i++) {
            st = new StringTokenizer(br.readLine(), " ");

            int width = Integer.parseInt(st.nextToken());
            int height = Integer.parseInt(st.nextToken());
            int cabbage = Integer.parseInt(st.nextToken());

            initArr(height, width);

            for(int j = 0; j < cabbage; j++) {
                st = new StringTokenizer(br.readLine(), " ");

                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());

                resultArr[y][x].setValue(1);
            }

            sb.append(getAreaCount()).append("\n");
        }

        System.out.println(sb);
    }

    private static void initArr(int height, int width) {
        resultArr = new Node[height][width];
        visitArr = new boolean[height][width];

        for(int i = 0; i < height; i++) {
            for(int j = 0; j < width; j++) {
                resultArr[i][j] = new Node(j, i, 0);
            }
        }
    }

    private static int getAreaCount() {
        int count = 0;

        for(int height = 0; height < resultArr.length; height++) {
            for(int width = 0; width < resultArr[0].length; width++) {
                if(resultArr[height][width].getValue() == 0) continue;
                if(visitArr[height][width]) continue;

                bfs(resultArr[height][width]);
                count++;
            }
        }

        return count;
    }

    private static void bfs(Node startNode) {
        Queue<Node> resultQueue = new LinkedList<>();
        resultQueue.offer(startNode);

        while(!resultQueue.isEmpty()) {
            Node node = resultQueue.poll();

            int x = node.getX();
            int y = node.getY();

            for(int i = 0; i < 4; i++) {
                int xValue = x + dx[i];
                int yValue = y + dy[i];

                if(xValue < 0 || yValue < 0 || xValue >= resultArr[0].length || yValue >= resultArr.length) continue;
                if(resultArr[yValue][xValue].getValue() == 0) continue;
                if(visitArr[yValue][xValue]) continue;

                visitArr[yValue][xValue] = true;
                resultQueue.offer(resultArr[yValue][xValue]);
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

        public void setValue(int value) {
            this.value = value;
        }
    }
}
