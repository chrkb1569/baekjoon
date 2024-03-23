import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.util.Queue;

public class PrinterQueue {
    private static Queue<Document> resultQueue;
    private static PriorityQueue<Integer> weightQueue;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for(int testCase = 0; testCase < T; testCase++) {
            st = new StringTokenizer(br.readLine(), " ");

            int N = Integer.parseInt(st.nextToken()); // document
            int M = Integer.parseInt(st.nextToken()); // index

            st = new StringTokenizer(br.readLine(), " "); // document status

            initResultQueue(st);

            sb.append(getCount(M)).append("\n");
        }

        System.out.println(sb);
    }

    private static void initResultQueue(StringTokenizer st) {
        resultQueue = new LinkedList<>();
        weightQueue = new PriorityQueue<>(Collections.reverseOrder());

        int index = 0;

        for(;st.hasMoreTokens();) {
            int value = Integer.parseInt(st.nextToken());

            resultQueue.add(new Document(index++, value));
            weightQueue.add(value);
        }
    }

    private static int getCount(int M) {
        int count = 0;

        while(!resultQueue.isEmpty()) {
            Document document = resultQueue.poll(); // Queue에 저장되어 있던 값
            int peekValue = weightQueue.peek(); // Queue 내부에 저장되어 있던 최댓값

            // 나머지 문서들 중, 현재 문서보다 중요도가 높은 문서가 존재한다면, Queue의 가장 뒤에 재배치할 것
            if(document.getPriority() != peekValue) {
                resultQueue.add(document);
                continue;
            }

            count++;
            weightQueue.poll();
            if(document.getIndex() == M) break;
        }

        return count;
    }

    private static class Document {
        private int index;
        private int priority;

        public Document(int index, int priority) {
            this.index = index;
            this.priority = priority;
        }

        public int getIndex() {
            return this.index;
        }

        public int getPriority() {
            return this.priority;
        }
    }
}