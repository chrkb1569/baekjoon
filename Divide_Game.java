import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Divide_Game {
    private static int[] resultArr;
    private static int[] primeArr = new int[1_000_001];
    private static boolean[] visitArr = new boolean[1_000_001];
    private static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine(), " ");

        initResultArr(N);
        mkResultArr(st);
        mkPrimeArr();

        printResult();
    }

    private static void initResultArr(int length) {
        resultArr = new int[length];
    }

    private static void mkResultArr(StringTokenizer st) {
        int index = 0;

        for(;st.hasMoreTokens();) {
            int value = Integer.parseInt(st.nextToken());

            visitArr[value] = true;
            resultArr[index++] = value;
        }
    }

    private static void mkPrimeArr() {
        for(int index = 0; index < resultArr.length; index++) {
            int value = resultArr[index];

            for(int number = 2 * value; number < primeArr.length; number += value) {
                if(visitArr[number]) {
                    primeArr[value]++;
                    primeArr[number]--;
                }
            }
        }
    }

    private static void printResult() {
        for(int index : resultArr) {
            sb.append(primeArr[index]).append(" ");
        }

        System.out.println(sb);
    }
}
