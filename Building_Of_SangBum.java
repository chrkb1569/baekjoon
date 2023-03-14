import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;
import java.util.Queue;

public class Building_Of_SangBum {

    public static class Point {
        private char value;
        private boolean visit;
        private int x;
        private int y;
        private int z;
        private int count;

        public Point(char value, int x, int y, int z) {
            this.value = value;
            this.x = x;
            this.y = y;
            this.z = z;
            this.visit = false;
            this.count = 0;
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

        public char getValue() {
            return this.value;
        }

        public int getCount() {
            return this.count;
        }

        public boolean isVisit() {
            return this.visit;
        }

        public void visit() {
            this.visit = true;
        }

        public void setCount(int value) {
            this.count = value + 1;
        }
    }

    public static Point[][][] resultArr;
    public static Queue<Point> resultQueue;
    public static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        while(true) {
            String command = br.readLine();
            st = new StringTokenizer(command, " ");

            int floor = Integer.parseInt(st.nextToken());
            int height = Integer.parseInt(st.nextToken());
            int width = Integer.parseInt(st.nextToken());

            if(floor == 0 && height == 0 && width == 0) break;

            initResultArr(width, height, floor);
            initResultQueue();

            for(int i = 0; i < floor; i++) {
                for(int j = 0; j < height + 1; j++) {
                    String value = br.readLine();
                    mkResultArr(value, i, j);
                }
            }
            initStart();

            printStatus(findMinDistance());
        }

        System.out.println(sb);
    }

    public static int findMinDistance() {
        int minLength = 0;

        while(!resultQueue.isEmpty()) {
            Point point = resultQueue.poll();

            if(point.getValue() == '#') continue;
            if(point.getValue() == 'E') {
                minLength = point.getCount();
                break;
            }
            if(point.isVisit()) continue;

            point.visit();

            int x = point.getX();
            int y = point.getY();
            int z = point.getZ();
            int count = point.getCount();

            if(x > 0) {
                resultArr[z][y][x-1].setCount(count);
                resultQueue.offer(resultArr[z][y][x-1]);
            }
            if(x < resultArr[0][0].length - 1) {
                resultArr[z][y][x+1].setCount(count);
                resultQueue.offer(resultArr[z][y][x+1]);
            }
            if(y > 0) {
                resultArr[z][y-1][x].setCount(count);
                resultQueue.offer(resultArr[z][y-1][x]);
            }
            if(y < resultArr[0].length - 1) {
                resultArr[z][y+1][x].setCount(count);
                resultQueue.offer(resultArr[z][y+1][x]);
            }
            if(z > 0) {
                resultArr[z-1][y][x].setCount(count);
                resultQueue.offer(resultArr[z-1][y][x]);
            }
            if(z < resultArr.length-1) {
                resultArr[z+1][y][x].setCount(count);
                resultQueue.offer(resultArr[z+1][y][x]);
            }
        }

        return minLength;
    }

    public static void printStatus(int value) {
        if(value == 0) {
            sb.append("Trapped!" + "\n");
            return;
        }

        sb.append("Escaped in " + value + " minute(s)." + "\n");
    }

    public static void initStart() {
        for(int i = 0; i < resultArr.length; i++) {
            for(int j = 0; j < resultArr[0].length; j++) {
                for(int m = 0; m < resultArr[0][0].length; m++) {
                    if(resultArr[i][j][m].getValue() != 'S') continue;
                    resultQueue.offer(resultArr[i][j][m]);
                }
            }
        }
    }

    public static void mkResultArr(String value, int floor, int height) {
        for(int i = 0; i < value.length(); i++) {
            resultArr[floor][height][i] = new Point(value.charAt(i), i, height, floor);
        }
    }

    public static void initResultArr(int x, int y, int z) {
        resultArr = new Point[z][y][x];
    }

    public static void initResultQueue() {
        resultQueue = new LinkedList<>();
    }
}
