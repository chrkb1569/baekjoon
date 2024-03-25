import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class FourSquares {
    private static int[] resultArr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        initResultArr(N);
        mkResultArr();

        System.out.println(resultArr[N]);
    }

    private static void initResultArr(int length) {
        resultArr = new int[length + 1];

        Arrays.fill(resultArr, Integer.MAX_VALUE);

        resultArr[0] = 0;
    }

    private static void mkResultArr() {
        int maxValue = (int)Math.sqrt(resultArr.length);

        for(int index = 0; index < resultArr.length; index++) {
            for(int value = 1; value <= maxValue; value++) {
                if(index + value * value >= resultArr.length) break;
                resultArr[index + value * value] = Math.min(resultArr[index + value * value], resultArr[index] + 1);
            }
        }
    }
}
