import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class RobotCleaner {
    private static int[][] resultArr;
    private static int[] dx = {0, 1, 0, -1};
    private static int[] dy = {-1, 0, 1, 0};
    private static int cleanTime = 1;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int height = Integer.parseInt(st.nextToken());
        int width = Integer.parseInt(st.nextToken());

        initResultArr(height, width);

        st = new StringTokenizer(br.readLine(), " ");

        int y = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());
        int direction = Integer.parseInt(st.nextToken());

        for(int i = 0; i < height; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            mkResultArr(st, i);
        }

        findTime(y, x, direction);

        System.out.println(cleanTime);
    }

    private static void initResultArr(int height, int width) {
        resultArr = new int[height][width];
    }

    private static void mkResultArr(StringTokenizer st, int height) {
        int numValue = 0;

        for(;st.hasMoreTokens();) {
            resultArr[height][numValue++] = Integer.parseInt(st.nextToken());
        }
    }

    private static void findTime(int height, int width, int direction) {
        resultArr[height][width] = -1;

        for(int i = 0; i < 4; i++) {
            direction = (direction + 3) % 4;
            int xValue = width + dx[direction];
            int yValue = height + dy[direction];

            if(xValue >= 0 && yValue >= 0 && xValue < resultArr[0].length && yValue < resultArr.length &&
                    resultArr[yValue][xValue] == 0) {
                cleanTime++;
                findTime(yValue, xValue, direction);
                return;
            }
        }

        int back = (direction + 2) % 4;
        int x = width + dx[back];
        int y = height + dy[back];

        if(x >= 0 && y >= 0 && x < resultArr[0].length && y < resultArr.length && resultArr[y][x] != 1) {
            findTime(y, x, direction);
        }
    }
}