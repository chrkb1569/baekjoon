import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class LIS_3 {
    private static int[] resultArr;
    private static int[] dpArr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        initArr(N);
        mkResultArr(st);
        mkDpArr();

        int validateLength = getValidateLength();
        System.out.println(validateLength);
    }

    private static void initArr(int length) {
        resultArr = new int[length];
        dpArr = new int[length];
    }

    private static void mkResultArr(StringTokenizer st) {
        int indexValue = 0;

        for(;st.hasMoreTokens();) {
            resultArr[indexValue++] = Integer.parseInt(st.nextToken());
        }
    }

    private static void mkDpArr() {
        int length = 0;

        Arrays.fill(dpArr, Integer.MIN_VALUE);

        for(int i = 0; i < dpArr.length; i++) {
            int index = binarySearch(resultArr[i], 0, length, length + 1);

            if(index == -1) {
                dpArr[length++] = resultArr[i];
                continue;
            }
            dpArr[index] = resultArr[i];
        }
    }

    private static int binarySearch(int value, int start, int end, int size) {
        int index = 0;

        while(start <= end) {
            int mid = (start + end) / 2;

            if(value <= dpArr[mid]) {
                index = mid;
                end = mid - 1;
                continue;
            }

            start = mid + 1;
        }

        if(start == size) {
            return -1;
        }

        return index;
    }

    private static int getValidateLength() {
        int length = 0;

        for(int value : dpArr) {
            if(value != Integer.MIN_VALUE) length++;
        }

        return length;
    }
}
