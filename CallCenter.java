import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;

public class CallCenter {

    private final static PriorityQueue<Integer> leftQueue = new PriorityQueue<>(Collections.reverseOrder());
    private final static PriorityQueue<Integer> rightQueue = new PriorityQueue<>();

    private final static StringBuilder sb = new StringBuilder();

    private static int midNumber;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        for (int testCase = 1; testCase <= N; testCase++) {
            int number = Integer.parseInt(br.readLine());
            int centerNumber = callCenterNumber(testCase, number);

            sb.append(centerNumber).append("\n");
        }

        System.out.println(sb);
    }

    private static int callCenterNumber(int count, int number) {
        if (count == 1) {
            midNumber = number;

            return midNumber;
        }

        if (midNumber > number) leftQueue.add(number);
        if (midNumber <= number) rightQueue.add(number);

        // 현재 회차가 짝수인 경우
        // rightQueue의 size가 leftQueue의 사이즈보다 1커야함.
        if (count % 2 == 0) {
            arrangeQueueAtEven(count);

            return midNumber;
        }

        // 현재 회차가 홀수인 경우
        // leftQueue의 size와 rightQueue의 size가 동일해야함.
        arrangeQueueAtOdd(count);

        return midNumber;
    }

    private static void arrangeQueueAtEven(int count) {
        int leftQueueSize = leftQueue.size();
        int rightQueueSize = rightQueue.size();

        if (rightQueueSize - leftQueueSize == 1) return;

        int stdRightSize = count / 2;

        if (leftQueueSize > rightQueueSize) {
            while (rightQueueSize != stdRightSize) {
                int pollValue = leftQueue.poll();
                rightQueue.add(midNumber);
                midNumber = pollValue;
                rightQueueSize++;
            }
            return;
        }

        while (rightQueueSize != stdRightSize) {
            int pollValue = rightQueue.poll();
            leftQueue.add(midNumber);
            midNumber = pollValue;
            rightQueueSize--;
        }
    }

    private static void arrangeQueueAtOdd(int count) {
        int leftQueueSize = leftQueue.size();
        int rightQueueSize = rightQueue.size();

        if (leftQueueSize == rightQueueSize) return;

        int stdSize = count / 2;

        if (leftQueueSize > rightQueueSize) {
            while (rightQueueSize != stdSize) {
                int pollValue = leftQueue.poll();
                rightQueue.add(midNumber);
                midNumber = pollValue;
                rightQueueSize++;
            }

            return;
        }

        while (leftQueueSize != stdSize) {
            int pollValue = rightQueue.poll();
            leftQueue.add(midNumber);
            midNumber = pollValue;
            leftQueueSize++;
        }
    }
}
