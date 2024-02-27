import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Change {
    private static int[] resultArr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        initResultArr(N);
        mkResultArr();

        System.out.println(resultArr[N]);
    }

    private static void initResultArr(int length) {
        resultArr = new int[length + 1];

        Arrays.fill(resultArr, Integer.MAX_VALUE);
    }

    private static void mkResultArr() {
        if(resultArr.length <= 2) {
            Arrays.fill(resultArr, -1);
            return;
        }

        resultArr[2] = 1;

        if(resultArr.length >= 6) resultArr[5] = 1;

        for(int index = 2; index < resultArr.length; index++) {
            if(resultArr[index] == Integer.MAX_VALUE) {
                resultArr[index] = -1;
                continue;
            }

            if(index + 2 < resultArr.length) resultArr[index + 2] = Math.min(resultArr[index + 2], resultArr[index] + 1);
            if(index + 5 < resultArr.length) resultArr[index + 5] = Math.min(resultArr[index + 5], resultArr[index] + 1);
        }
    }
}
