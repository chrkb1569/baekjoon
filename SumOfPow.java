import java.io.BufferedReader;
import java.io.InputStreamReader;

public class SumOfPow {
    public static int[] resultArr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        initResultArr(N);
        mkResultArr();

        System.out.println(resultArr[N]);
    }

    public static void mkResultArr() {
        for(int i = 1; i < resultArr.length; i++) {
            if(resultArr[i] != 0) continue;

            resultArr[i] = Integer.MAX_VALUE;

            for(int j = 1; j <= i/2; j++) {
                resultArr[i] = Math.min(resultArr[i], resultArr[j] + resultArr[i-j]);
            }
        }
    }

    public static void initResultArr(int length) {
        resultArr = new int[length + 1];

        for(int i = 1; i <= Math.sqrt(length); i++) {
            int index = (int)Math.pow(i, 2);
            resultArr[index]++;
        }
    }
}
