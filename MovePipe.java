import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class MovePipe {
    private static int[][] valueArr;
    private static int pathCount = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        initValueArr(N);

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            mkValueArr(st, i);
        }

        findResult(1, 0, 0);

        System.out.println(pathCount);
    }

    private static void initValueArr(int length) {
        valueArr = new int[length][length];
    }

    private static void mkValueArr(StringTokenizer st, int height) {
        int index = 0;

        for(;st.hasMoreTokens();) {
            valueArr[height][index++] = Integer.parseInt(st.nextToken());
        }
    }

    private static void findResult(int x, int y, int direction) {
        if(x == valueArr.length - 1 && y == valueArr.length - 1) {
            pathCount++;
            return;
        }

        if(direction == 0) {
            if(rightValidation(x, y)) findResult(x + 1, y, 0);
            if(crossValidation(x, y)) findResult(x + 1, y + 1, 2);
            return;
        }

        if(direction == 1) {
            if(downValidation(x, y)) findResult(x, y + 1, 1);
            if(crossValidation(x, y)) findResult(x + 1, y + 1, 2);
            return;
        }

        if(rightValidation(x, y)) findResult(x + 1, y, 0);
        if(downValidation(x, y)) findResult(x, y + 1, 1);
        if(crossValidation(x, y)) findResult(x + 1, y + 1, 2);
    }

    private static boolean rightValidation(int x, int y) {
        if(x + 1 >= valueArr.length) return false;
        return valueArr[y][x + 1] != 1;
    }

    private static boolean downValidation(int x, int y) {
        if(y + 1 >= valueArr.length) return false;
        return valueArr[y + 1][x] != 1;
    }

    private static boolean crossValidation(int x, int y) {
        if(x + 1 >= valueArr.length || y + 1 >= valueArr.length) return false;
        return valueArr[y][x + 1] != 1 && valueArr[y + 1][x] != 1 && valueArr[y + 1][x + 1] != 1;
    }

    // 0 - 가로, 1 - 세로, 2 - 대각선
    // 가로
    // 가로, 대각선
    // 새로
    // 새로, 대각선
    // 대각선
    // 가로, 세로, 대각선
}