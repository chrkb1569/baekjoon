import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Sort_Card {
    public static PriorityQueue<Integer> resultQueue = new PriorityQueue<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int testCase = Integer.parseInt(br.readLine());

        for(int i = 0; i < testCase; i++) {
            resultQueue.offer(Integer.parseInt(br.readLine()));
        }

        System.out.println(getResult());
    }

    public static int getResult() {
        int result = 0;

        while(resultQueue.size() != 1) {
            int value1 = resultQueue.poll();
            int value2 = resultQueue.poll();

            int sum = value1 + value2;

            resultQueue.offer(sum);
            result += sum;
        }

        return result;
    }
}
