import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class ProblemBook {
    private static Problem[] valueArr;
    private static int[] visitArr;
    private static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int problem = Integer.parseInt(st.nextToken());
        int correlation = Integer.parseInt(st.nextToken());

        initValueArr(problem);
        initVisitArr(problem);

        for(int i = 0; i < correlation; i++) {
            st = new StringTokenizer(br.readLine(), " ");

            int first = Integer.parseInt(st.nextToken());
            int second = Integer.parseInt(st.nextToken());

            valueArr[first].getLink().add(second);
            visitArr[second]++;
        }

        topologicalSort();

        System.out.println(sb);
    }

    private static void initValueArr(int length) {
        valueArr = new Problem[length + 1];

        for(int i = 1; i < valueArr.length; i++) {
            valueArr[i] = new Problem(i);
        }
    }

    private static void initVisitArr(int length) {
        visitArr = new int[length + 1];
    }

    private static void topologicalSort() {
        PriorityQueue<Integer> resultQueue = new PriorityQueue<>();

        for(int i = 1; i < visitArr.length; i++) {
            if(visitArr[i] == 0) resultQueue.offer(i);
        }

        while(!resultQueue.isEmpty()) {
            int problem = resultQueue.poll();

            sb.append(problem).append(" ");

            for(int nextProblem : valueArr[problem].getLink()) {
                visitArr[nextProblem]--;

                if(visitArr[nextProblem] == 0) resultQueue.offer(nextProblem);
            }
        }
    }

    private static class Problem {
        private int number;
        private List<Integer> link = new LinkedList<>();

        public Problem(int number) {
            this.number = number;
        }

        public int getNumber() {
            return this.number;
        }

        public List<Integer> getLink() {
            return this.link;
        }
    }
}
