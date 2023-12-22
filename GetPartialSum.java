import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class GetPartialSum {
    private static int[][] resultArr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(st.nextToken());
        int point = Integer.parseInt(st.nextToken());

        initResultArr(N);

        for(int i = 0; i < resultArr.length; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            mkResultArr(st, i);
        }

        changeValueOfResultArr();

        for(int i = 0; i < point; i++) {
            st = new StringTokenizer(br.readLine(), " ");

            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());

            int partialSum = getPartialSum(y1, x1, y2, x2);

            sb.append(partialSum).append("\n");
        }

        System.out.println(sb);
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

    private static void changeValueOfResultArr() {
        for(int i = 0; i < resultArr.length; i++) {
            for(int j = 1; j < resultArr[0].length; j++) {
                resultArr[i][j] += resultArr[i][j-1];
            }
        }
    }

    private static int getPartialSum(int x1, int y1, int x2, int y2) {
        int partialSum = 0;

        for(int height = y1 - 1; height < y2; height++) {
            if(x1 == 1) {
                partialSum += resultArr[height][x2 - 1];
                continue;
            }
            partialSum += (resultArr[height][x2 - 1] - resultArr[height][x1 - 2]);
        }

        return partialSum;
    }
}
