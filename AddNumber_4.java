import java.io.BufferedReader;
import java.io.InputStreamReader;

public class AddNumber_4 {
    private static long[][] resultArr = new long[10_001][3];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuffer sb = new StringBuffer();

        int T = Integer.parseInt(br.readLine()); // testCase

        mkResultArr();

        for(int testCase = 0; testCase < T; testCase++) {
            int n = Integer.parseInt(br.readLine());

            sb.append(getNthNumber(n)).append("\n");
        }

        System.out.println(sb);
    }

    private static void mkResultArr() {
        resultArr[1][0] = 1;
        resultArr[2][1] = 1;
        resultArr[3][2] = 1;

        for(int index = 1; index < resultArr.length; index++) {
            if(resultArr[index][0] != 0) {
                if(index + 1 < resultArr.length) resultArr[index + 1][0] += resultArr[index][0];
                if(index + 2 < resultArr.length) resultArr[index + 2][1] += resultArr[index][0];
                if(index + 3 < resultArr.length) resultArr[index + 3][2] += resultArr[index][0];
            }

            if(resultArr[index][1] != 0) {
                if(index + 2 < resultArr.length) resultArr[index + 2][1] += resultArr[index][1];
                if(index + 3 < resultArr.length) resultArr[index + 3][2] += resultArr[index][1];
            }

            if(resultArr[index][2] != 0) {
                if(index + 3 < resultArr.length) resultArr[index + 3][2] += resultArr[index][2];
            }
        }
    }

    private static long getNthNumber(int n) {
        return resultArr[n][0] + resultArr[n][1] + resultArr[n][2];
    }
}
