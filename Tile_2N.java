import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Tile_2N {
    public static int[] resultArr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        initResultArr(N);
        printResult(N);
    }

    public static void printResult(int N) {
        if(N == 1) {
            System.out.println(1);
            return;
        }

        if(N == 2) {
            System.out.println(3);
            return;
        }

        mkResultArr(N);
        System.out.println(resultArr[N]);
    }

    public static void mkResultArr(int N) {
        resultArr[1] = 1;
        resultArr[2] = 3;

        for(int i = 3; i <= N; i++) {
            resultArr[i] = (resultArr[i-1] + 2 * resultArr[i-2]) % 10007;
        }
    }

    public static void initResultArr(int length) {
        resultArr = new int[length + 1];
    }
}

/**
 * 2 -> 3
 * 8 -> 171
 * 12 -> 2731
 * 첫째 줄에 n이 주어진다. (1 ≤ n ≤ 1,000)
 */