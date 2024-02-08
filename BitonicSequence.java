import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BitonicSequence {
    private static int[] valueArr;
    private static int[] ascArr;
    private static int[] descArr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        initArr(N);

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        mkValueArr(st);
        mkAscArr();
        mkDescArr();

        printResult();
    }

    private static void initArr(int length) {
        valueArr = new int[length];
        ascArr = new int[length];
        descArr = new int[length];
    }

    private static void mkValueArr(StringTokenizer st) {
        int index = 0;

        for(;st.hasMoreTokens();) {
            valueArr[index++] = Integer.parseInt(st.nextToken());
        }
    }

    private static void mkAscArr() {
        for(int i = 0; i < ascArr.length; i++) {
            ascArr[i] = 1;

            for(int j = 0; j < i; j++) {
                if(valueArr[j] < valueArr[i] && ascArr[i] < ascArr[j] + 1) {
                    ascArr[i] = ascArr[j] + 1;
                }
            }
        }
    }

    private static void mkDescArr() {
        for(int i = descArr.length - 1; i >= 0; i--) {
            descArr[i] = 1;

            for(int j = descArr.length - 1; j > i; j--) {
                if(valueArr[j] < valueArr[i] && descArr[i] < descArr[j] + 1) {
                    descArr[i] = descArr[j] + 1;
                }
            }
        }
    }

    private static void printResult() {
        int maxValue = Integer.MIN_VALUE;

        for(int i = 0; i < valueArr.length; i++) {
            maxValue = Math.max(maxValue, ascArr[i] + descArr[i]);
        }

        System.out.println(maxValue - 1);
    }
}
