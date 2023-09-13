import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PinaryNumber {
    public static long[] resultArr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int number = Integer.parseInt(br.readLine());

        initResultArr(number);
        mkResultArr();

        System.out.println(resultArr[number]);
    }

    public static void mkResultArr() {
        for(int i = 3; i < resultArr.length; i++) {
            resultArr[i] = resultArr[i-1] + resultArr[i-2];
        }
    }

    public static void initResultArr(int width) {
        resultArr = new long[width + 1];

        resultArr[1] = 1;

        if(width < 2) return;

        resultArr[2] = 1;
    }
}
