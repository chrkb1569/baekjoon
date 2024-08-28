import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class DragonCurve {
    private static boolean[][] resultArr = new boolean[101][101];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 사용자로부터 드래곤 커브의 개수 N을 입력받는다.
        int N = Integer.parseInt(br.readLine());

        // N개의 드래곤 커브 정보를 입력 받는다.
        // x y d g (x좌표, y좌표, 시작 방향, 세대)
        for(int info = 0; info < N; info++) {
            st = new StringTokenizer(br.readLine(), " ");

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int g = Integer.parseInt(st.nextToken());

            // 드래곤 커브의 정보를 입력 받은 뒤, 배열에 이를 반영한다.
            int[][] curveArr = curveArr(x, y, d, g);

            markResultArr(curveArr);
        }

        // 정보가 모두 반영된 배열의 정보를 토대로 정사각형의 개수를 구하여 반환한다.
        int squareCount = getSquareCount();

        // 이를 출력한다.
        System.out.println(squareCount);
    }

    private static int[][] curveArr(int x, int y, int direction, int generation) {
        List<int[]> pointList = new LinkedList<>();
        initPointList(pointList, x, y, direction);

        if(generation == 0) return convertToArray(pointList);

        for(int currentGeneration = 1; currentGeneration <= generation; currentGeneration++) {
            int[] stdPoint = pointList.get(pointList.size()-1); // 끝점
            int length = pointList.size() - 1;

            for(int index = length-1; index >= 0; index--) {
                int[] targetPoint = pointList.get(index);

                int[] circulatedPoint = circulatePoint(stdPoint, targetPoint);

                pointList.add(circulatedPoint);
            }
        }

        return convertToArray(pointList);
    }

    private static void initPointList(List<int[]> pointList, int x, int y, int direction) {
        pointList.add(new int[]{y, x});

        if(direction == 0) {
            pointList.add(new int[]{y, x+1});
            return;
        }

        if(direction == 1) {
            pointList.add(new int[]{y-1, x});
            return;
        }

        if(direction == 2) {
            pointList.add(new int[]{y, x-1});
            return;
        }

        pointList.add(new int[]{y+1, x});
    }

    // 특정 점을 기준으로 회전한 좌표를 반환
    private static int[] circulatePoint(int[] stdPoint, int[] targetPoint) {
        int stdX = stdPoint[1];
        int stdY = stdPoint[0];

        int targetX = targetPoint[1];
        int targetY = targetPoint[0];

        int resultY = stdY - stdX + targetX;
        int resultX = stdX + stdY - targetY;

        return new int[]{resultY, resultX};
    }

    private static int[][] convertToArray(List<int[]> resultList) {
        int[][] resultArr = new int[resultList.size()][2];

        for(int height = 0; height < resultList.size(); height++) {
            int[] valueArr = resultList.get(height);

            resultArr[height][0] = valueArr[0];
            resultArr[height][1] = valueArr[1];
        }

        return resultArr;
    }

    private static void markResultArr(int[][] curveArr) {
        for(int height = 0; height < curveArr.length; height++) {
            int y = curveArr[height][0];
            int x = curveArr[height][1];

            if(y < 0 || x < 0) continue;

            resultArr[y][x] = true;
        }
    }

    private static int getSquareCount() {
        int squareCount = 0;

        for(int height = 1; height < resultArr.length; height++) {
            for(int width = 1; width < resultArr.length; width++) {
                if(
                        resultArr[height][width] && resultArr[height-1][width] &&
                        resultArr[height][width-1] && resultArr[height-1][width-1]
                ) squareCount++;
            }
        }

        return squareCount;
    }
}
