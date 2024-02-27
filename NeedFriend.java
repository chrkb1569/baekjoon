import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class NeedFriend {
    private static char[][] resultArr;
    private static boolean[][] visitArr;
    private final static int[] dx = {1, -1, 0, 0};
    private final static int[] dy = {0, 0, 1, -1};
    private final static char WALL_SPACE = 'X';
    private final static char START_SPACE = 'I';
    private final static char PERSON_SPACE = 'P';
    private static int resultCount = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        initResultArr(N, M);
        initVisitArr(N, M);

        for(int height = 0; height < N; height++) {
            String userInput = br.readLine();
            mkResultArr(userInput, height);
        }

        for(int height = 0; height < N; height++) {
            for(int width = 0; width < M; width++) {
                char value = resultArr[height][width];

                if(value != START_SPACE) continue;

                resultCount = bfs(height, width);
                break;
            }
        }

        printResult();
    }

    private static void initResultArr(int height, int width) {
        resultArr = new char[height][width];
    }

    private static void initVisitArr(int height, int width) {
        visitArr = new boolean[height][width];
    }

    private static void mkResultArr(String value, int height) {
        resultArr[height] = value.toCharArray();
    }

    private static int bfs(int height, int width) {
        Queue<int[]> resultQueue = new LinkedList<>();
        visitArr[height][width] = true;
        resultQueue.offer(new int[]{height, width});

        int count = 0;

        while(!resultQueue.isEmpty()) {
            int[] indexArr = resultQueue.poll();

            int y = indexArr[0];
            int x = indexArr[1];

            if(resultArr[y][x] == PERSON_SPACE) count++;

            for(int i = 0; i < 4; i++) {
                int xValue = x + dx[i];
                int yValue = y + dy[i];

                if(xValue < 0 || yValue < 0 || xValue >= resultArr[0].length || yValue >= resultArr.length) continue;
                if(visitArr[yValue][xValue]) continue;
                if(resultArr[yValue][xValue] == WALL_SPACE) continue;

                visitArr[yValue][xValue] = true;

                resultQueue.offer(new int[]{yValue, xValue});
            }
        }

        return count;
    }

    private static void printResult() {
        if(resultCount == 0) {
            System.out.println("TT");
            return;
        }

        System.out.println(resultCount);
    }
}
