import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class DivideSum {
    private static int[][] resultArr;
    private final static int DIVIDER = 1_000_000_000;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        initResultArr(K, N);
        mkResultArr();

        System.out.println(resultArr[K][N]);
    }

    private static void initResultArr(int height, int width) {
        resultArr = new int[height + 1][width + 1];

        for(int index = 0; index < resultArr.length; index++) {
            resultArr[index][0] = 1;
        }
    }

    private static void mkResultArr() {
        for(int height = 1; height < resultArr.length; height++) {
            for(int width = 1; width < resultArr[0].length; width++) {
                resultArr[height][width] = (resultArr[height-1][width] + resultArr[height][width-1]) % DIVIDER;
            }
        }
    }
}