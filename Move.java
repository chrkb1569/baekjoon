import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Move {
    public static int[][] resultArr;
    public static int[][] dpArr;
    public static int[] dx = {0, -1, -1};
    public static int[] dy = {-1, 0, -1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] infoArr = br.readLine().split(" ");

        int height = Integer.parseInt(infoArr[0]);
        int width = Integer.parseInt(infoArr[1]);

        initArr(height, width);

        for(int i = 0; i < height; i++) {
            infoArr = br.readLine().split(" ");
            mkResultArr(infoArr, i);
        }

        updateValues();

        System.out.println(Arrays.stream(dpArr[dpArr.length-1]).max().getAsInt());
    }

    public static void initArr(int height, int width) {
        resultArr = new int[height][width];
        dpArr = new int[height][width];
    }

    public static void mkResultArr(String[] infoArr, int height) {
        for(int i = 0; i < infoArr.length; i++) {
            resultArr[height][i] = Integer.parseInt(infoArr[i]);
        }
    }

    public static void updateValues() {
        for(int i = 0; i < resultArr.length; i++) {
            for(int j = 0; j < resultArr[i].length; j++) {
                int value = resultArr[i][j];
                int maxValue = 0;

                for(int m = 0; m < 3; m++) {
                    int xValue = j + dx[m];
                    int yValue = i + dy[m];

                    if(xValue < 0 || yValue < 0 || xValue >= resultArr[0].length || yValue >= resultArr.length) continue;

                    maxValue = Math.max(maxValue, dpArr[yValue][xValue]);
                }

                dpArr[i][j] = value + maxValue;
            }
        }
    }
}
