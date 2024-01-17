import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Binomial {
    private static int[][] resultArr;
    private static int INIT_VALUE = 10_008;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        initResultArr(N);
        int combinationResult = getCombination(N, K);

        System.out.println(combinationResult);
    }

    private static void initResultArr(int length) {
        resultArr = new int[length+1][length+1];

        for(int i = 0; i < resultArr.length; i++) {
            Arrays.fill(resultArr[i], INIT_VALUE);
        }
    }

    private static int getCombination(int N, int K) {
        if(resultArr[N][K] != INIT_VALUE) return resultArr[N][K];
        if(resultArr[N][N-K] != INIT_VALUE) return resultArr[N][N-K];

        if(K == 0 || N == K) return 1;
        if(K == 1) return N;

        resultArr[N][K] = (getCombination(N-1, K-1) + getCombination(N-1, K)) % 10_007;
        resultArr[N][N-K] = resultArr[N][K];

        return resultArr[N][K];
    }
}
