import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

public class GrapNumber {
    private static PriorityQueue<Integer> resultQueue = new PriorityQueue<>(new Comparator<Integer>() {
        @Override
        public int compare(Integer o1, Integer o2) {
            if(o1 > 0 && o2 > 0) return o2 - o1;
            if(o1 < 0 && o2 < 0) return Math.abs(o2) - Math.abs(o1);
            return o2 - o1;
        }
    });

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int length = Integer.parseInt(br.readLine());

        for(int i = 0; i < length; i++) {
            resultQueue.offer(Integer.parseInt(br.readLine()));
        }

        System.out.println(getMaxSum());
    }

    private static long getMaxSum() {
        long sum = 0;

        while(resultQueue.size() >= 2) {
            int biggerNumber = resultQueue.poll();
            int smallerNumber = resultQueue.poll();

            if(biggerNumber == 0 && smallerNumber == 0) {
                resultQueue.offer(0);
                continue;
            }

            if(biggerNumber == 0) {
                if(!resultQueue.isEmpty()) {
                    sum += smallerNumber * (long)resultQueue.poll();
                    resultQueue.offer(0);

                }
                continue;
            }

            if(smallerNumber == 0) {
                sum += biggerNumber;
                resultQueue.offer(0);
                continue;
            }

            if(biggerNumber > 0 && smallerNumber < 0 && !resultQueue.isEmpty()) {
                sum += (((long)resultQueue.poll() * smallerNumber) + biggerNumber);
                continue;
            }

            sum += Math.max(biggerNumber + smallerNumber, biggerNumber * smallerNumber);
        }

        if(resultQueue.isEmpty()) return sum;

        for(int value : resultQueue) {
            sum += value;
        }

        return sum;
    }
}
