import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class BabyShark {
    private static int[][] valueArr;
    private static Shark shark;
    private final static int[] dx = {1, -1, 0, 0};
    private final static int[] dy = {0, 0, 1, -1};
    private final static int SHARK_VALUE = 9;
    private final static int INITIAL_SIZE = 2;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        initValueArr(N);

        for(int height = 0; height < N; height++) {
            st = new StringTokenizer(br.readLine(), " ");
            mkValueArr(st, height);
        }

        while(true) {
            if(!moveShark()) break;
        }

        System.out.println(shark.getTime());
    }

    private static void initValueArr(int length) {
        valueArr = new int[length][length];
    }

    private static void mkValueArr(StringTokenizer st, int height) {
        int index = 0;

        for(;st.hasMoreTokens();) {
            int value = Integer.parseInt(st.nextToken());

            if(value == SHARK_VALUE) {
                shark = new Shark(index, height);
                value = 0;
            }

            valueArr[height][index] = value;
            index++;
        }
    }

    private static boolean moveShark() {
        int length = valueArr.length;

        Queue<int[]> resultQueue = new LinkedList<>();
        boolean[][] visitArr = new boolean[length][length];
        PriorityQueue<int[]> fishQueue = new PriorityQueue<>((o1, o2) -> {
            if(o1[2] == o2[2]) {
                if(o1[1] == o2[1]) return o1[0] - o2[0];
                return o1[1] - o2[1];
            }

            return o1[2] - o2[2];
        });

        int sharkX = shark.getX();
        int sharkY = shark.getY();
        int sharkSize = shark.getSize();

        visitArr[sharkY][sharkX] = true;
        resultQueue.offer(new int[]{sharkX, sharkY, 0});

        while(!resultQueue.isEmpty()) {
            int[] arr = resultQueue.poll();

            int x = arr[0];
            int y = arr[1];
            int distance = arr[2];

            if(valueArr[y][x] != 0 && valueArr[y][x] < sharkSize) {
                fishQueue.offer(new int[]{x, y, distance});
                continue;
            }

            for(int i = 0; i < 4; i++) {
                int xValue = x + dx[i];
                int yValue = y + dy[i];

                if(xValue < 0 || yValue < 0 || xValue >= length || yValue >= length) continue;
                if(visitArr[yValue][xValue]) continue;
                if(valueArr[yValue][xValue] > sharkSize) continue;

                visitArr[yValue][xValue] = true;
                resultQueue.offer(new int[]{xValue, yValue, distance + 1});
            }
        }

        if(fishQueue.isEmpty()) return false;

        int[] arr = fishQueue.poll();

        shark.setX(arr[0]);
        shark.setY(arr[1]);
        shark.updateTime(arr[2]);
        shark.eatFish();

        valueArr[arr[1]][arr[0]] = 0;

        return true;
    }

    private static class Shark {
        private int x;
        private int y;
        private int time;
        private int size;
        private int eatCount;

        public Shark(int x, int y) {
            this.x = x;
            this.y = y;
            this.time = 0;
            this.size = INITIAL_SIZE;
            this.eatCount = 0;
        }

        public int getX() {
            return this.x;
        }

        public int getY() {
            return this.y;
        }

        public int getTime() {
            return this.time;
        }

        public int getSize() {
            return this.size;
        }

        public void setX(int x) {
            this.x = x;
        }

        public void setY(int y) {
            this.y = y;
        }

        public void updateTime(int time) {
            this.time += time;
        }

        public void eatFish() {
            this.eatCount++;

            if(eatCount == size) {
                this.size++;
                this.eatCount = 0;
            }
        }
    }
}