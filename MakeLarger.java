import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Stack;

public class MakeLarger {

    public static Stack<Character> stack;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int totalLength = Integer.parseInt(st.nextToken());
        int deleteLength = Integer.parseInt(st.nextToken());

        String targetValue = br.readLine();

        initStack();

        System.out.println(getMaxString(targetValue, deleteLength));
    }

    public static String getMaxString(String targetValue, int deleteCount) {

        int count = 0;

        for(char value : targetValue.toCharArray()) {
            while(!stack.isEmpty() && stack.peek() < value && count < deleteCount) {
                count++;
                stack.pop();
            }
            stack.push(value);
        }

        return stackToString(targetValue, deleteCount);
    }

    public static String stackToString(String targetValue, int deleteCount) {
        StringBuilder sb = new StringBuilder();

        for(char value : stack) {
            sb.append(value);
        }

        String resultString = sb.toString();

        if(resultString.length() > (targetValue.length() - deleteCount)) {
            return resultString.substring(0, targetValue.length() - deleteCount);
        }

        return resultString;
    }

    public static void initStack() {
        stack = new Stack<>();
    }
}
