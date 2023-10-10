import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ClimbNumber {
    public static int[][] resultArr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int length = Integer.parseInt(br.readLine());

        initResultArr(length);
        mkResultArr();

        System.out.println(resultArr[0][length]);
    }

    public static void mkResultArr() {
        for(int i = 1; i < resultArr[0].length; i++) {
            for(int j = 8; j >= 0; j--) {
                resultArr[j][i] = (resultArr[j+1][i] + resultArr[j][i-1]) % 10007;
            }
        }
    }

    public static void initResultArr(int length) {
        resultArr = new int[10][length + 1];

        for(int i = 0; i < 10; i++) {
            resultArr[i][0] = 1;
        }

        for(int i = 0; i <= length; i++) {
            resultArr[9][i] = 1;
        }
    }
}