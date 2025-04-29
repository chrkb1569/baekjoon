import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class PlumTree {

    private static int[] plumArr;
    private static int[][][] resultArr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken()); // 자두가 떨어지는 시간
        int W = Integer.parseInt(st.nextToken()); // 자두가 움직일 수 있는 횟수

        initPlumArr(T);
        initResultArr(T, W);

        for(int plum = 0; plum < T; plum++) {
            int plumInfo = Integer.parseInt(br.readLine());

            plumArr[plum + 1] = plumInfo;
        }

        mkResultArr();

        System.out.println(getMaxValue());
    }

    private static void initPlumArr(int length) {
        plumArr = new int[length + 1];
    }

    private static void initResultArr(int height, int width) {
        resultArr = new int[height + 1][width + 1][3];
    }

    private static void mkResultArr() {
        int initialInfo = plumArr[1];

        if(initialInfo == 1) {
            resultArr[1][0][1] = 1;
            resultArr[1][1][2] = 0;
        }

        if(initialInfo == 2) {
            resultArr[1][0][1] = 0;
            resultArr[1][1][2] = 1;
        }

        for(int time = 2; time < resultArr.length; time++) {
            int plumInfo = plumArr[time];

            if(plumInfo == 1) {
                resultArr[time][0][1] = resultArr[time-1][0][1] + 1;
                resultArr[time][0][2] = resultArr[time-1][0][2];

                for(int weight = 1; weight < resultArr[0].length; weight++) {
                    resultArr[time][weight][1] = Math.max(resultArr[time-1][weight][1], resultArr[time-1][weight-1][2]) + 1;
                    resultArr[time][weight][2] = Math.max(resultArr[time-1][weight][2], resultArr[time-1][weight-1][1]);
                }

                continue;
            }

            resultArr[time][0][1] = resultArr[time-1][0][1];
            resultArr[time][0][2] = resultArr[time-1][0][2] + 1;

            for(int weight = 1; weight < resultArr[0].length; weight++) {
                resultArr[time][weight][1] = Math.max(resultArr[time-1][weight][1], resultArr[time-1][weight-1][2]);
                resultArr[time][weight][2] = Math.max(resultArr[time-1][weight][2], resultArr[time-1][weight-1][1]) + 1;
            }
        }
    }

    private static long getMaxValue() {
        long maxValue = Integer.MIN_VALUE;

        for(int width = 0; width < resultArr[0].length; width++) {
            maxValue = Math.max(maxValue, Math.max(resultArr[resultArr.length-1][width][1], resultArr[resultArr.length-1][width][2]));
        }

        return maxValue;
    }
}
