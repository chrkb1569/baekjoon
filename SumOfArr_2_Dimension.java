import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SumOfArr_2_Dimension {
    private static int[][] resultArr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        StringBuffer sb = new StringBuffer();

        int N = Integer.parseInt(st.nextToken()); // height
        int M = Integer.parseInt(st.nextToken()); // width

        initResultArr(N, M);

        for(int height = 1; height <= N; height++) {
            st = new StringTokenizer(br.readLine(), " ");
            mkResultArr(st, height);
        }

        int K = Integer.parseInt(br.readLine()); // count

        for(int count = 0; count < K; count++) {
            st = new StringTokenizer(br.readLine(), " ");

            int i = Integer.parseInt(st.nextToken()); // height
            int j = Integer.parseInt(st.nextToken()); // width

            int x = Integer.parseInt(st.nextToken()); // height
            int y = Integer.parseInt(st.nextToken()); // width

            sb.append(getArraySum(i, j, x, y)).append("\n");
        }

        System.out.println(sb);
    }

    private static void initResultArr(int height, int width) {
        resultArr = new int[height + 1][width + 1];
    }

    private static void mkResultArr(StringTokenizer st, int height) {
        int index = 1;

        for(;st.hasMoreTokens();) {
            resultArr[height][index] = Integer.parseInt(st.nextToken()) + resultArr[height-1][index] + resultArr[height][index-1] - resultArr[height-1][index-1];
            index++;
        }
    }

    private static int getArraySum(int y1, int x1, int y2, int x2) {
        return resultArr[y2][x2] - resultArr[y2][x1-1] - resultArr[y1-1][x2] + resultArr[y1-1][x1-1];
    }
}
