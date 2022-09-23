import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Stack_Progression {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        Stack<Integer> origin = new Stack<>();
        Stack<Integer> stack = new Stack<>();
        boolean printable = true;

        int topValue = 0;

        int N = Integer.parseInt(br.readLine());

        for(int i = N; i >=1; i--) {
            origin.push(i);
        }

        for(int i = 0; i < N; i++) {
            int value = Integer.parseInt(br.readLine());
            boolean success = true;

            if(stack.isEmpty() && origin.isEmpty() && i == N-1) {
                printable = false;
                break;
            }

            if(topValue <= value) {
                for(int j = 0; j < value - topValue; j++) {
                    sb.append("+").append("\n");
                    stack.push(origin.pop());
                }
                topValue = stack.peek();
            }

            int size = stack.size();

            for(int j = 0; j < size; j++) {
                int peekValue = stack.pop();
                sb.append("-").append("\n");

                if(peekValue == value) {
                    break;
                }

                if(j == size - 1 && peekValue != value) {
                    success = false;
                }
            }

            if(!success) {
                printable = false;
            }
        }

        if(printable) {
            System.out.print(sb);
        }
        else {
            System.out.println("NO");
        }

    }
}