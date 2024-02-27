import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class MovePopulation {
    private static int[][] resultArr;
    private final static int[] dx = {1, -1, 0, 0};
    private final static int[] dy = {0, 0, 1, -1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int resultCount = 0;

        int N = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());

        initResultArr(N);

        for(int height = 0; height < N; height++) {
            st = new StringTokenizer(br.readLine(), " ");
            mkResultArr(st, height);
        }

        while(true) {
            boolean[][] visitArr = new boolean[N][N];
            boolean converted = false;

            for(int height = 0; height < N; height++) {
                for(int width = 0; width < N; width++) {
                    if(visitArr[height][width]) continue;

                    boolean flag = false;
                    int population = resultArr[height][width];

                    for(int i = 0; i < 4; i++) {
                        int x = width + dx[i];
                        int y = height + dy[i];

                        if(x < 0 || y < 0 || x >= N || y >= N) continue;
                        if(visitArr[y][x]) continue;

                        int otherPopulation = resultArr[y][x];

                        if(Math.abs(population - otherPopulation) < L) continue;
                        if(Math.abs(population - otherPopulation) > R) continue;

                        flag = true;
                        break;
                    }

                    if(flag) {
                        mark(visitArr, height, width, L, R);
                        converted = true;
                    }
                }
            }

            if(!converted) break;

            resultCount++;
        }

        System.out.println(resultCount);
    }

    private static void initResultArr(int length) {
        resultArr = new int[length][length];
    }

    private static void mkResultArr(StringTokenizer st, int height) {
        int index = 0;

        for(;st.hasMoreTokens();) {
            resultArr[height][index++] = Integer.parseInt(st.nextToken());
        }
    }

    private static void mark(boolean[][] visitArr, int startHeight, int startWidth, int low, int high) {
        Queue<int[]> resultQueue = new LinkedList<>();
        List<int[]> resultList = new LinkedList<>();

        visitArr[startHeight][startWidth] = true;

        resultQueue.offer(new int[]{startHeight, startWidth});
        resultList.add(new int[]{startHeight, startWidth});

        int sum = 0;

        while(!resultQueue.isEmpty()) {
            int[] arr = resultQueue.poll();

            int x = arr[1];
            int y = arr[0];

            sum += resultArr[y][x];

            for(int i = 0; i < 4; i++) {
                int xValue = x + dx[i];
                int yValue = y + dy[i];

                if(xValue < 0 || yValue < 0 || xValue >= resultArr.length || yValue >= resultArr.length) continue;
                if(visitArr[yValue][xValue]) continue;

                int population = resultArr[yValue][xValue];

                if(Math.abs(population - resultArr[y][x]) < low) continue;
                if(Math.abs(population - resultArr[y][x]) > high) continue;

                visitArr[yValue][xValue] = true;

                resultQueue.offer(new int[]{yValue, xValue});
                resultList.add(new int[]{yValue, xValue});
            }
        }

        int markValue = sum / resultList.size();

        for(int[] arr : resultList) {
            int y = arr[0];
            int x = arr[1];

            resultArr[y][x] = markValue;
        }
    }
}