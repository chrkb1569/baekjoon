import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class TSP {
    private static int[][] resultArr;
    private static int minWeight = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        initResultArr(N);

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            mkResultArr(st, i);
        }

        for (int i = 0; i < N; i++) {
            travel(i, i, 0, 0);
        }

        System.out.println(minWeight);
    }

    private static void initResultArr(int length) {
        resultArr = new int[length][length];
    }

    private static void mkResultArr(StringTokenizer st, int height) {
        int index = 0;

        for(;st.hasMoreTokens();) {
            resultArr[height][index++] = Integer.parseInt(st.nextToken());
        }
    }

    private static void travel(int start, int now, int visit, int cost) {
        if(cost > minWeight) return;

        if (visit == (1 << resultArr.length) - 1 && start == now) {
            minWeight = cost;
            return;
        }

        for (int i = 0; i < resultArr.length; i++) {
            if (resultArr[now][i] == 0 || (visit | (1 << i)) == visit) {
                continue;
            }

            travel(start, i, visit | (1 << i), cost + resultArr[now][i]);
        }
    }
}