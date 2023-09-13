import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SumOfSubSet {
    public static int[] resultArr;
    public static int count = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] init = br.readLine().split(" ");
        String[] values = br.readLine().split(" ");

        int length = Integer.parseInt(init[0]);
        int target = Integer.parseInt(init[1]);

        initResultArr(length);
        mkResultArr(values);
        getCount(0, 0, target);

        printResult(target);
    }

    public static void printResult(int target) {
        if(target == 0) System.out.println(count - 1);
        else System.out.println(count);
    }

    public static void getCount(int index, int sum, int target) {
        if(index == resultArr.length) {
            if(sum == target) count++;
            return;
        }

        int value = resultArr[index];

        getCount(index+1, sum + value, target);
        getCount(index+1, sum, target);
    }

    public static void mkResultArr(String[] values) {
        for(int i = 0; i < values.length; i++) {
            resultArr[i] = Integer.parseInt(values[i]);
        }
    }

    public static void initResultArr(int length) {
        resultArr = new int[length];
    }
}

/**
 * 문제
 * N개의 정수로 이루어진 수열이 있을 때, 크기가 양수인 부분수열 중에서 그 수열의 원소를 다 더한 값이 S가 되는 경우의 수를 구하는 프로그램을 작성하시오.
 *
 * 입력
 * 첫째 줄에 정수의 개수를 나타내는 N과 정수 S가 주어진다. (1 ≤ N ≤ 20, |S| ≤ 1,000,000) 둘째 줄에 N개의 정수가 빈 칸을 사이에 두고 주어진다. 주어지는 정수의 절댓값은 100,000을 넘지 않는다.
 *
 * 출력
 * 첫째 줄에 합이 S가 되는 부분수열의 개수를 출력한다.
 *
 * 예제 입력 1
 * 5 0
 * -7 -3 -2 5 8
 * 예제 출력 1
 * 1
 */
