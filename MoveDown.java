import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class MoveDown {
    public static int[][] resultArr;
    public static int[][] maxArr;
    public static int[][] minArr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        initArr(N);

        for(int i = 0; i < N; i++) {
            String[] infoArr = br.readLine().split(" ");
            mkResultArr(infoArr, i);
        }

        mkMaxArr();
        mkMinArr();

        System.out.println(getMaxValue() + " " + getMinValue());
    }

    public static void initArr(int N) {
        resultArr = new int[N][3];
        maxArr = new int[N][3];
        minArr = new int[N][3];
    }

    public static void mkResultArr(String[] infoArr, int height) {
        for(int i = 0; i < infoArr.length; i++) {
            resultArr[height][i] = Integer.parseInt(infoArr[i]);
        }
    }

    public static void mkMaxArr() {
        for(int i = 0; i < 3; i++) {
            maxArr[0][i] = resultArr[0][i];
        }

        for(int i = 0; i < resultArr.length - 1; i++) {
            for(int j = 0; j < 3; j++) {
                int value = maxArr[i][j];

                if(j == 0) {
                    maxArr[i+1][j] = Math.max(maxArr[i+1][j], resultArr[i+1][j] + value);
                    maxArr[i+1][j+1] = Math.max(maxArr[i+1][j+1], resultArr[i+1][j+1] + value);
                    continue;
                }

                if(j == 1) {
                    maxArr[i+1][j-1] = Math.max(maxArr[i+1][j-1], resultArr[i+1][j-1] + value);
                    maxArr[i+1][j] = Math.max(maxArr[i+1][j], resultArr[i+1][j] + value);
                    maxArr[i+1][j+1] = Math.max(maxArr[i+1][j+1], resultArr[i+1][j+1] + value);
                    continue;
                }

                maxArr[i+1][j-1] = Math.max(maxArr[i+1][j-1], resultArr[i+1][j-1] + value);
                maxArr[i+1][j] = Math.max(maxArr[i+1][j], resultArr[i+1][j] + value);
            }
        }
    }

    public static void mkMinArr() {
        for(int i = 0; i < minArr.length; i++) {
            Arrays.fill(minArr[i], Integer.MAX_VALUE);
        }

        for(int i = 0; i < 3; i++) {
            minArr[0][i] = resultArr[0][i];
        }

        for(int i = 0; i < resultArr.length - 1; i++) {
            for(int j = 0; j < 3; j++) {
                int value = minArr[i][j];

                if(j == 0) {
                    minArr[i+1][j] = Math.min(minArr[i+1][j], resultArr[i+1][j] + value);
                    minArr[i+1][j+1] = Math.min(minArr[i+1][j+1], resultArr[i+1][j+1] + value);
                    continue;
                }

                if(j == 1) {
                    minArr[i+1][j-1] = Math.min(minArr[i+1][j-1], resultArr[i+1][j-1] + value);
                    minArr[i+1][j] = Math.min(minArr[i+1][j], resultArr[i+1][j] + value);
                    minArr[i+1][j+1] = Math.min(minArr[i+1][j+1], resultArr[i+1][j+1] + value);
                    continue;
                }

                minArr[i+1][j-1] = Math.min(minArr[i+1][j-1], resultArr[i+1][j-1] + value);
                minArr[i+1][j] = Math.min(minArr[i+1][j], resultArr[i+1][j] + value);
            }
        }
    }

    public static int getMaxValue() {
        return Arrays.stream(maxArr[maxArr.length - 1]).max().getAsInt();
    }

    public static  int getMinValue() {
        return Arrays.stream(minArr[minArr.length - 1]).min().getAsInt();
    }
}
