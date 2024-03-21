import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class PostFix {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        char[] infoArr = br.readLine().toCharArray();

        String postFix = getPostFixResult(infoArr);

        System.out.println(postFix);
    }

    private static String getPostFixResult(char[] infoArr) {
        Stack<Character> resultStack = new Stack<>();
        StringBuilder sb = new StringBuilder();

        for(int index = 0; index < infoArr.length; index++) {
            char value = infoArr[index];

            if(value == '+' || value == '-' || value == '*' || value == '/') {
                while(!resultStack.isEmpty() && getPriority(resultStack.peek()) >= getPriority(value)) {
                    sb.append(resultStack.pop());
                }
                resultStack.add(value);
                continue;
            }

            if(value == '(') {
                resultStack.add(value);
                continue;
            }

            if(value == ')') {
                while(!resultStack.isEmpty() && resultStack.peek() != '(') {
                    sb.append(resultStack.pop());
                }
                resultStack.pop();
                continue;
            }

            sb.append(value);
        }

        while(!resultStack.isEmpty()) {
            sb.append(resultStack.pop());
        }

        return sb.toString();
    }

    private static int getPriority(char operator) {
        if(operator == '(' || operator == ')') return 0;
        if(operator == '+' || operator == '-') return 1;
        return 2;
    }
}
