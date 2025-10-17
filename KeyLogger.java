import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;

public class KeyLogger {

    private static ArrayDeque<Character> leftDeque = new ArrayDeque<>();
    private static ArrayDeque<Character> rightDeque = new ArrayDeque<>();

    private final static StringBuilder sb = new StringBuilder();

    private final static char BACK_SPACE = '-';
    private final static char TO_LEFT = '<';
    private final static char TO_RIGHT = '>';

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for(int testCase = 0; testCase < T; testCase++) {
            String userInput = br.readLine();

            String password = getPassword(userInput);

            sb.append(password).append("\n");
        }

        System.out.println(sb);
    }

    private static String getPassword(String userInput) {
        leftDeque = new ArrayDeque<>();
        rightDeque = new ArrayDeque<>();

        for(char value : userInput.toCharArray()) {
            if(value == BACK_SPACE) {
                handleBackSpace();
                continue;
            }

            if(value == TO_LEFT) {
                moveLeft();
                continue;
            }

            if(value == TO_RIGHT) {
                moveRight();
                continue;
            }

            leftDeque.addLast(value);
        }

        return getResultString();
    }

    private static void handleBackSpace() {
        if(leftDeque.isEmpty()) return;
        leftDeque.removeLast();
    }

    private static void moveLeft() {
        if(leftDeque.isEmpty()) return;

        rightDeque.addLast(leftDeque.removeLast());
    }

    private static void moveRight() {
        if(rightDeque.isEmpty()) return;

        leftDeque.addLast(rightDeque.removeLast());
    }

    private static String getResultString() {
        StringBuilder passwordBuilder = new StringBuilder();

        while(!leftDeque.isEmpty()) {
            passwordBuilder.append(leftDeque.removeFirst());
        }

        while(!rightDeque.isEmpty()) {
            passwordBuilder.append(rightDeque.removeLast());
        }

        return passwordBuilder.toString();
    }
}
