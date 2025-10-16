import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class MakeBigger {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        String number = br.readLine();

        String maxNumber = calculateMaxNumber(number, N, K);

        System.out.println(maxNumber);
    }

    public static String calculateMaxNumber(String userInput, int length, int count) {
        char[] resultStack = new char[length];
        int top = 0;

        for(char value : userInput.toCharArray()) {
            while(top > 0 && count > 0 && resultStack[top - 1] < value) {
                top--;
                count--;
            }

            resultStack[top++] = value;
        }

        return new String(resultStack, 0, top - count);
    }
}