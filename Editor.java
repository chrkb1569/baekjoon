import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Editor {
    private static Stack<Character> leftStack = new Stack<>();
    private static Stack<Character> rightStack = new Stack<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String origin = br.readLine();

        mkLeftStack(origin);

        int M = Integer.parseInt(br.readLine());

        for(int command = 0; command < M; command++) {
            String userInput = br.readLine();

            processCommand(userInput);
        }

        printResult();
    }

    private static void mkLeftStack(String origin) {
        for(char value : origin.toCharArray()) {
            leftStack.push(value);
        }
    }

    private static void processCommand(String command) {
        char commandValue = command.charAt(0);

        // 커서 왼쪽으로 이동
        if(commandValue == 'L') {
            if(leftStack.isEmpty()) return;
            rightStack.push(leftStack.pop());
            return;
        }

        // 커서 오른쪽으로 이동
        if(commandValue == 'D') {
            if(rightStack.isEmpty()) return;
            leftStack.push(rightStack.pop());
            return;
        }

        // 커서 왼쪽 문자 삭제
        if(commandValue == 'B') {
            if(leftStack.isEmpty()) return;
            leftStack.pop();
            return;
        }

        // signalValue를 커서 왼쪽에 추가
        StringTokenizer st = new StringTokenizer(command, " ");

        String signal = st.nextToken();
        char signalValue = st.nextToken().charAt(0);

        leftStack.push(signalValue);
    }

    private static void printResult() {
        StringBuffer sb = new StringBuffer();

        for(char value : leftStack) {
            sb.append(value);
        }

        while(!rightStack.isEmpty()) {
            sb.append(rightStack.pop());
        }

        System.out.println(sb);
    }
}
