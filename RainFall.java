import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class RainFall {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int height = Integer.parseInt(st.nextToken());
        int width = Integer.parseInt(st.nextToken());

        int[][] resultArr = getResultArr(height, width);

        st = new StringTokenizer(br.readLine(), " ");
        initResultArr(resultArr, st);

        System.out.println(calculateResult(resultArr));
    }

    public static int calculateResult(int[][] resultArr) {
        int result = 0;

        for(int i = 0; i < resultArr.length; i++) {
            result += getZeroCount(resultArr[i]);
        }

        return result;
    }

    public static int getZeroCount(int[] resultArr) {
        int zeroCount = 0;
        int count = 0;
        boolean able = false;

        if(resultArr[0] == 1) {
            able = true;
        }

        for(int i = 1; i < resultArr.length; i++) {
            int value = resultArr[i];

            if(value == 0 && resultArr[i-1] == 1) {
                able = true;
                count++;
                continue;
            }

            if(value == 1 && resultArr[i-1] == 0) {
                able = false;
                zeroCount += count;
                count = 0;
                continue;
            }

            if(value == 0 && able) {
                count++;
            }
        }

        return zeroCount;
    }

    public static void initResultArr(int[][] resultArr, StringTokenizer st) {
        int numValue = 0;

        for(;st.hasMoreTokens();) {
            int value = Integer.parseInt(st.nextToken());

            for(int i = 0; i < value; i++) {
                resultArr[i][numValue] = 1;
            }
            numValue++;
        }
    }

    public static int[][] getResultArr(int height, int width) {
        return new int[height][width];
    }
}
