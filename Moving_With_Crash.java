import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Moving_With_Crash {

    private static int[][] resultArr;
    private static byte[][][] visitArr;

    private static final int[] dx = {1, -1, 0, 0};
    private static final int[] dy = {0, 0, 1, -1};

    private static boolean flag = false;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int N = Integer.parseInt(st.nextToken()); // height
        int M = Integer.parseInt(st.nextToken()); // width
        int K = Integer.parseInt(st.nextToken()); // limit

        initResultArr(N, M);
        initVisitArr(N, M, K);

        for(int height = 0; height < resultArr.length; height++) {
            String userInput = br.readLine();
            mkResultArr(height, userInput);
        }

        bfs(0, 0);

        printMinDistance();
    }

    private static void initResultArr(int height, int width) {
        resultArr = new int[height][width];
    }

    private static void initVisitArr(int height, int width, int limit) {
        visitArr = new byte[height][width][limit + 1];
    }

    private static void mkResultArr(int height, String userInput) {
        char[] inputArr = userInput.toCharArray();

        for(int index = 0; index < inputArr.length; index++) {
            resultArr[height][index] = inputArr[index] - '0';
        }
    }

    private static void bfs(int height, int width) {
        Queue<int[]> resultQueue = new LinkedList<>();

        visitArr[height][width][0] = 1;
        // height, width, chance
        resultQueue.add(new int[]{height, width, 0, 1});

        while(!resultQueue.isEmpty()) {
            int[] currentInfo = resultQueue.poll();

            int y = currentInfo[0];
            int x = currentInfo[1];
            int chance = currentInfo[2];
            int currentCost = currentInfo[3];

            if(y == resultArr.length - 1 && x == resultArr[0].length - 1) {
                flag = true;
                System.out.println(currentCost);
                break;
            }

            for(int i = 0; i < 4; i++) {
                int xValue = x + dx[i];
                int yValue = y + dy[i];

                if(xValue < 0 || xValue >= resultArr[0].length) continue;
                if(yValue < 0 || yValue >= resultArr.length) continue;

                int nextSpot = resultArr[yValue][xValue];

                // 이동 가능한 지역인 경우, 벽을 부수지 않고 이동
                if(nextSpot == 0) {
                    if(visitArr[yValue][xValue][chance] == 1) continue;

                    visitArr[yValue][xValue][chance] = 1;
                    resultQueue.add(new int[]{yValue, xValue, chance, currentCost + 1});
                }

                // 이동 불가능한 지역인 경우, 벽을 부수고 이동
                if(nextSpot == 1) {
                    if(chance + 1 >= visitArr[0][0].length) continue;
                    if(visitArr[yValue][xValue][chance + 1] == 1) continue;

                    visitArr[yValue][xValue][chance + 1] = 1;
                    resultQueue.add(new int[]{yValue, xValue, chance + 1, currentCost + 1});
                }
            }
        }
    }

    private static void printMinDistance() {
        if(!flag) System.out.println("-1");
    }
}