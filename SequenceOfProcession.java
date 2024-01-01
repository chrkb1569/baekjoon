import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SequenceOfProcession {
    private static int[][] valueArr;
    private static int[][] resultArr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int length = Integer.parseInt(br.readLine());

        initValueArr(length);
        initResultArr(length);

        for(int i = 0; i < length; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            mkValueArr(st, i);
        }

        mkResult();

        System.out.println(resultArr[0][length-1]);
    }

    private static void initValueArr(int length) {
        valueArr = new int[length][2];
    }

    private static void initResultArr(int length) {
        resultArr = new int[length][length];
    }

    private static void mkValueArr(StringTokenizer st, int height) {
        int heightValue = Integer.parseInt(st.nextToken());
        int widthValue = Integer.parseInt(st.nextToken());

        valueArr[height][0] = heightValue;
        valueArr[height][1] = widthValue;
    }

    private static void mkResult() {
        for(int i = 1; i < resultArr.length; i++) {
            for(int j = 0; j < resultArr.length - i; j++) {
                resultArr[j][j+i] = Integer.MAX_VALUE;

                for(int k = 0; k < i; k++) {
                    resultArr[j][j+i] = Math.min(resultArr[j][j+i],
                            resultArr[j][j+k] + resultArr[j+k+1][j+i] +
                            valueArr[j][0] * valueArr[j+k][1] * valueArr[j+i][1]);
                }
            }
        }
    }
}
