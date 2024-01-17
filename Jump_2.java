import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Jump_2 {
    private static int[][] resultArr;
    private static long[][] dpArr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        initResultArr(N);
        initDpArr(N);

        for(int i = 0; i < resultArr.length; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            mkResultArr(st, i);
        }

        long pathCount = getPathCount(0, 0);

        System.out.println(pathCount);
    }

    private static void initResultArr(int length) {
        resultArr = new int[length][length];
    }

    private static void initDpArr(int length) {
        dpArr = new long[length][length];

        for(int i = 0; i < resultArr.length; i++) {
            Arrays.fill(dpArr[i], Long.MAX_VALUE/2);
        }
    }

    private static void mkResultArr(StringTokenizer st, int height) {
        int index = 0;

        for(;st.hasMoreTokens();) {
            resultArr[height][index++] = Integer.parseInt(st.nextToken());
        }
    }

    private static long getPathCount(int x, int y) {
        if(x == resultArr.length - 1 && y == resultArr.length - 1) return 1L;
        if(resultArr[y][x] == 0) return 0;
        if(dpArr[y][x] != Long.MAX_VALUE/2) return dpArr[y][x];

        int distance = resultArr[y][x];
        long toRight = 0;
        long toDown = 0;

        if(x + distance < resultArr.length) toRight = getPathCount(x + distance, y);
        if(y + distance < resultArr.length) toDown = getPathCount(x, y + distance);

        dpArr[y][x] = toRight + toDown;

        return dpArr[y][x];
    }
}
