import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class DeathKnight {

    private static boolean[][] visitArr;

    private static final int[][] pathArr = {
              {-2, -1}
            , {-2, 1}
            , {0, -2}
            , {0, 2}
            , {2, -1}
            , {2, 1}
    };

    private static int MIN_VALUE = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine()); // 체스판의 크기

        initVisitArr(N);

        StringTokenizer st = new StringTokenizer(br.readLine());

        int startHeight = Integer.parseInt(st.nextToken());
        int startWidth = Integer.parseInt(st.nextToken());
        int endHeight = Integer.parseInt(st.nextToken());
        int endWidth = Integer.parseInt(st.nextToken());

        findPath(startHeight, startWidth, endHeight, endWidth);

        printResult();
    }

    private static void initVisitArr(int length) {
        visitArr = new boolean[length][length];
    }

    private static void findPath(int startHeight, int startWidth, int endHeight, int endWidth) {
        Queue<int[]> resultQueue = new LinkedList<>();
        resultQueue.add(new int[]{startHeight, startWidth, 0});

        visitArr[startHeight][startWidth] = true;

        while(!resultQueue.isEmpty()) {
            int[] currentIndex = resultQueue.poll();

            int currentHeight = currentIndex[0];
            int currentWidth = currentIndex[1];
            int pathCount = currentIndex[2];

            if(endHeight == currentHeight && endWidth == currentWidth) {
                MIN_VALUE = Math.min(MIN_VALUE, pathCount);
                continue;
            }

            for(int index = 0; index < pathArr.length; index++) {
                int[] pathInfo = pathArr[index];

                int nextHeight = currentHeight + pathInfo[0];
                int nextWidth = currentWidth + pathInfo[1];

                if(nextHeight >= visitArr.length ||
                        nextWidth >= visitArr.length ||
                        nextHeight < 0 || nextWidth < 0) continue;
                if(visitArr[nextHeight][nextWidth]) continue;

                visitArr[nextHeight][nextWidth] = true;
                resultQueue.add(new int[]{nextHeight, nextWidth, pathCount + 1});
            }
        }
    }

    private static void printResult() {
        if(MIN_VALUE == Integer.MAX_VALUE) {
            System.out.println("-1");
            return;
        }

        System.out.println(MIN_VALUE);
    }
}
