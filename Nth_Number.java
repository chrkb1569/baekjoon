import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Nth_Number {

    private final static PriorityQueue<Integer> resultQueue = new PriorityQueue<>(Collections.reverseOrder());

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        for(int testCase = 0; testCase < N; testCase++) {
            st = new StringTokenizer(br.readLine(), " ");

            initResultQueue(st, N);
        }

        System.out.println(getNthNumber(N));
    }

    private static void initResultQueue(StringTokenizer st, int length) {
        for(int number = 0; number < length; number++) {
            int value = Integer.parseInt(st.nextToken());
            resultQueue.add(value);
        }
    }

    private static int getNthNumber(int N) {
        int count = 1;

        while(count != N) {
            resultQueue.poll();
            count++;
        }

        return resultQueue.poll();
    }
}
