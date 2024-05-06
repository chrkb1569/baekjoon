import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BABBA {
    private static int[][] resultArr; // 0 -> A_Count, 1 -> B_Count

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int K = Integer.parseInt(br.readLine());

        initResultArr(K);
        mkResultArr();

        System.out.println(resultArr[K][0] + " " + resultArr[K][1]);
    }

    private static void initResultArr(int length) {
        resultArr = new int[length + 1][2];
    }

    private static void mkResultArr() {
        resultArr[0][0] = 1;
        resultArr[0][1] = 0;

        for(int index = 1; index < resultArr.length; index++) {
            int aCount = resultArr[index-1][0];
            int bCount = resultArr[index-1][1];

            resultArr[index][0] += bCount; // A
            resultArr[index][1] += (aCount + bCount); // B
        }
    }
}
