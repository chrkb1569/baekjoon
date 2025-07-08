import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.util.Queue;

public class MyUmbrella {

    private static char[][] infoArr;
    private static boolean[][][] visitArr;

    private static int[][] startPoint = new int[1][2];
    private static int[][] endPoint = new int[1][2];
    private static Map<Integer, Integer> itemMap = new HashMap<>();

    private static final int[] dx = {1, -1, 0, 0};
    private static final int[] dy = {0, 0, 1, -1};
    private static long MIN_COUNT = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int N = Integer.parseInt(st.nextToken()); // width
        int M = Integer.parseInt(st.nextToken()); // height

        initInfoArr(M, N);

        for(int height = 0; height < infoArr.length; height++) {
            String info = br.readLine();
            mkInfoArr(height, info);
        }

        initVisitArr(M, N);

        bfs();

        System.out.println(MIN_COUNT);
    }

    private static void initInfoArr(int height, int width) {
        infoArr = new char[height][width];
    }

    private static void initVisitArr(int height, int width) {
        visitArr = new boolean[height][width][(1 << (itemMap.size()))];
    }

    private static void mkInfoArr(int height, String userInput) {
        char[] userInputArr = userInput.toCharArray();

        for(int width = 0; width < userInputArr.length; width++) {
            char value = userInputArr[width];

            if(value == 'S') {
                startPoint[0][0] = height;
                startPoint[0][1] = width;
            }

            if(value == 'E') {
                endPoint[0][0] = height;
                endPoint[0][1] = width;
            }

            if(value == 'X') {
                int key = infoArr[0].length * height + width;
                itemMap.put(key, itemMap.size());
            }

            infoArr[height][width] = value;
        }
    }

    private static void bfs() {
        Queue<int[]> resultQueue = new LinkedList<>();

        int startHeight = startPoint[0][0];
        int startWidth = startPoint[0][1];
        int currentStatus = 0;
        int targetStatus = (1 << (itemMap.size())) - 1;

        int targetHeight = endPoint[0][0];
        int targetWidth = endPoint[0][1];

        visitArr[startHeight][startWidth][0] = true;

        resultQueue.add(new int[]{startHeight, startWidth, currentStatus, 0}); // height, width, currentStatus

        while(!resultQueue.isEmpty()) {
            int[] currentInfo = resultQueue.poll();

            int height = currentInfo[0];
            int width = currentInfo[1];
            int current = currentInfo[2];
            int pathCount = currentInfo[3];

            if(height == targetHeight && width == targetWidth && current == targetStatus) {
                MIN_COUNT = pathCount;
                break;
            }

            for(int i = 0; i < 4; i++) {
                int xValue = width + dx[i];
                int yValue = height + dy[i];

                if(yValue < 0 || yValue >= infoArr.length) continue;
                if(xValue < 0 || xValue >= infoArr[0].length) continue;

                char value = infoArr[yValue][xValue];

                if (value != '#') {
                    int next = current;

                    if (value == 'X') {
                        int key = infoArr[0].length * yValue + xValue;
                        Integer index = itemMap.get(key);
                        if (index == null) continue;
                        next |= (1 << index);
                    }

                    if (!visitArr[yValue][xValue][next]) {
                        visitArr[yValue][xValue][next] = true;
                        resultQueue.add(new int[]{yValue, xValue, next, pathCount + 1});
                    }
                }
            }
        }
    }
}