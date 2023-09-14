import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BuyCard {
    public static int[] resultArr;
    public static int[] dpArr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int number = Integer.parseInt(br.readLine());
        String[] arr = br.readLine().split(" ");

        initArr(number);
        mkResultArr(arr);

        System.out.println(getDpResult(number));
    }

    public static int getDpResult(int number) {
        if(number <= 0) return 0;
        if(dpArr[number] != -1) return dpArr[number];

        int result = Integer.MIN_VALUE;

        for(int i = 1; i <= number; i++) {
            int value = resultArr[i];

            result = Math.max(result, value + getDpResult(number - i));
        }

        dpArr[number] = result;

        return dpArr[number];
    }

    public static void mkResultArr(String[] arr) {
        for(int i = 1; i < resultArr.length; i++) {
            resultArr[i] = Integer.parseInt(arr[i-1]);
        }
    }

    public static void initArr(int width) {
        resultArr = new int[width + 1];
        dpArr = new int[width + 1];

        for(int i = 1; i < dpArr.length; i++) {
            dpArr[i] = -1;
        }
    }
}

/**
 * 입력
 * 첫째 줄에 민규가 구매하려고 하는 카드의 개수 N이 주어진다. (1 ≤ N ≤ 1,000)
 * 둘째 줄에는 Pi가 P1부터 PN까지 순서대로 주어진다. (1 ≤ Pi ≤ 10,000)
 *
 * 출력
 * 첫째 줄에 민규가 카드 N개를 갖기 위해 지불해야 하는 금액의 최댓값을 출력한다.
 *
 * 예제 입력 1
 * 4
 * 1 5 6 7
 *
 * 예제 출력 1
 * 10
 *
 * 예제 입력 2
 * 5
 * 10 9 8 7 6
 *
 * 예제 출력 2
 * 50
 *
 * 예제 입력 3
 * 10
 * 1 1 2 3 5 8 13 21 34 55
 *
 * 예제 출력 3
 * 55
 *
 * 예제 입력 4
 * 10
 * 5 10 11 12 13 30 35 40 45 47
 *
 * 예제 출력 4
 * 50
 *
 * 예제 입력 5
 * 4
 * 5 2 8 10
 *
 * 예제 출력 5
 * 20
 *
 * 예제 입력 6
 * 4
 * 3 5 15 16
 *
 * 예제 출력 6
 * 18
 */

