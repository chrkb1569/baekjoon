import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class TSP_2 {
    private static int[][] resultArr;
    private static int[][] dpArr;
    private static int maxValue = 16_000_001;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        initResultArr(N);
        initDpArr(N);

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            mkResultArr(st, i);
        }

        System.out.println(travel(0, 1));
    }

    private static void initResultArr(int N) {
        resultArr = new int[N][N];
    }

    private static void initDpArr(int N) {
        dpArr = new int[N][1<<N];

        for(int i = 0; i < dpArr.length; i++) {
            Arrays.fill(dpArr[i], -1);
        }
    }

    private static void mkResultArr(StringTokenizer st, int height) {
        int index = 0;

        for(;st.hasMoreTokens();) {
            resultArr[height][index++] = Integer.parseInt(st.nextToken());
        }
    }

    private static int travel(int now, int visit) {
        if(visit == (1 << resultArr.length) - 1) {
            if(resultArr[now][0] == 0) return maxValue;
            return resultArr[now][0];
        }

        if(dpArr[now][visit] != -1) return dpArr[now][visit];

        dpArr[now][visit] = maxValue;

        for(int i = 0; i < resultArr.length; i++) {
            if(resultArr[now][i] == 0) continue;
            if((visit | (1 << i)) == visit) continue;

            dpArr[now][visit] = Math.min(dpArr[now][visit], travel(i, visit | (1 << i)) + resultArr[now][i]);
        }

        return dpArr[now][visit];
    }
}
