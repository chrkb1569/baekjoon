import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class DanceGame {
    private static int[] valueArr;
    private static int[][][] resultArr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        initValueArr(st.countTokens() - 1);
        initResultArr();
        mkValueArr(st);

        System.out.println(getResult(0, 0, 0));
    }

    private static void initValueArr(int length) {
        valueArr = new int[length];
    }

    private static void mkValueArr(StringTokenizer st) {
        for(int i = 0; i < valueArr.length; i++) {
            valueArr[i] = Integer.parseInt(st.nextToken());
        }
    }

    private static void initResultArr() {
        resultArr = new int[5][5][valueArr.length];

        for(int i = 0; i < 5; i++) {
            for(int j = 0; j < 5; j++) {
                Arrays.fill(resultArr[i][j], -1);
            }
        }
    }

    private static int getResult(int right, int left, int count) {
        if(count == valueArr.length) return 0;
        if(resultArr[right][left][count] != -1) return resultArr[right][left][count];

        int to = valueArr[count];

        resultArr[right][left][count] = Math.min(
                getResult(to, left, count + 1) + getWeight(right, to),
                getResult(right, to, count + 1) + getWeight(left, to)
        );

        return resultArr[right][left][count];
    }

    private static int getWeight(int from, int to) {
        if(from == to) return 1;
        if(from == 0) return 2;
        if(Math.abs(from - to) == 2) return 4;
        return 3;
    }
}
