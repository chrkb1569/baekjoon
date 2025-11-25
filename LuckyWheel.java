import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class LuckyWheel {

    private static char[] resultArr;
    private static boolean flag = true;

    private final static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int N = Integer.parseInt(st.nextToken()); // numbers
        int K = Integer.parseInt(st.nextToken()); // testCase

        initResultArr(N);
        mkResultArr();

        int currentIndex = 0;

        for(int testCase = 0; testCase < K; testCase++) {
            st = new StringTokenizer(br.readLine(), " ");

            int index = Integer.parseInt(st.nextToken());
            char value = st.nextToken().charAt(0);

            currentIndex = (currentIndex - index + 100 * N) % N;

            if(resultArr[currentIndex] != '?' && resultArr[currentIndex] != value) {
                flag = false;
                break;
            }

            resultArr[currentIndex] = value;
        }

        printResult(currentIndex);
    }

    private static void initResultArr(int length) {
        resultArr = new char[length];
    }

    private static void mkResultArr() {
        Arrays.fill(resultArr, '?');
    }

    private static void printResult(int currentIndex) {
        if(!flag || !checkValidation()) {
            System.out.println("!");
            return;
        }

        int length = resultArr.length;

        for(int index = currentIndex; index < currentIndex + length; index++) {
            sb.append(resultArr[index % length]);
        }

        System.out.println(sb);
    }

    private static boolean checkValidation() {
        boolean[] visitArr = new boolean[26];

        for(char value : resultArr) {
            if(value == '?') continue;
            if(visitArr[value - 'A']) return false;
            visitArr[value - 'A'] = true;
        }

        return true;
    }
}
