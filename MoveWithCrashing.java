import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Queue;
import java.util.LinkedList;

public class MoveWithCrashing {
    private static Queue<Move> resultQueue = new LinkedList<>();
    private static int[][] resultArr;
    private static boolean[][][] visit;
    private final static int[] dx = {1, -1, 0, 0};
    private final static int[] dy = {0, 0, 1, -1};
    private static int minValue = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] infoArr = br.readLine().split(" ");

        int height = Integer.parseInt(infoArr[0]);
        int width = Integer.parseInt(infoArr[1]);

        initArr(height, width);

        for(int i = 0; i < height; i++) {
            infoArr = br.readLine().split("");
            mkResultArr(infoArr, i);
        }

        bfs();

        printResult();
    }

    public static void initArr(int height, int width) {
        resultArr = new int[height][width];
        visit = new boolean[2][height][width];
    }

    public static void mkResultArr(String[] infoArr, int height) {
        for(int i = 0; i < infoArr.length; i++) {
            resultArr[height][i] = Integer.parseInt(infoArr[i]);
        }
    }

    public static void bfs() {
        Move startMove = new Move(0, 0, 1);
        resultQueue.offer(startMove);

        while(!resultQueue.isEmpty()) {
            Move move = resultQueue.poll();

            int moveX = move.getX();
            int moveY = move.getY();
            int count = move.getCount();

            if(moveX == resultArr[0].length-1 && moveY == resultArr.length - 1) {
                minValue = Math.min(minValue, count);
                continue;
            }

            for(int i = 0; i < 4; i++) {
                int xValue = moveX + dx[i];
                int yValue = moveY + dy[i];

                if(xValue < 0 || yValue < 0 || xValue >= resultArr[0].length || yValue >= resultArr.length) continue;

                int value = resultArr[yValue][xValue];

                if(!move.isBreakable() && value == 0 && !visit[1][yValue][xValue]) {
                    visit[1][yValue][xValue] = true;
                    resultQueue.offer(new Move(xValue, yValue, count + 1, false));
                }
                if(move.isBreakable() && value == 0 && !visit[0][yValue][xValue]) {
                    visit[0][yValue][xValue] = true;
                    resultQueue.offer(new Move(xValue, yValue, count + 1));
                }
                if(move.isBreakable() && value == 1 && !visit[1][yValue][xValue]) {
                    visit[1][yValue][xValue] = true;
                    resultQueue.offer(new Move(xValue, yValue, count + 1, false));
                }
            }
        }

    }

    public static void printResult() {
        if(minValue == Integer.MAX_VALUE) System.out.println("-1");
        else System.out.println(minValue);
    }


    private static class Move {
        private int x;
        private int y;
        private int count;
        private boolean breakable;

        public Move(int x, int y, int count) {
            this.x = x;
            this.y = y;
            this.count = count;
            this.breakable = true;
        }

        public Move(int x, int y, int count, boolean breakable) {
            this.x = x;
            this.y = y;
            this.count = count;
            this.breakable = breakable;
        }

        public int getX() {
            return this.x;
        }

        public int getY() {
            return this.y;
        }

        public int getCount() {
            return this.count;
        }

        public boolean isBreakable() {
            return this.breakable;
        }
    }
}
