import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Censor {
    private static int[] resultArr;
    private static int[] diffArr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int K = Integer.parseInt(br.readLine());

        if(N <= K) {
            System.out.println(0);
            return;
        }

        initResultArr(N);

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        mkResultArr(st);
        Arrays.sort(resultArr);

        mkDiffArr();
        Arrays.sort(diffArr);

        int result = 0;

        for(int index = 0; index < N - K; index++) {
            result += diffArr[index];
        }

        System.out.println(result);
    }

    private static void initResultArr(int length) {
        resultArr = new int[length];
    }

    private static void mkResultArr(StringTokenizer st) {
        int width = 0;

        for(;st.hasMoreTokens();) {
            resultArr[width++] = Integer.parseInt(st.nextToken());
        }
    }

    private static void mkDiffArr() {
        diffArr = new int[resultArr.length-1];

        for(int index = 0; index < diffArr.length; index++) {
            diffArr[index] = resultArr[index + 1] - resultArr[index];
        }
    }
}