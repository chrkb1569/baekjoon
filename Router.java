import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;

public class Router {

    private final static ArrayDeque<Integer> resultDeque = new ArrayDeque<>();

    private final static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        while(true) {
            int input = Integer.parseInt(br.readLine());

            if(input == -1) break;
            if(input == 0) {
                resultDeque.removeFirst();
                continue;
            }

            if(resultDeque.size() == N) continue;
            resultDeque.addLast(input);
        }

        printResult();
    }

    private static void printResult() {
        if(resultDeque.isEmpty()) {
            System.out.println("empty");
            return;
        }

        for(int value : resultDeque) {
            sb.append(value).append(" ");
        }

        System.out.println(sb);
    }
}
