import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class LIS_1 {

    public static int[] resultArr;
    public static int[] dpArr;
    public static int maxValue = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int array = Integer.parseInt(br.readLine());

        initResultArr(array);
        initDpArr(array);

        st = new StringTokenizer(br.readLine(), " ");
        mkResultArr(st);

        mkDpArr();

        System.out.println(maxValue);
    }

    public static void mkDpArr() {
        for(int i = 0; i < resultArr.length; i++) {
            dpArr[i] = 1;

            for(int j = 0; j < i; j++) {
                if (resultArr[i] > resultArr[j]) dpArr[i] = Math.max(dpArr[i], dpArr[j] + 1);
            }

            maxValue = Math.max(dpArr[i], maxValue);
        }
    }

    public static void mkResultArr(StringTokenizer st) {
        int numValue = 0;

        for(;st.hasMoreTokens();) {
            resultArr[numValue++] = Integer.parseInt(st.nextToken());
        }
    }

    public static void initDpArr(int value) {
        dpArr = new int[value];
    }

    public static void initResultArr(int value) {
        resultArr = new int[value];
    }
}
