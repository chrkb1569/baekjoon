import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class StackBlock {
    private static int[][] resultArr;
    private static int[][] dpArr;
    private static final int DIVIDER = 10_007;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int N = Integer.parseInt(st.nextToken()); // 학생의 수
        int M = Integer.parseInt(st.nextToken()); // 최대로 소지할 수 있는 블럭의 수
        int H = Integer.parseInt(st.nextToken()); // 만들고자하는 탑의 높이

        initResultArr(N, M);
        initDpArr(N, H);

        for(int height = 1; height < resultArr.length; height++) {
            st = new StringTokenizer(br.readLine(), " ");
            mkResultArr(st, height);
        }

        mkDpArr();

        System.out.println(dpArr[N][H]);
    }

    private static void initResultArr(int height, int width) {
        resultArr = new int[height + 1][width];
    }

    private static void initDpArr(int height, int width) {
        dpArr = new int[height + 1][width + 1];
    }

    private static void mkResultArr(StringTokenizer st, int height) {
        int width = 0;

        for(;st.hasMoreTokens();) {
            int block = Integer.parseInt(st.nextToken());

            if(block == 0) break;

            resultArr[height][width++] = block;
        }
    }

    private static void mkDpArr() {
        int[] firstPerson = resultArr[1];

        for(int value : firstPerson) {
            dpArr[1][value]++;
        }

        dpArr[1][0] = 1;

        for(int height = 2; height < dpArr.length; height++) {
            for(int width = 0; width < dpArr[0].length; width++) {
                int sumValue = 0;

                for(int index = 0; index < resultArr[height].length; index++) {
                    int value = resultArr[height][index];

                    if(value == 0) continue;
                    if(width - value < 0) continue;

                    sumValue += dpArr[height-1][width - value];
                }

                sumValue += dpArr[height - 1][width];

                dpArr[height][width] = (dpArr[height][width] + sumValue) % DIVIDER;
            }
        }
    }
}
