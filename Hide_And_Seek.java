import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Hide_And_Seek {
    private static boolean[] visitArr = new boolean[100_001];
    private static int[] depthArr = new int[100_001];
    private static int MAX_PATH_COUNT = 100_000;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int start = Integer.parseInt(st.nextToken());
        int destination = Integer.parseInt(st.nextToken());

        Arrays.fill(depthArr, MAX_PATH_COUNT);

        findMinimumPath(start);

        System.out.println(depthArr[destination]);
    }

    private static void findMinimumPath(int start) {
        Queue<Integer> resultQueue = new LinkedList<>();
        visitArr[start] = true;
        depthArr[start] = 0;
        resultQueue.offer(start);

        while(!resultQueue.isEmpty()) {
            int currentIndex = resultQueue.poll();

            int depth = depthArr[currentIndex];

            if(currentIndex + 1 < visitArr.length &&
                    !visitArr[currentIndex + 1] &&
                    depthArr[currentIndex + 1] > depth + 1) {
                visitArr[currentIndex + 1] = true;
                depthArr[currentIndex + 1] = depth + 1;
                resultQueue.offer(currentIndex+1);
            }

            if(currentIndex - 1 >= 0 &&
                    !visitArr[currentIndex - 1] &&
                    depthArr[currentIndex - 1] > depth + 1) {
                visitArr[currentIndex - 1] = true;
                depthArr[currentIndex - 1] = depth + 1;
                resultQueue.offer(currentIndex-1);
            }

            if(currentIndex * 2 < visitArr.length &&
                    !visitArr[2 * currentIndex] &&
                    depthArr[2 * currentIndex] > depth + 1) {
                visitArr[2 * currentIndex] = true;
                depthArr[2 * currentIndex] = depth + 1;
                resultQueue.offer(2 * currentIndex);
            }
        }
    }
}
