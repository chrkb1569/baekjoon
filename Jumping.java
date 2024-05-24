import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Jumping {
    private static PriorityQueue<Integer> resultQueue;
    private static int[] resultArr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuffer sb = new StringBuffer();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine()); // testCase

        for(int testCase = 0; testCase < T; testCase++) {
            int N = Integer.parseInt(br.readLine()); // log

            st = new StringTokenizer(br.readLine(), " "); // length

            initResultArr(N);
            mkResultQueue(st);

            mkResultArr();

            int minGap = getMinGap();

            sb.append(minGap).append("\n");
        }

        System.out.println(sb);
    }

    private static void initResultArr(int length) {
        resultArr = new int[length];
    }

    private static void mkResultQueue(StringTokenizer st) {
        resultQueue = new PriorityQueue<>(Collections.reverseOrder());

        for(;st.hasMoreTokens();) {
            resultQueue.add(Integer.parseInt(st.nextToken()));
        }
    }

    private static void mkResultArr() {
        Deque<Integer> deque = new LinkedList<>();

        deque.add(resultQueue.poll());

        while(!resultQueue.isEmpty()) {
            int value = resultQueue.poll();

            int leftValue = deque.peekFirst();
            int rightValue = deque.peekLast();

            if(leftValue > rightValue) {
                deque.addFirst(value);
                continue;
            }

            if(leftValue < rightValue) {
                deque.addLast(value);
                continue;
            }

            deque.addLast(value);
        }

        resultArr = deque.stream().mapToInt(Integer::intValue).toArray();
    }

    private static int getMinGap() {
        int gap = Math.abs(resultArr[resultArr.length - 1] - resultArr[0]);

        for(int index = 0; index < resultArr.length - 1; index++) {
            int currentValue = resultArr[index];
            int nextValue = resultArr[index + 1];

            gap = Math.max(gap, Math.abs(currentValue - nextValue));
        }

        return gap;
    }
}
