import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Tomato_2 {
    private static Queue<Tomato> resultQueue = new LinkedList<>();
    private static int[][][] resultArr;
    private static final int[] dx = {1, -1, 0, 0, 0, 0};
    private static final int[] dy = {0, 0, 1, -1, 0, 0};
    private static final int[] dz = {0, 0, 0, 0, 1, -1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int width = Integer.parseInt(st.nextToken());
        int height = Integer.parseInt(st.nextToken());
        int tall = Integer.parseInt(st.nextToken());

        initResultArr(tall, height, width);

        for(int i = 0; i < tall; i++) {
            for(int j = 0; j < height; j++) {
                st = new StringTokenizer(br.readLine(), " ");
                mkResultArr(st, i, j);
            }
        }

        bfs();
        printResult();
    }

    private static void initResultArr(int tall, int height, int width) {
        resultArr = new int[tall][height][width];
    }

    private static void mkResultArr(StringTokenizer st, int tall, int height) {
        int index = 0;

        for(;st.hasMoreTokens();) {
            int value = Integer.parseInt(st.nextToken());
            resultArr[tall][height][index] = value;

            if(value == 1) resultQueue.offer(new Tomato(index, height, tall));
            index++;
        }
    }

    private static void bfs() {
        while(!resultQueue.isEmpty()) {
            Tomato tomato = resultQueue.poll();

            int x = tomato.getX();
            int y = tomato.getY();
            int z = tomato.getZ();

            for(int i = 0; i < 6; i++) {
                int xValue = x + dx[i];
                int yValue = y + dy[i];
                int zValue = z + dz[i];

                if(xValue < 0 || yValue < 0 || zValue < 0 ||
                        xValue >= resultArr[0][0].length ||
                        yValue >= resultArr[0].length ||
                        zValue >= resultArr.length) continue;

                if(resultArr[zValue][yValue][xValue] == 0) {
                    resultArr[zValue][yValue][xValue] = resultArr[z][y][x] + 1;
                    resultQueue.offer(new Tomato(xValue, yValue, zValue));
                }
            }
        }
    }

    private static void printResult() {
        int maxValue = Integer.MIN_VALUE;
        int zero = 0;

        for(int i = 0; i < resultArr.length; i++) {
            for(int j = 0; j < resultArr[0].length; j++) {
                for(int m = 0; m < resultArr[0][0].length; m++) {
                    if(resultArr[i][j][m] == 0) {
                        zero++;
                        continue;
                    }
                    maxValue = Math.max(maxValue, resultArr[i][j][m]);
                }
            }
        }

        if(zero != 0) {
            System.out.println("-1");
            return;
        }

        System.out.println(maxValue - 1);
    }

    private static class Tomato {
        private int x;
        private int y;
        private int z;

        public Tomato(int x, int y, int z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }

        public int getX() {
            return this.x;
        }

        public int getY() {
            return this.y;
        }

        public int getZ() {
            return this.z;
        }
    }
}
