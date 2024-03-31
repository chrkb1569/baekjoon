import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;

public class MaxHeap {
    private static PriorityQueue<Integer> resultQueue = new PriorityQueue<>(Collections.reverseOrder());

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());

        for(int testCase = 0; testCase < N; testCase++) {
            int number = Integer.parseInt(br.readLine());

            // 힙이 비어있는 경우
            if(number == 0 && resultQueue.isEmpty()) {
                sb.append(0).append("\n");
                continue;
            }

            // 값을 추출해야하는 경우
            if(number == 0) {
                int maxValue = resultQueue.poll();

                sb.append(maxValue).append("\n");
                continue;
            }

            resultQueue.add(number);
        }

        System.out.println(sb);
    }
}