import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;
import java.util.Queue;

public class SnakeAndLadderGame {
    private static int[] gameArr = new int[101];
    private static int[] resultArr = new int[101];
    private static boolean[] visitArr = new boolean[101];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");

        int ladder = Integer.parseInt(st.nextToken());
        int snake = Integer.parseInt(st.nextToken());

        for(int i = 0; i < ladder + snake; i++) {
            st = new StringTokenizer(br.readLine(), " ");

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            gameArr[from] = to;
        }

        startGame(1);

        System.out.println(resultArr[100]);
    }

    private static void startGame(int startIndex) {
        Queue<Integer> resultQueue = new LinkedList<>();
        resultQueue.offer(startIndex);
        visitArr[startIndex] = true;

        while(!resultQueue.isEmpty()) {
            int index = resultQueue.poll();

            if(index == 100) return;

            int count = resultArr[index];

            for(int i = 1; i <= 6; i++) {
                int nextIndex = index + i;

                if(nextIndex > 100) continue;
                if(visitArr[nextIndex]) continue;

                visitArr[nextIndex] = true;
                int next = gameArr[nextIndex];

                if(next != 0 && !visitArr[next]) {
                    visitArr[next] = true;
                    resultQueue.offer(next);
                    resultArr[next] = count + 1;
                    continue;
                }

                if(next == 0) {
                    resultQueue.offer(nextIndex);
                    resultArr[nextIndex] = count + 1;
                }
            }
        }
    }
}
