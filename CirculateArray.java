import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class CirculateArray {
    private static int[][] resultArr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int height = Integer.parseInt(st.nextToken());
        int width = Integer.parseInt(st.nextToken());
        int testCase = Integer.parseInt(st.nextToken());

        initResultArr(height, width);

        for(int i = 0; i < height; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            mkResultArr(st, i);
        }

        for(int i = 0; i < testCase; i++) {
            circulateArr();
        }

        printResult();
    }

    private static void initResultArr(int height, int width) {
        resultArr = new int[height][width];
    }

    private static void mkResultArr(StringTokenizer st, int height) {
        int index = 0;

        for(;st.hasMoreTokens();) {
            resultArr[height][index++] = Integer.parseInt(st.nextToken());
        }
    }

    private static void circulateArr() {
        int length = Math.min(resultArr.length, resultArr[0].length);
        int tmp = 0;
        int value = 0;

        for(int sub = 0; sub < length/2; sub++) {
            tmp = resultArr[sub][sub];

            for(int width = sub; width < resultArr[0].length - sub - 1; width++) {
                resultArr[sub][width] = resultArr[sub][width+1];
            }

            value = resultArr[resultArr.length-sub-1][sub];

            for(int height = resultArr.length - sub - 1; height > sub; height--) {
                resultArr[height][sub] = resultArr[height-1][sub];
            }

            resultArr[sub+1][sub] = tmp;
            tmp = resultArr[resultArr.length-sub-1][resultArr[0].length-sub-1];

            for(int width = resultArr[0].length - sub - 1; width > sub; width--) {
                resultArr[resultArr.length-sub-1][width] = resultArr[resultArr.length-sub-1][width-1];
            }

            resultArr[resultArr.length-sub-1][sub+1] = value;

            for(int height = sub; height < resultArr.length - sub - 1; height++) {
                resultArr[height][resultArr[0].length-sub-1] = resultArr[height+1][resultArr[0].length-sub-1];
            }

            resultArr[resultArr.length-sub-2][resultArr[0].length-sub-1] = tmp;
        }
    }

    private static void printResult() {
        StringBuilder sb = new StringBuilder();

        for(int[] arr : resultArr) {
            for(int value : arr) {
                sb.append(value).append(" ");
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }
}
