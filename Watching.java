import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.util.Queue;

public class Watching {
    private static PriorityQueue<CCTV> resultQueue = new PriorityQueue<>();
    private static Queue<char[][]> processQueue = new LinkedList<>();
    private final static char WALL_POINT = '6';
    private final static char EMPTY_POINT = '0';
    private final static char CCTV_1 = '1';
    private final static char CCTV_2 = '2';
    private final static char CCTV_3 = '3';
    private final static char CCTV_4 = '4';

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int N = Integer.parseInt(st.nextToken()); // height
        int M = Integer.parseInt(st.nextToken()); // width

        char[][] resultArr = new char[N][M];

        for(int height = 0; height < N; height++) {
            st = new StringTokenizer(br.readLine(), " ");
            mkResultArr(resultArr, st, height);
        }

        processQueue.add(resultArr);

        while(!resultQueue.isEmpty()) {
            CCTV cctv = resultQueue.poll();

            char type = cctv.getType();
            int x = cctv.getX();
            int y = cctv.getY();

            processCommand(type, y, x);
        }

        System.out.println(getCount());
    }

    private static void mkResultArr(char[][] arr, StringTokenizer st, int height) {
        int index = 0;

        for(;st.hasMoreTokens();) {
            char value = st.nextToken().charAt(0);

            if(value != WALL_POINT && value != EMPTY_POINT) {
                resultQueue.add(new CCTV(value, index, height));
            }

            arr[height][index] = value;
            index++;
        }
    }

    private static void processCommand(char type, int height, int width) {
        if(type == CCTV_1) {
            processType1(height, width);
            return;
        }

        if(type == CCTV_2) {
            processType2(height, width);
            return;
        }

        if(type == CCTV_3) {
            processType3(height, width);
            return;
        }

        if(type == CCTV_4) {
            processType4(height, width);
            return;
        }

        processType5(height, width);
    }

    private static void processType1(int height, int width) {
        int length = processQueue.size();

        for(int index = 0; index < length; index++) {
            char[][] resultArr = processQueue.poll();

            char[][] copyArr = copyArr(resultArr);
            markNorth(copyArr, height, width);
            processQueue.add(copyArr);

            copyArr = copyArr(resultArr);
            markEast(copyArr, height, width);
            processQueue.add(copyArr);

            copyArr = copyArr(resultArr);
            markSouth(copyArr, height, width);
            processQueue.add(copyArr);

            copyArr = copyArr(resultArr);
            markWest(copyArr, height, width);
            processQueue.add(copyArr);
        }
    }

    private static void processType2(int height, int width) {
        int length = processQueue.size();

        for(int index = 0; index < length; index++) {
            char[][] resultArr = processQueue.poll();

            char[][] copyArr = copyArr(resultArr);
            markWest(copyArr, height, width);
            markEast(copyArr, height, width);
            processQueue.add(copyArr);

            copyArr = copyArr(resultArr);
            markNorth(copyArr, height, width);
            markSouth(copyArr, height, width);
            processQueue.add(copyArr);
        }
    }

    private static void processType3(int height, int width) {
        int length = processQueue.size();

        for(int index = 0; index < length; index++) {
            char[][] resultArr = processQueue.poll();

            char[][] copyArr = copyArr(resultArr);
            markNorth(copyArr, height, width);
            markEast(copyArr, height, width);
            processQueue.add(copyArr);

            copyArr = copyArr(resultArr);
            markEast(copyArr, height, width);
            markSouth(copyArr, height, width);
            processQueue.add(copyArr);

            copyArr = copyArr(resultArr);
            markSouth(copyArr, height, width);
            markWest(copyArr, height, width);
            processQueue.add(copyArr);

            copyArr = copyArr(resultArr);
            markWest(copyArr, height, width);
            markNorth(copyArr, height, width);
            processQueue.add(copyArr);
        }
    }

    private static void processType4(int height, int width) {
        int length = processQueue.size();

        for(int index = 0; index < length; index++) {
            char[][] resultArr = processQueue.poll();

            char[][] copyArr = copyArr(resultArr);

            markWest(copyArr, height, width);
            markNorth(copyArr, height, width);
            markEast(copyArr, height, width);

            processQueue.add(copyArr);

            copyArr = copyArr(resultArr);

            markNorth(copyArr, height, width);
            markEast(copyArr, height, width);
            markSouth(copyArr, height, width);

            processQueue.add(copyArr);

            copyArr = copyArr(resultArr);

            markEast(copyArr, height, width);
            markSouth(copyArr, height, width);
            markWest(copyArr, height, width);

            processQueue.add(copyArr);

            copyArr = copyArr(resultArr);

            markSouth(copyArr, height, width);
            markWest(copyArr, height, width);
            markNorth(copyArr, height, width);

            processQueue.add(copyArr);
        }
    }

    private static void processType5(int height, int width) {
        int length = processQueue.size();

        for(int index = 0; index < length; index++) {
            char[][] resultArr = processQueue.poll();

            markNorth(resultArr, height, width);
            markEast(resultArr, height, width);
            markSouth(resultArr, height, width);
            markWest(resultArr, height, width);

            processQueue.add(resultArr);
        }
    }

    private static void markNorth(char[][] resultArr, int height, int width) {
        for(int i = height - 1; i >= 0; i--) {
            if(resultArr[i][width] == WALL_POINT) break;
            if(resultArr[i][width] != EMPTY_POINT) continue;
            resultArr[i][width] = '#';
        }
    }

    private static void markEast(char[][] resultArr, int height, int width) {
        for(int i = width + 1; i < resultArr[0].length; i++) {
            if(resultArr[height][i] == WALL_POINT) break;
            if(resultArr[height][i] != EMPTY_POINT) continue;
            resultArr[height][i] = '#';
        }
    }

    private static void markSouth(char[][] resultArr, int height, int width) {
        for(int i = height + 1; i < resultArr.length; i++) {
            if(resultArr[i][width] == WALL_POINT) break;
            if(resultArr[i][width] != EMPTY_POINT) continue;
            resultArr[i][width] = '#';
        }
    }

    private static void markWest(char[][] resultArr, int height, int width) {
        for(int i = width - 1; i >= 0; i--) {
            if(resultArr[height][i] == WALL_POINT) break;
            if(resultArr[height][i] != EMPTY_POINT) continue;
            resultArr[height][i] = '#';
        }
    }

    private static char[][] copyArr(char[][] resultArr) {
        char[][] arr = new char[resultArr.length][resultArr[0].length];

        for(int height = 0; height < resultArr.length; height++) {
            for(int width = 0; width < resultArr[0].length; width++) {
                arr[height][width] = resultArr[height][width];
            }
        }

        return arr;
    }

    private static int getCount() {
        int minCount = Integer.MAX_VALUE;

        while(!processQueue.isEmpty()) {
            char[][] resultArr = processQueue.poll();

            int countOfArray = getCountOfArray(resultArr);

            minCount = Math.min(minCount, countOfArray);
        }

        if(minCount == Integer.MAX_VALUE) return 0;
        return minCount;
    }

    private static int getCountOfArray(char[][] resultArr) {
        int count = 0;

        for(int height = 0; height < resultArr.length; height++) {
            for(int width = 0; width < resultArr[0].length; width++) {
                if(resultArr[height][width] == '0') count++;
            }
        }

        return count;
    }

    private static class CCTV implements Comparable<CCTV> {
        private char type;
        private int x;
        private int y;

        CCTV(char type, int x, int y) {
            this.type = type;
            this.x = x;
            this.y = y;
        }

        public char getType() {
            return this.type;
        }

        public int getX() {
            return this.x;
        }

        public int getY() {
            return this.y;
        }

        @Override
        public int compareTo(CCTV cctv) {
            return Character.compare(cctv.getType(), this.type);
        }
    }
}