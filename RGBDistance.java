import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class RGBDistance {
    private static int[][] resultArr;
    private static int[][] dpArr;
    private static int minValue = Integer.MAX_VALUE;
    private static int MAX_VALUE = 1_000;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int height = Integer.parseInt(br.readLine());

        initResultArr(height);

        for(int i = 0; i < height; i++) {
            st = new StringTokenizer(br.readLine(), " ");

            resultArr[i][0] = Integer.parseInt(st.nextToken());
            resultArr[i][1] = Integer.parseInt(st.nextToken());
            resultArr[i][2] = Integer.parseInt(st.nextToken());
        }

        for(int i = 0; i < 3; i++) {
            initDpArr(resultArr.length);
            mkDpArr(i);
        }

        System.out.println(minValue);
    }

    private static void initResultArr(int height) {
        resultArr = new int[height][3];
    }

    private static void initDpArr(int height) {
        dpArr = new int[height][3];
    }

    private static void mkDpArr(int startIndex) {
        for(int i = 0; i < 3; i++) {
            if(i == startIndex) {
                dpArr[0][i] = resultArr[0][i];
                continue;
            }
            dpArr[0][i] = MAX_VALUE;
        }

        for(int i = 1; i < resultArr.length; i++) {
            dpArr[i][0] += (Math.min(dpArr[i-1][1], dpArr[i-1][2]) + resultArr[i][0]);
            dpArr[i][1] += (Math.min(dpArr[i-1][0], dpArr[i-1][2]) + resultArr[i][1]);
            dpArr[i][2] += (Math.min(dpArr[i-1][0], dpArr[i-1][1]) + resultArr[i][2]);
        }

        for(int i = 0; i < 3; i++) {
            if(i == startIndex) continue;
            minValue = Math.min(minValue, dpArr[dpArr.length-1][i]);
        }
    }

    private static int getMinResult() {
        return Math.min(
                Math.min(dpArr[resultArr.length-1][0], dpArr[resultArr.length-1][1]),
                dpArr[resultArr.length-1][2]);
    }
}
