import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Palindrome_2 {
    private static int[] resultArr;
    private static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int length = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine(), " ");

        initResultArr(length);
        mkResultArr(st);

        int question = Integer.parseInt(br.readLine());

        for(int i = 0; i < question; i++) {
            st = new StringTokenizer(br.readLine(), " ");

            int startIndex = Integer.parseInt(st.nextToken());
            int endIndex = Integer.parseInt(st.nextToken());

            if(isPalinDrome(startIndex, endIndex)) {
                sb.append("1").append("\n");
                continue;
            }

            sb.append("0").append("\n");
        }

        System.out.println(sb);
    }

    private static void initResultArr(int length) {
        resultArr = new int[length];
    }

    private static void mkResultArr(StringTokenizer st) {
        int index = 0;

        for(;st.hasMoreTokens();) {
            resultArr[index++] = Integer.parseInt(st.nextToken());
        }
    }

    private static boolean isPalinDrome(int start, int end) {
        if(start == end) return true;
        return checkRangeValidation(start, end);
    }

    private static boolean checkRangeValidation(int start, int end) {
        for(int i = 0; i <= (end - start + 1)/2; i++) {
            int startNumber = resultArr[start-1+i];
            int endNumber = resultArr[end-1-i];

            if(startNumber != endNumber) return false;
        }

        return true;
    }
}
