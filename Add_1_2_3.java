import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Add_1_2_3 {
    private static long[] resultArr = new long[1_000_001];
    private static StringBuilder sb = new StringBuilder();
    private static final int DIVIDER = 1_000_000_009;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        mkResultArr();

        for(int i = 0; i < N; i++) {
            int index = Integer.parseInt(br.readLine());
            sb.append(resultArr[index]).append("\n");
        }

        System.out.println(sb);
    }

    private static void mkResultArr() {
        resultArr[1] = 1;
        resultArr[2] = 2;
        resultArr[3] = 4;

        for(int i = 4; i < resultArr.length; i++) {
            resultArr[i] = (resultArr[i-1] + resultArr[i-2] + resultArr[i-3]) % DIVIDER;
        }
    }
}
