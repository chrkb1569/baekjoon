import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Add_1_2_3_7 {

    private static int[][] resultArr = new int[1001][1001];
    private static final int DIVIDER = 1_000_000_009;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine()); // 테스트 케이스

        mkResultArr();

        for(int testCase = 0; testCase < T; testCase++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");

            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            sb.append(resultArr[N][M]);

            if(testCase != T - 1) sb.append("\n");
        }

        System.out.println(sb);
    }

    private static void mkResultArr() {
        resultArr[1][1] = 1;
        resultArr[2][1] = 1;
        resultArr[3][1] = 1;

        for(int width = 1; width < resultArr.length; width++) {
            for(int height = 1; height < resultArr.length; height++) {
                if(width + 1 >= resultArr.length) continue;

                int currentValue = resultArr[height][width];

                if(height + 1 < resultArr.length) resultArr[height + 1][width + 1] = (resultArr[height + 1][width + 1] + currentValue) % DIVIDER;
                if(height + 2 < resultArr.length) resultArr[height + 2][width + 1] = (resultArr[height + 2][width + 1] + currentValue) % DIVIDER;
                if(height + 3 < resultArr.length) resultArr[height + 3][width + 1] = (resultArr[height + 3][width + 1] + currentValue) % DIVIDER;
            }
        }
    }
}
