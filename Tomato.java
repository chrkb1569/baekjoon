import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;
import java.util.Queue;

public class Tomato {
    public static class Point {
        private int x;
        private int y;
        private int value;
        private boolean visit;

        public Point(int x, int y, int value) {
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

        public boolean isVisit() {
            return this.visit;
        }

        public void visit() {
            this.visit = true;
        }
    }

    public static Queue<Point> resultQueue;
    public static Point[][] resultArr;
    public static int maxValue = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int width = Integer.parseInt(st.nextToken());
        int height = Integer.parseInt(st.nextToken());

        initResultArr(height, width);
        initResultQueue();

        for(int i = 0; i < height; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            mkResultArr(i, st);
        }

        processBFS();
        checkValidation();

        System.out.println(maxValue - 1);
    }

    public static void processBFS() {
        while(!resultQueue.isEmpty()) {
            Point value = resultQueue.poll();

            if(value.isVisit()) continue;
            value.visit();

            int x = value.getX();
            int y = value.getY();
            int weight = value.getValue();
            maxValue = Math.max(maxValue, weight);

            if(x > 0 && resultArr[y][x-1].getValue() == 0) {
                resultArr[y][x-1].setValue(weight + 1);
                resultQueue.offer(resultArr[y][x-1]);
            }
            if(x < resultArr[0].length-1 && resultArr[y][x+1].getValue() == 0) {
                resultArr[y][x+1].setValue(weight + 1);
                resultQueue.offer(resultArr[y][x+1]);
            }
            if(y > 0 && resultArr[y-1][x].getValue() == 0) {
                resultArr[y-1][x].setValue(weight + 1);
                resultQueue.offer(resultArr[y-1][x]);
            }
            if(y < resultArr.length-1 && resultArr[y+1][x].getValue() == 0) {
                resultArr[y+1][x].setValue(weight + 1);
                resultQueue.offer(resultArr[y+1][x]);
            }
        }
    }

    public static void checkValidation() {
        for(int i = 0; i < resultArr.length; i++) {
            for(int j = 0; j < resultArr[0].length; j++) {
                if(resultArr[i][j].getValue() == 0) maxValue = 0;
            }
        }
    }

    public static void initResultQueue() {
        resultQueue = new LinkedList<>();
    }

    public static void initResultArr(int height, int width) {
        resultArr = new Point[height][width];
    }

    public static void mkResultArr(int height, StringTokenizer st) {
        int numValue = 0;

        for(;st.hasMoreTokens();) {
            int value = Integer.parseInt(st.nextToken());
            resultArr[height][numValue] = new Point(numValue, height, value);

            if(value == 1) resultQueue.offer(resultArr[height][numValue]);

            numValue++;
        }
    }
}
