import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Cheese {
    private static int[][] resultArr;
    private static boolean[][] visit;
    private static final int[] dx = {1, -1, 0, 0};
    private static final int[] dy = {0, 0, 1, -1};
    private static int cheeseCount = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int resultTime = 0;

        String[] infoArr = br.readLine().split(" ");

        int height = Integer.parseInt(infoArr[0]);
        int width = Integer.parseInt(infoArr[1]);

        initArr(height, width);

        for(int i = 0; i < height; i++) {
            infoArr = br.readLine().split(" ");
            mkResultArr(infoArr, i);
        }

        while(cheeseCount != 0) {
            setAirCondition(0, 0);
            visit = new boolean[height][width];

            for(int i = 0; i < resultArr.length; i++) {
                for(int j = 0; j < resultArr[i].length; j++) {
                    int value = resultArr[i][j];

                    if(value == 1 && !visit[i][j]) {
                        changeCheeseStatus(j, i);
                    }
                }
            }

            visit = new boolean[height][width];

            for(int i = 0; i < resultArr.length; i++) {
                for(int j = 0; j < resultArr[0].length; j++) {
                    if(resultArr[i][j] == -1) {
                        resultArr[i][j] = 2;
                    }
                }
            }

            resultTime++;
        }

        System.out.println(resultTime);
    }

    private static void initArr(int height, int width) {
        resultArr = new int[height][width];
        visit = new boolean[height][width];
    }

    private static void mkResultArr(String[] infoArr, int height) {
        for(int i = 0; i < infoArr.length; i++) {
            int value = Integer.parseInt(infoArr[i]);

            resultArr[height][i] = value;

            if(value == 1) cheeseCount++;
        }
    }

    private static void setAirCondition(int x, int y) {
        if(visit[y][x]) return;

        visit[y][x] = true;
        resultArr[y][x] = 2;

        for(int i = 0; i < 4; i++) {
            int xValue = x + dx[i];
            int yValue = y + dy[i];

            if(xValue < 0 || yValue < 0 || xValue >= resultArr[0].length || yValue >= resultArr.length) continue;
            if(resultArr[yValue][xValue] == 1) continue;

            setAirCondition(xValue, yValue);
        }
    }

    private static void changeCheeseStatus(int x, int y) {
        if(visit[y][x]) return;

        visit[y][x] = true;

        if(resultArr[y][x] == 1 && checkValidation(x, y)) {
            cheeseCount--;
            resultArr[y][x] = -1;
        }

        for(int i = 0; i < 4; i++) {
            int xValue = x + dx[i];
            int yValue = y + dy[i];

            if(xValue < 0 || yValue < 0 || xValue >= resultArr[0].length || yValue >= resultArr.length) continue;
            if(resultArr[yValue][xValue] == 0) continue;

            changeCheeseStatus(xValue, yValue);
        }
    }

    private static boolean checkValidation(int x, int y) {
        int airCount = 0;

        for(int i = 0; i < 4; i++) {
            int xValue = x + dx[i];
            int yValue = y + dy[i];

            if(xValue < 0 || yValue < 0 || xValue >= resultArr[0].length || yValue >= resultArr.length) continue;
            if(resultArr[yValue][xValue] == 2) airCount++;
        }

        return airCount >= 2;
    }
}
