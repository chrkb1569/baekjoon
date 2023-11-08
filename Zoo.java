import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Zoo {
    public static int[] resultArr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        initResultArr(N);
        mkResultArr();

        System.out.println(resultArr[N]);
    }

    public static void initResultArr(int length) {
        resultArr = new int[length + 1];
    }

    public static void mkResultArr() {
        resultArr[0] = 1;

        if(resultArr.length == 1) return;

        resultArr[1] = 3;

        if(resultArr.length == 2) return;

        for(int i = 2; i < resultArr.length; i++) {
            resultArr[i] = (resultArr[i-1] * 2 + resultArr[i-2]) % 9901;
        }
    }
}
