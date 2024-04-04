import java.io.BufferedReader;
import java.io.InputStreamReader;

public class IOI {
    private static int[] resultArr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine()); // Pattern Length ==> 2 * N + 1
        int M = Integer.parseInt(br.readLine()); // S length
        String S = br.readLine();

        initResultArr(S.length());
        mkResultArr(S);

        System.out.println(checkResult(N));
    }

    private static void initResultArr(int length) {
        resultArr = new int[length];
    }

    private static void mkResultArr(String s) {
        resultArr[0] = 1;
        resultArr[1] = 1;

        for(int index = 2; index < s.length(); index++) {
            char value = s.charAt(index);

            if(value == 'O') {
                resultArr[index] = 1;
                continue;
            }

            if(s.charAt(index - 2) == 'O' || s.charAt(index - 1) == 'I') {
                resultArr[index] = 1;
                continue;
            }

            resultArr[index] = resultArr[index-2] + 2;
        }
    }

    private static int checkResult(int N) {
        int count = 0;

        for(int value : resultArr) {
            if(value >= 2 * N + 1) count++;
        }

        return count;
    }
}