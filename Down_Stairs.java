import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Down_Stairs {

    public static int[][] resultArr;
    public static int[][] pathArr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] arr = br.readLine().split(" ");

        int height = Integer.parseInt(arr[0]);
        int width = Integer.parseInt(arr[1]);

        initArr(height, width);

        for(int i = 0; i < height; i++) {
            String[] values = br.readLine().split(" ");
            mkArr(values, i);
        }

        System.out.println(getDpResult(1, 1));
    }

    public static int getDpResult(int x, int y) {
        if(x == resultArr[0].length-1 && y == resultArr.length-1) return 1;

        int pathValue = pathArr[y][x];

        if(pathValue != -1) return pathValue;

        pathArr[y][x] = 0;
        int heightValue = resultArr[y][x];

        if(y < resultArr.length - 1 && heightValue > resultArr[y+1][x]) pathArr[y][x] += getDpResult(x, y+1);
        if(y > 0 && heightValue > resultArr[y-1][x]) pathArr[y][x] += getDpResult(x, y-1);
        if(x < resultArr[0].length - 1 && heightValue > resultArr[y][x+1]) pathArr[y][x] += getDpResult(x+1, y);
        if(x > 0 && heightValue > resultArr[y][x-1]) pathArr[y][x] += getDpResult(x-1, y);

        return pathArr[y][x];
    }

    public static void initArr(int height, int width) {
        resultArr = new int[height + 1][width + 1];
        pathArr = new int[height + 1][width + 1];
    }

    public static void mkArr(String[] arr, int height) {
        for(int i = 0; i < arr.length; i++) {
            resultArr[height+1][i+1] = Integer.parseInt(arr[i]);
        }

        for(int i = 0; i < resultArr.length; i++) {
            for(int j = 0; j < resultArr[0].length; j++) {
                pathArr[i][j] = -1;
            }
        }
    }
}
