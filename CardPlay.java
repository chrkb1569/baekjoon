import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class CardPlay {
    private static PriorityQueue<Long> resultQueue = new PriorityQueue<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine(), " ");

        initResultQueue(st);

        playCardGame(M);

        System.out.println(getSum());
    }

    private static void initResultQueue(StringTokenizer st) {
        for(;st.hasMoreTokens();) {
            long value = Long.parseLong(st.nextToken());
            resultQueue.add(value);
        }
    }

    private static void playCardGame(int times) {
        for(int time = 0; time < times; time++) {
            long card1 = resultQueue.poll();
            long card2 = resultQueue.poll();

            resultQueue.add(card1 + card2);
            resultQueue.add(card1 + card2);
        }
    }

    private static long getSum() {
        long sum = 0;

        while(!resultQueue.isEmpty()) {
            sum += resultQueue.poll();
        }

        return sum;
    }
}