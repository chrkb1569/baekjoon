import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Make_Bridge {
    public static long[][] resultArr = new long[30][30];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int testCase = Integer.parseInt(br.readLine());
        mkResultArr();

        for(int i = 0; i < testCase; i++) {
            String[] arr = br.readLine().split(" ");
            int N = Integer.parseInt(arr[0]);
            int M = Integer.parseInt(arr[1]);
            sb.append(resultArr[M][N]).append("\n");
        }

        System.out.println(sb);
    }

    public static void mkResultArr() {
        for(int i = 1; i < 30; i++) {
            resultArr[i][i] = 1;
            resultArr[i][0] = 1;
        }

        for(int i = 1; i < 30; i++) {
            for(int j = 2; j < 30; j++) {
                resultArr[j][i] = resultArr[j-1][i] +resultArr[j-1][i-1];
            }
        }
    }
}