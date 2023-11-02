import java.io.BufferedReader;
import java.io.InputStreamReader;

public class LIS_3 {
    public static long[] resultArr;
    public static long[] dpArr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        initArr(N);

        String[] infoArr = br.readLine().split(" ");

        mkResultArr(infoArr);
        System.out.println(getDpResult());
    }

    public static void initArr(int length) {
        resultArr = new long[length];
        dpArr = new long[length];
    }

    public static void mkResultArr(String[] infoArr) {
        for(int i = 0; i < resultArr.length; i++) {
            resultArr[i] = Integer.parseInt(infoArr[i]);
        }
    }

    public static int getDpResult() {
        int length = 0;

        for(int i = 0; i < dpArr.length; i++) {
            int index = binarySearch(resultArr[i], 0, length, length + 1);

            if(index == -1) {
                dpArr[length++] = resultArr[i];
                continue;
            }
            dpArr[index] = resultArr[i];
        }

        return length == 0? 1 : length;
    }

    public static int binarySearch(long value, int start, int end, int size) {
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

        if(start == size) return -1;

        return index;
    }
}
