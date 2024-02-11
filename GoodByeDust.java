import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class GoodByeDust {
    private static Queue<Node> dustQueue = new LinkedList<>();
    private static int[][] resultArr;
    private static Node upperCleaner;
    private static Node lowerCleaner;
    private final static int[] dx = {1, -1, 0, 0};
    private final static int[] dy = {0, 0, 1, -1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int height = Integer.parseInt(st.nextToken());
        int width = Integer.parseInt(st.nextToken());
        int time = Integer.parseInt(st.nextToken());

        initResultArr(height, width);

        for(int i = 0; i < height; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            mkResultArr(st, i);
        }

        for(int i = 0; i < time; i++) {
            processCommand();
        }

        printResult();
    }

    private static void initResultArr(int height, int width) {
        resultArr = new int[height][width];
    }

    private static void mkResultArr(StringTokenizer st, int height) {
        int index = 0;

        for(;st.hasMoreTokens();) {
            int value = Integer.parseInt(st.nextToken());

            if(value == -1 && upperCleaner == null) {
                upperCleaner = new Node(index, height);
            }
            else if(value == -1 && lowerCleaner == null) {
                lowerCleaner = new Node(index, height);
            }
            else if(value != 0) {
                dustQueue.offer(new Node(index, height));
            }

            resultArr[height][index] = value;
            index++;
        }
    }

    private static void processCommand() {
        spreadDust();
        operateUpperCleaner();
        operateLowerCleaner();
        mkDustQueue();
    }

    private static void spreadDust() {
        int[][] spreadArr = new int[resultArr.length][resultArr[0].length];

        while(!dustQueue.isEmpty()) {
            Node dust = dustQueue.poll();

            int x = dust.getX();
            int y = dust.getY();
            int value = resultArr[y][x];

            int spreadValue = value / 5;

            for(int j = 0; j < 4; j++) {
                int xValue = x + dx[j];
                int yValue = y + dy[j];

                if(xValue < 0 || yValue < 0 || xValue >= resultArr[0].length || yValue >= resultArr.length) continue;
                if(isCleanerLocation(xValue, yValue)) continue;

                spreadArr[yValue][xValue] += spreadValue;
                value -= spreadValue;
            }

            spreadArr[y][x] += value;
        }

        for(int height = 0; height < resultArr.length; height++) {
            for(int width = 0; width < resultArr[0].length; width++) {
                resultArr[height][width] = spreadArr[height][width];
            }
        }
    }

    private static void operateUpperCleaner() {
        int x = upperCleaner.getX();
        int y = upperCleaner.getY();

        int tmp = resultArr[y][resultArr[0].length-1];

        for(int width = resultArr[0].length-1; width > 0; width--) {
            resultArr[y][width] = resultArr[y][width-1];
        }

        int value = resultArr[0][resultArr[0].length-1];

        for(int height = 0; height < y; height++) {
            resultArr[height][resultArr[0].length-1] = resultArr[height+1][resultArr[0].length-1];
        }

        resultArr[y-1][resultArr[0].length-1] = tmp;

        tmp = resultArr[0][0];

        for(int width = 0; width < resultArr[0].length-1; width++) {
            resultArr[0][width] = resultArr[0][width+1];
        }

        resultArr[0][resultArr[0].length-2] = value;

        for(int height = y; height > 0; height--) {
            resultArr[height][x] = resultArr[height-1][x];
        }

        resultArr[1][0] = tmp;
        resultArr[y][x] = 0;
    }

    private static void operateLowerCleaner() {
        int x = lowerCleaner.getX();
        int y = lowerCleaner.getY();

        int tmp = resultArr[y][resultArr[0].length-1];

        for(int width = resultArr[0].length - 1; width > 0; width--) {
            resultArr[y][width] = resultArr[y][width-1];
        }

        int value = resultArr[resultArr.length-1][resultArr[0].length-1];

        for(int height = resultArr.length - 1; height > y; height--) {
            resultArr[height][resultArr[0].length-1] = resultArr[height-1][resultArr[0].length-1];
        }

        resultArr[y+1][resultArr[0].length-1] = tmp;
        tmp = resultArr[resultArr.length-1][x];

        for(int width = x; width < resultArr[0].length-1; width++) {
            resultArr[resultArr.length-1][width] = resultArr[resultArr.length-1][width+1];
        }

        resultArr[resultArr.length-1][resultArr[0].length-2] = value;

        for(int height = y; height < resultArr.length-1; height++) {
            resultArr[height][x] = resultArr[height+1][x];
        }

        resultArr[y][x] = 0;
        resultArr[resultArr.length-2][x] = tmp;
    }

    private static boolean isCleanerLocation(int x, int y) {
        int upperX = upperCleaner.getX();
        int upperY = upperCleaner.getY();

        int lowerX = lowerCleaner.getX();
        int lowerY = lowerCleaner.getY();

        if(x == upperX && y == upperY) return true;
        return x == lowerX && y == lowerY;
    }

    private static void mkDustQueue() {
        for(int height = 0; height < resultArr.length; height++) {
            for(int width = 0; width < resultArr[0].length; width++) {
                if(resultArr[height][width] != 0) dustQueue.add(new Node(width, height));
            }
        }
    }

    private static void printResult() {
        StringBuilder sb = new StringBuilder();
        int sum = 0;

        for(int height = 0; height < resultArr.length; height++) {
            for(int width = 0; width < resultArr[0].length; width++) {
                int value = resultArr[height][width];

                if(value == 0 || value == -1) continue;

                sum += value;
            }
        }

        sb.append(sum);
        System.out.println(sb);
    }

    private static class Node {
        private int x;
        private int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return this.x;
        }

        public int getY() {
            return this.y;
        }
    }
}
