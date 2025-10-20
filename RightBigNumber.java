import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class RightBigNumber {

    private static int[] rootArr;
    private static int[] resultArr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        initResultArr(N);
        initRootArr(N);

        mkResultArr(st);
        mkRootArr();

        printResult();
    }

    private static void initResultArr(int length) {
        resultArr = new int[length];
    }

    private static void initRootArr(int length) {
        rootArr = new int[length];

        Arrays.fill(rootArr, -1);
    }

    private static void mkResultArr(StringTokenizer st) {
        for(int index = 0; index < resultArr.length; index++) {
            resultArr[index] = Integer.parseInt(st.nextToken());
        }
    }

    private static void mkRootArr() {
        for(int index = resultArr.length - 2; index >= 0; index--) {
            int currentValue = resultArr[index];
            int preValue = resultArr[index + 1];

            if(currentValue < preValue) {
                rootArr[index] = index + 1;
                continue;
            }

            mkRoot(index, index + 1);
        }
    }

    private static void mkRoot(int currentIndex, int preIndex) {
        int rootIndex = rootArr[preIndex];

        if(rootIndex == -1) return;

        int currentValue = resultArr[currentIndex];
        int rootValue = resultArr[rootIndex];

        if(currentValue < rootValue) {
            rootArr[currentIndex] = rootArr[preIndex];
            return;
        }

        mkRoot(currentIndex, rootArr[preIndex]);
    }

    private static void printResult() {
        StringBuilder sb = new StringBuilder();

        for(int index : rootArr) {
            if(index == -1) sb.append(-1).append(" ");
            else sb.append(resultArr[index]).append(" ");
        }

        System.out.println(sb);
    }
}
