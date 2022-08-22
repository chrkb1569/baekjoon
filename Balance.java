import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Balance {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        boolean result = true;

        String str = br.readLine();

        while(!str.equals(".")) {
            Stack<Character> stack = new Stack();
            for(char s : str.toCharArray()) {
                if(s == '(' || s == '[') {
                    stack.push(s);
                }

                else if(s == ')') {
                    if(stack.isEmpty()) {
                        result = false;
                        break;
                    }

                    char value = stack.peek();

                    if(value == '(') {
                        stack.pop();
                        continue;
                    }
                    else {
                        result = false;
                        break;
                    }
                }

                else if(s == ']') {
                    if(stack.isEmpty()) {
                        result = false;
                        break;
                    }

                    char value = stack.peek();

                    if(value == '[') {
                        stack.pop();
                        continue;
                    }
                    else {
                        result = false;
                        break;
                    }
                }

                else continue;
            }

            if(!stack.isEmpty()) {
                result = false;
            }

            System.out.println(result? "yes" : "no");
            str = br.readLine();
            result = true;
        }
    }
}
