import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.util.Queue;

public class IceBerg {
    private static int[][] resultArr;
    private static final int[] dx = {1, -1, 0, 0};
    private static final int[] dy = {0, 0, 1, -1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int N = Integer.parseInt(st.nextToken()); // height
        int M = Integer.parseInt(st.nextToken()); // width

        initResultArr(N, M);

        for(int height = 0; height < N; height++) {
            st = new StringTokenizer(br.readLine(), " ");
            mkResultArr(st, height);
        }

        System.out.println(getYear());
    }

    private static void initResultArr(int height, int width) {
        resultArr = new int[height][width];
    }

    private static void mkResultArr(StringTokenizer st, int height) {
        int index = 0;

        for(;st.hasMoreTokens();) {
            resultArr[height][index++] = Integer.parseInt(st.nextToken());
        }
    }

    private static List<int[]> checkStatus() {
        List<int[]> checkList = new ArrayList<>();

        for(int height = 0; height < resultArr.length; height++) {
            for(int width = 0; width < resultArr[0].length; width++) {
                if(resultArr[height][width] <= 0) continue;

                int zeroCount = 0;

                for(int i = 0; i < 4; i++) {
                    int x = width + dx[i];
                    int y = height + dy[i];

                    if(x < 0 || y < 0 || x >= resultArr[0].length || y >= resultArr.length) continue;
                    if(resultArr[y][x] <= 0) zeroCount++;
                }

                if(zeroCount == 0) continue;

                checkList.add(new int[]{height, width, zeroCount});
            }
        }

        return checkList;
    }

    private static void changeStatus(List<int[]> checkList) {
        for(int[] arr : checkList) {
            int height = arr[0];
            int width = arr[1];
            int weight = arr[2];

            resultArr[height][width] -= weight;
        }
    }

    private static boolean checkValidation() {
        boolean[][] visitArr = new boolean[resultArr.length][resultArr[0].length];
        int iceCount = 0;

        for(int height = 0; height < resultArr.length; height++) {
            for(int width = 0; width < resultArr[0].length; width++) {
                if(resultArr[height][width] <= 0) continue;
                if(visitArr[height][width]) continue;

                bfs(visitArr, height, width);
                iceCount++;

                if(iceCount >= 2) return true;
            }
        }

        return false;
    }

    private static void bfs(boolean[][] visitArr, int height, int width) {
        Queue<int[]> resultQueue = new LinkedList<>();
        resultQueue.add(new int[]{height, width});
        visitArr[height][width] = true;

        while(!resultQueue.isEmpty()) {
            int[] indexArr = resultQueue.poll();

            int y = indexArr[0];
            int x = indexArr[1];

            for(int i = 0; i < 4; i++) {
                int xValue = x + dx[i];
                int yValue = y + dy[i];

                if(xValue < 0 || yValue < 0 || xValue >= resultArr[0].length || yValue >= resultArr.length) continue;
                if(visitArr[yValue][xValue]) continue;
                if(resultArr[yValue][xValue] <= 0) continue;

                visitArr[yValue][xValue] = true;
                resultQueue.add(new int[]{yValue, xValue});
            }
        }
    }

    private static int getYear() {
        for(int year = 1; year <= 10000; year++) {
            List<int[]> status = checkStatus();
            changeStatus(status);

            if(checkValidation()) return year;
        }

        return 0;
    }
}