import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Stock {
    private static int[] resultArr;
    private static int[] valueArr;
    private final static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int testCase = Integer.parseInt(br.readLine());

        for(int i = 0; i < testCase; i++) {
            String[] infoArr = br.readLine().split(" ");
            String[] values = br.readLine().split(" ");

            int length = Integer.parseInt(infoArr[0]);
            int K = Integer.parseInt(infoArr[1]);

            initArr(length);
            mkResultArr(values);

            int resultLength = mkValueArr();

            printResult(i, K, resultLength);
        }

        System.out.println(sb);
    }

    public static void initArr(int length) {
        resultArr = new int[length];
        valueArr = new int[length];
    }

    public static void mkResultArr(String[] infoArr) {
        for(int i = 0; i < resultArr.length; i++) {
            resultArr[i] = Integer.parseInt(infoArr[i]);
        }
    }

    public static int mkValueArr() {
        int length = 0;

        for(int i = 0; i < resultArr.length; i++) {
            int value = resultArr[i];

            int index = binarySearch(value, 0, length, length + 1);

            if(index == -1) {
                valueArr[length++] = value;
                continue;
            }

            valueArr[index] = value;
        }

        return length;
    }

    public static int binarySearch(int value, int start, int end, int size) {
        int index = 0;

        while(start <= end) {
            int mid = (start + end) / 2;

            if(value <= valueArr[mid]) {
                index = mid;
                end = mid - 1;
                continue;
            }
            start = mid + 1;
        }

        if(start == size) return -1;

        return index;
    }

    public static void printResult(int testCase, int K, int result) {
        sb.append("Case #").append(testCase + 1).append("\n");

        if(K <= result) sb.append(1).append("\n");
        else sb.append(0).append("\n");
    }
}
