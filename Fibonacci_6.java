import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Fibonacci_6 {
    private static final long[][] initialArr = {{1, 1}, {1, 0}};
    private static final int DIVIDER = 1_000_000_007;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        long N = Long.parseLong(br.readLine());

        if(N == 0 || N == 1) {
            System.out.println(N);
            return;
        }

        long[][] A = {{1, 1}, {1, 0}};

        System.out.println(getFibonacciValue(A, N - 1)[0][0]);
    }

    private static long[][] getFibonacciValue(long[][] arr, long exp) {
        if(exp == 1 || exp == 0) return arr;

        long[][] resultArr = getFibonacciValue(arr, exp / 2);

        resultArr = multiply(resultArr, resultArr);

        if(exp % 2 == 1L) {
            resultArr = multiply(resultArr, initialArr);
        }

        return resultArr;
    }

    private static long[][] multiply(long[][] o1, long[][] o2) {
        long[][] resultArr = new long[2][2];

        resultArr[0][0] = ((o1[0][0] * o2[0][0]) + (o1[0][1] * o2[1][0])) % DIVIDER;
        resultArr[0][1] = ((o1[0][0] * o2[0][1]) + (o1[0][1] * o2[1][1])) % DIVIDER;
        resultArr[1][0] = ((o1[1][0] * o2[0][0]) + (o1[1][1] * o2[1][0])) % DIVIDER;
        resultArr[1][1] = ((o1[1][0] * o2[0][1]) + (o1[1][1] * o2[1][1])) % DIVIDER;

        return resultArr;
    }
}