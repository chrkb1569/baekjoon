import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Value_Of_Bracket {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String bracket = br.readLine();
        printResult(bracket);
    }

    public static void printResult(String bracket) {
        if(!validateString(bracket)) {
            System.out.println("0");
            return;
        }

        System.out.println(getBracketResult(bracket));
    }

    public static int getBracketResult(String bracket) {
        Stack<String> stack = (Stack<String>) getStack();

        for(int i = 0; i < bracket.length(); i++) {
            String value = convertChar(bracket.charAt(i));

            if(value.equals("(") || value.equals("[")) {
                stack.push(value);
                continue;
            }

            String popValue = stack.pop();

            if(isBracket(popValue)) {
                calculateWithBracket(stack, popValue, value);
                continue;
            }

            calculateWithoutBracket(stack, popValue, value);
        }

        return Integer.parseInt(stack.pop());
    }

    public static boolean isBracket(String value) {
        return value.equals("(") || value.equals(")") || value.equals("[") || value.equals("]");
    }

    public static void calculateWithBracket(Stack<String> stack, String popValue, String value) {
        if(verifyTopValue(stack)) {
            numberPush(stack, popValue, value);
            return;
        }
        justPush(stack, popValue, value);
    }

    public static void calculateWithoutBracket(Stack<String> stack, String popValue, String value) {
        int numValue = Integer.parseInt(popValue);
        String first = stack.pop();

        if(verifyTopValue(stack)) {
            numberPush(stack, first, value, numValue);
            return;
        }

        justPush(stack, first, value, numValue);
    }

    public static boolean verifyTopValue(Stack<String> stack) {
        if(stack.isEmpty()) return false;

        String value = stack.peek();
        if(isBracket(value)) return false;

        return true;
    }

    public static void justPush(Stack<String> stack, String popValue, String value) {
        if(popValue.equals("(") && value.equals(")")) {
            stack.push("2");
            return;
        }

        stack.push("3");
    }

    public static void justPush(Stack<String> stack, String start, String end, int value) {
        if(start.equals("(") && end.equals(")")) {
            stack.push(String.valueOf(value * 2));
            return;
        }
        stack.push(String.valueOf(value * 3));
    }

    public static void numberPush(Stack<String> stack, String popValue, String value) {
        int topValue = Integer.parseInt(stack.pop());

        if(popValue.equals("(") && value.equals(")")) {
            stack.push(String.valueOf(topValue + 2));
            return;
        }

        stack.push(String.valueOf(topValue + 3));
    }

    public static void numberPush(Stack<String> stack, String start, String end, int value) {
        int numValue = Integer.parseInt(stack.pop());

        if(start.equals("(") && end.equals(")")) {
            stack.push(String.valueOf(value * 2 + numValue));
            return;
        }
        stack.push(String.valueOf(value * 3 + numValue));
    }

    public static boolean validateString(String bracket) {
        Stack<Character> stack = (Stack<Character>) getStack();

        for(int i = 0; i < bracket.length(); i++) {
            char value = bracket.charAt(i);

            if(value == '(' || value == '[') {
                stack.push(value);
                continue;
            }

            if(stack.isEmpty()) return false;

            if(value == ')' && stack.peek() == '(') {
                stack.pop();
                continue;
            }

            if(value == ']' && stack.peek() == '[') {
                stack.pop();
            }
        }

        return stack.isEmpty();
    }

    public static String convertChar(char value) {
        return "" + value;
    }

    public static Stack<?> getStack() {
        return new Stack<>();
    }
}