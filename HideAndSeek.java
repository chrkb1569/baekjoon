import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class HideAndSeek {
    private static int[] resultArr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int from = Integer.parseInt(st.nextToken());
        int to = Integer.parseInt(st.nextToken());

        initResultArr();
        findPath(from, to);

        System.out.println(resultArr[to]);
    }

    private static void initResultArr() {
        resultArr = new int[100001];

        Arrays.fill(resultArr, 100000000);
    }

    public static void findPath(int current, int target) {
        Deque<Integer> resultQueue = new LinkedList<>();
        resultQueue.offer(current);
        resultArr[current] = 0;

        while(!resultQueue.isEmpty()) {
            int index = resultQueue.pollFirst();

            if(index == target) return;

            if(2 * index <= 100000 && resultArr[2 * index] == 100000000) {
                resultQueue.offerFirst(2 * index);
                resultArr[2 * index] = Math.min(resultArr[2 * index], resultArr[index]);
            }

            if(index - 1 >= 0 && resultArr[index - 1] == 100000000) {
                resultQueue.offerLast(index - 1);
                resultArr[index - 1] = Math.min(resultArr[index - 1], resultArr[index] + 1);
            }

            if(index + 1 <= 100000 && resultArr[index + 1] == 100000000) {
                resultQueue.offerLast(index + 1);
                resultArr[index + 1] = Math.min(resultArr[index + 1], resultArr[index] + 1);
            }
        }
    }
}
