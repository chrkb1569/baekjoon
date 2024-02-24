import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class GetPartialSum_4 {
    private static int[] resultArr;
    private static int[] sumArr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        initArr(N);

        st = new StringTokenizer(br.readLine(), " ");

        mkResultArr(st);
        mkSumArr();

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");

            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            sb.append(getPartialSum(start, end)).append("\n");
        }

        System.out.println(sb);
    }

    private static void initArr(int length) {
        resultArr = new int[length];
        sumArr = new int[length + 1];
    }

    private static void mkResultArr(StringTokenizer st) {
        int index = 0;

        for(;st.hasMoreTokens();) {
            resultArr[index++] = Integer.parseInt(st.nextToken());
        }
    }

    private static void mkSumArr() {
        sumArr[0] = 0;

        for(int index = 1; index < sumArr.length; index++) {
            sumArr[index] = sumArr[index-1] + resultArr[index-1];
        }
    }

    private static int getPartialSum(int start, int end) {
        return sumArr[end] - sumArr[start - 1];
    }
}
