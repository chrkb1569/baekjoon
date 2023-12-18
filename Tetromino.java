import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Tetromino {
    private static int[][] resultArr;
    private static int maxArea = Integer.MIN_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int height = Integer.parseInt(st.nextToken());
        int width = Integer.parseInt(st.nextToken());

        initResultArr(height, width);

        for(int i = 0; i < height; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            mkResultArr(st, i);
        }

        for(int i = 0; i < resultArr.length; i++) {
            for(int j = 0; j < resultArr[0].length; j++) {
                int area = getMaxArea(j, i);

                maxArea = Math.max(maxArea, area);
            }
        }

        System.out.println(maxArea);
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

    private static int getMaxArea(int x, int y) {
        int straightBlockArea = getStraightBlockArea(x, y);
        int sBlockArea = getSBlockArea(x, y);
        int squareBlockArea = getSquareBlockArea(x, y);
        int wooBlockArea = getWooBlockArea(x, y);
        int lBlockArea = getLBlockArea(x, y);

        return Math.max(Math.max(Math.max(straightBlockArea, sBlockArea), Math.max(squareBlockArea, wooBlockArea)), lBlockArea);
    }

    private static int getStraightBlockArea(int x, int y) {
        int widthArea = 0;
        int heightArea = 0;

        for(int i = 0; i < 4; i++) {
            if(x + i < resultArr[0].length) widthArea += resultArr[y][x + i];
            if(y + i < resultArr.length) heightArea += resultArr[y + i][x];
        }

        return Math.max(widthArea, heightArea);
    }

    private static int getSquareBlockArea(int x, int y) {
        if(x + 1 >= resultArr[0].length || y + 1 >= resultArr.length) return 0;

        int current = resultArr[y][x];
        int right = resultArr[y][x+1];
        int down = resultArr[y+1][x];
        int cross = resultArr[y+1][x+1];

        return current + right + down + cross;
    }

    private static int getLBlockArea(int x, int y) {
        int northArea = northAreaOfLBlock(x, y);
        int eastArea = eastAreaOfLBlock(x, y);
        int southArea = southAreaOfLBlock(x, y);
        int westArea = westAreaOfLBlock(x, y);

        return Math.max(Math.max(northArea, eastArea), Math.max(southArea, westArea));
    }

    private static int getSBlockArea(int x, int y) {
        int northArea = northAreaOfSBlock(x, y);
        int eastArea = eastAreaOfSBlock(x, y);
        int southArea = southAreaOfSBlock(x, y);
        int westArea = westAreaOfSBlock(x, y);

        return Math.max(Math.max(northArea, eastArea), Math.max(southArea, westArea));
    }

    private static int getWooBlockArea(int x, int y) {
        int northArea = northAreaOfWooBlock(x, y);
        int eastArea = eastAreaOfWooBlock(x, y);
        int southArea = southAreaOfWooBlock(x, y);
        int westArea = westAreaOfWooBlock(x, y);

        return Math.max(Math.max(northArea, eastArea), Math.max(southArea, westArea));
    }

    private static int eastAreaOfLBlock(int x, int y) {
        if(x + 2 >= resultArr[0].length) return 0;

        int area = 0;

        for(int i = 0; i < 3; i++) {
            area += resultArr[y][x + i];
        }

        if(y + 1 >= resultArr.length) return area + resultArr[y-1][x+2];
        if(y - 1 < 0) return area + resultArr[y+1][x+2];
        return Math.max(area + resultArr[y+1][x+2], area + resultArr[y-1][x+2]);
    }

    private static int westAreaOfLBlock(int x, int y) {
        if(x - 2 < 0) return 0;

        int area = 0;

        for(int i = 0; i < 3; i++) {
            area += resultArr[y][x - i];
        }

        if(y + 1 >= resultArr.length) return area + resultArr[y-1][x-2];
        if(y - 1 < 0) return area + resultArr[y+1][x-2];
        return Math.max(area + resultArr[y-1][x-2], area + resultArr[y+1][x-2]);
    }

    private static int southAreaOfLBlock(int x, int y) {
        if(y + 2 >= resultArr.length) return 0;

        int area = 0;

        for(int i = 0; i < 3; i++) {
            area += resultArr[y + i][x];
        }

        if(x-1 < 0) return area + resultArr[y+2][x+1];
        if(x+1 >= resultArr[0].length) return area + resultArr[y+2][x-1];
        return Math.max(area + resultArr[y+2][x+1], area + resultArr[y+2][x-1]);
    }

    private static int northAreaOfLBlock(int x, int y) {
        if(y - 2 < 0) return 0;

        int area = 0;

        for(int i = 0; i < 3; i++) {
            area += resultArr[y-i][x];
        }

        if(x-1 < 0) return area + resultArr[y-2][x+1];
        if(x+1 >= resultArr[0].length) return area + resultArr[y-2][x-1];
        return Math.max(area + resultArr[y-2][x+1], area + resultArr[y-2][x-1]);
    }

    private static int northAreaOfSBlock(int x, int y) {
        if(y - 2 < 0) return 0;

        int area = 0;

        for(int i = 0; i < 2; i++) {
            area += resultArr[y-i][x];
        }

        if(x - 1 < 0) return area + resultArr[y-1][x+1] + resultArr[y-2][x+1];
        if(x + 1 >= resultArr[0].length) return area + resultArr[y-1][x-1] + resultArr[y-2][x-1];
        return Math.max(area + resultArr[y-1][x+1] + resultArr[y-2][x+1], area + resultArr[y-1][x-1] + resultArr[y-2][x-1]);
    }

    private static int eastAreaOfSBlock(int x, int y) {
        if(x + 2 >= resultArr[0].length) return 0;

        int area = 0;

        for(int i = 0; i < 2; i++) {
            area += resultArr[y][x+i];
        }

        if(y - 1 < 0) return area + resultArr[y+1][x+1] + resultArr[y+1][x+2];
        if(y + 1 >= resultArr.length) return area + resultArr[y-1][x+1] + resultArr[y-1][x+2];
        return Math.max(area + resultArr[y+1][x+1] + resultArr[y+1][x+2], area + resultArr[y-1][x+1] + resultArr[y-1][x+2]);
    }

    private static int southAreaOfSBlock(int x, int y) {
        if(y + 2 >= resultArr.length) return 0;

        int area = 0;

        for(int i = 0; i < 2; i++) {
            area += resultArr[y+i][x];
        }

        if(x - 1 < 0) return area + resultArr[y+1][x+1] + resultArr[y+2][x+1];
        if(x + 1 >= resultArr[0].length) return area + resultArr[y+1][x-1] + resultArr[y+2][x-1];
        return Math.max(area + resultArr[y+1][x+1] + resultArr[y+2][x+1], area + resultArr[y+1][x-1] + resultArr[y+2][x-1]);
    }

    private static int westAreaOfSBlock(int x, int y) {
        if(x - 2 < 0) return 0;

        int area = 0;

        for(int i = 0; i < 2; i++) {
            area += resultArr[y][x-i];
        }

        if(y - 1 < 0) return area + resultArr[y+1][x-1] + resultArr[y+1][x-2];
        if(y + 1 >= resultArr.length) return area + resultArr[y-1][x-1] + resultArr[y-1][x-2];
        return Math.max(area + resultArr[y+1][x-1] + resultArr[y+1][x-2], area + resultArr[y-1][x-1] + resultArr[y-1][x-2]);
    }

    private static int northAreaOfWooBlock(int x, int y) {
        if(x - 1 < 0 || x + 1 >= resultArr[0].length || y - 1 < 0) return 0;
        return resultArr[y][x] + resultArr[y-1][x-1] + resultArr[y-1][x] + resultArr[y-1][x+1];
    }

    private static int eastAreaOfWooBlock(int x, int y) {
        if(y-1 < 0 || y+1 >= resultArr.length || x+1 >= resultArr[0].length) return 0;
        return resultArr[y][x] + resultArr[y][x+1] + resultArr[y-1][x+1] + resultArr[y+1][x+1];
    }

    private static int southAreaOfWooBlock(int x, int y) {
        if(y+1 >= resultArr.length || x-1 < 0 || x+1 >= resultArr[0].length) return 0;
        return resultArr[y][x] + resultArr[y+1][x] + resultArr[y+1][x-1] + resultArr[y+1][x+1];
    }

    private static int westAreaOfWooBlock(int x, int y) {
        if(x-1 < 0 || y-1 < 0 || y+1 >= resultArr.length) return 0;
        return resultArr[y][x] + resultArr[y][x-1] + resultArr[y+1][x-1] + resultArr[y-1][x-1];
    }
}
