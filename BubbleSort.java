import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;

public class BubbleSort {

    private static int[][] resultArr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        initResultArr(N);

        for(int index = 0; index < N; index++) {
            int value = Integer.parseInt(br.readLine());

            resultArr[index][0] = value;
            resultArr[index][1] = index;
        }

        Arrays.sort(resultArr, new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                return Integer.compare(a[0], b[0]);
            }
        });

        int loopCount = getLoopCount();

        System.out.println(loopCount);
    }

    private static void initResultArr(int length) {
        resultArr = new int[length][2];
    }

    private static int getLoopCount() {
        int loopCount = 0;

        for(int index = 0; index < resultArr.length; index++) {
            int[] infoArr = resultArr[index];

            int preIndex = infoArr[1];

            loopCount = Math.max(loopCount, preIndex - index + 1);
        }

        return loopCount;
    }
}
