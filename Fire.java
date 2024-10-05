import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Fire {
    private static char[][] resultArr;
    private static boolean[][] visitArr;
    private static Queue<int[]> startPoint = new LinkedList<>();
    private static Queue<int[]> firePoint = new LinkedList<>();
    private static final int[] dx = {1, -1, 0, 0};
    private static final int[] dy = {0, 0, 1, -1};
    private static final char STRING_WALL = '#';
    private static final char STRING_START_POINT = '@';
    private static final char STRING_FIRE = '*';
    private static final String STRING_IMPOSSIBLE = "IMPOSSIBLE";
    private static boolean flag = false;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuffer sb = new StringBuffer();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine()); // 테스트 케이스

        for(int testCase = 0; testCase < T; testCase++) {
            st = new StringTokenizer(br.readLine(), " ");

            int w = Integer.parseInt(st.nextToken()); // 너비
            int h = Integer.parseInt(st.nextToken()); // 높이

            initArr(h, w); // 배열 초기화
            initPoint(); // Queue 초기화

            // 배열 값 저장
            for(int height = 0; height < h; height++) {
                String maze = br.readLine();
                mkResultArr(height, maze);
            }

            int bfsResult = getBFSResult();

            mkResultString(sb, bfsResult);
        }

        System.out.println(sb);
    }

    private static void initArr(int height, int width) {
        resultArr = new char[height][width];
        visitArr = new boolean[height][width];
    }

    private static void initPoint() {
        startPoint.clear();
        firePoint.clear();
    }

    private static void mkResultArr(int height, String maze) {
        char[] cnvrtArr = maze.toCharArray();

        for(int index = 0; index < cnvrtArr.length; index++) {
            char pointValue = cnvrtArr[index];

            resultArr[height][index] = cnvrtArr[index];

            if(pointValue == STRING_START_POINT) startPoint.add(new int[]{height, index});
            if(pointValue == STRING_FIRE) firePoint.add(new int[]{height, index});
        }
    }

    private static int getBFSResult() {
        int mazeCount = 0;
        flag = false;

        while(true) {
            mazeCount++;

            makeFire();
            movePerson();

            if(flag) return mazeCount - 1;
            if(startPoint.isEmpty()) break;
        }

        return -1;
    }

    private static void makeFire() {
        if(firePoint.isEmpty()) return;

        int firePlace = firePoint.size();

        for(int fire = 0; fire < firePlace; fire++) {
            int[] fireIndex = firePoint.poll();

            int y = fireIndex[0];
            int x = fireIndex[1];

            for(int i = 0; i < 4; i++) {
                int xValue = x + dx[i];
                int yValue = y + dy[i];

                if(xValue < 0 || yValue < 0 || xValue >= resultArr[0].length || yValue >= resultArr.length) continue;

                char nextSpot = resultArr[yValue][xValue];

                if(nextSpot == STRING_FIRE || nextSpot == STRING_WALL) continue;

                resultArr[yValue][xValue] = STRING_FIRE;
                firePoint.add(new int[]{yValue, xValue});
            }
        }
    }

    private static void movePerson() {
        if(startPoint.isEmpty()) return;

        int personCount = startPoint.size();

        for(int count = 0; count < personCount; count++) {
            int[] startIndex = startPoint.poll();

            int y = startIndex[0];
            int x = startIndex[1];

            if(x < 0 || y < 0 || x >= resultArr[0].length || y >= resultArr.length) {
                flag = true;
                break;
            }

            for(int i = 0; i < 4; i++) {
                int xValue = x + dx[i];
                int yValue = y + dy[i];

                if(xValue < 0 || yValue < 0 || xValue >= resultArr[0].length || yValue >= resultArr.length) {
                    startPoint.add(new int[]{yValue, xValue});
                    break;
                }

                char nextSpot = resultArr[yValue][xValue];

                if(nextSpot == STRING_FIRE || nextSpot == STRING_WALL) continue;
                if(visitArr[yValue][xValue]) continue;

                visitArr[yValue][xValue] = true;
                startPoint.add(new int[]{yValue, xValue});
            }
        }
    }

    private static void mkResultString(StringBuffer sb, int bfsResult) {
        if(bfsResult == -1) {
            sb.append(STRING_IMPOSSIBLE).append("\n");
            return;
        }

        sb.append(bfsResult).append("\n");
    }
}