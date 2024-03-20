import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Hide_And_Seek_2 {
    private static int[] resultArr = new int[100_001];
    private static int MIN_TIME = Integer.MAX_VALUE;
    private static int MIN_COUNT = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int N = Integer.parseInt(st.nextToken()); // start
        int K = Integer.parseInt(st.nextToken()); // end

        if(N >= K) {
            System.out.println(N-K);
            System.out.println(1);
            return;
        }

        getInfo(N, K);

        System.out.println(MIN_TIME);
        System.out.println(MIN_COUNT);
    }

    private static void getInfo(int startPoint, int endPoint) {
        Queue<Integer> resultQueue = new LinkedList<>();
        resultQueue.add(startPoint);

        resultArr[startPoint] = 1;

        while(!resultQueue.isEmpty()) {
            int currentPoint = resultQueue.poll();

            if(resultArr[currentPoint] > MIN_TIME) return;

            for(int i = 0; i < 3; i++) {
                int next;

                if(i == 0) next = currentPoint + 1;
                else if(i == 1) next = 2 * currentPoint;
                else next = currentPoint - 1;

                if(next < 0 || next >= resultArr.length) continue;

                if(next == endPoint) {
                    MIN_TIME = resultArr[currentPoint];
                    MIN_COUNT++;
                }

                if(resultArr[next] == 0 || resultArr[next] == resultArr[currentPoint] + 1) {
                    resultQueue.add(next);
                    resultArr[next] = resultArr[currentPoint] + 1;
                }
            }
        }
    }
}
