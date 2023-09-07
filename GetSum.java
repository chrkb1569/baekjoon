import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class GetSum {
    public static int[] resultArr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int length = Integer.parseInt(br.readLine());
        String[] valueArr = br.readLine().split(" ");

        initResultArr(length);
        mkResultArr(valueArr);

        System.out.println(getMaxValue());
    }

    public static int getMaxValue() {
        int value = resultArr[0];
        int maxValue = Math.max(Integer.MIN_VALUE, value);

        for(int i = 1; i < resultArr.length; i++) {
            value = Math.max(resultArr[i], value + resultArr[i]);
            maxValue = Math.max(value, maxValue);
        }

        return maxValue;
    }

    public static void mkResultArr(String[] arr) {
        for(int i = 0; i < arr.length; i++) {
            resultArr[i] = Integer.parseInt(arr[i]);
        }
    }

    public static void initResultArr(int length) {
        resultArr = new int[length];
    }
}

/**
 * 10
 * 10 -4 3 1 5 6 -35 12 21 -1
 * 33
 *
 * 10
 * 2 1 -4 3 4 -4 6 5 -5 1
 * 14
 *
 * 5
 * -1 -2 -3 -4 -5
 * -1
 *
 * 첫째 줄에 정수 n(1 ≤ n ≤ 100,000)이 주어지고 둘째 줄에는 n개의 정수로 이루어진 수열이 주어진다.
 * 수는 -1,000보다 크거나 같고, 1,000보다 작거나 같은 정수이다.
 */