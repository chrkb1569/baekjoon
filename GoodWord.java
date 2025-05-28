import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class GoodWord {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int wordCount = 0;

        for(int testCase = 0; testCase < N; testCase++) {
            String word = br.readLine();

            if(!isValidate(word)) continue;
            wordCount++;
        }

        System.out.println(wordCount);
    }

    private static boolean isValidate(String word) {
        Stack<Character> resultStack = new Stack<>();

        char[] wordArr = word.toCharArray();

        for(char value : wordArr) {
            if(resultStack.isEmpty()) {
                resultStack.push(value);
                continue;
            }

            char peekValue = resultStack.peek();

            if(peekValue == value) {
                resultStack.pop();
                continue;
            }

            resultStack.push(value);
        }

        return resultStack.isEmpty();
    }
}
