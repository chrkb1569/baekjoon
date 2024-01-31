import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class LIS {
    private static int[] valueArr;
    private static int[] resultArr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        initArr(N);

        StringTokenizer st = new StringTokenizer(br.readLine());

        mkValueArr(st);
        mkResultArr();

        int maxSum = Arrays.stream(resultArr).max().getAsInt();

        System.out.println(maxSum);
    }

    private static void initArr(int length) {
        valueArr = new int[length];
        resultArr = new int[length];
    }

    private static void mkValueArr(StringTokenizer st) {
        int index = 0;

        for(;st.hasMoreTokens();) {
            valueArr[index++] = Integer.parseInt(st.nextToken());
        }
    }

    private static void mkResultArr() {
        resultArr[0] = valueArr[0];

        for(int i = 1; i < valueArr.length; i++) {
            resultArr[i] = valueArr[i];

            for(int j = 0; j < i; j++) {
                if(valueArr[i] > valueArr[j]) {
                    resultArr[i] = Math.max(resultArr[i], resultArr[j] + valueArr[i]);
                }
            }
        }
    }
}