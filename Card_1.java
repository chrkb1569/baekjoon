import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;

public class Card_1 {
    private static Deque<Integer> resultDeque = new LinkedList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());

        initResultDeque(N);

        while(resultDeque.size() >= 2) {
            int deleteValue = resultDeque.pollFirst();
            int offerValue = resultDeque.pollFirst();
            resultDeque.offerLast(offerValue);

            sb.append(deleteValue).append(" ");
        }

        sb.append(resultDeque.pollFirst());

        System.out.println(sb);
    }

    private static void initResultDeque(int N) {
        for(int i = 1; i <= N; i++) {
            resultDeque.offer(i);
        }
    }
}
