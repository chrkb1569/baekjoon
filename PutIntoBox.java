import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class PutIntoBox {
    private static int[] resultArr;
    private static int[] dpArr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine(), " ");

        initArr(N);
        mkResultArr(st);
        mkDpArr();

        printResult();
    }

    private static void initArr(int length) {
        resultArr = new int[length];
        dpArr = new int[length];
    }

    private static void mkResultArr(StringTokenizer st) {
        int index = 0;

        for(;st.hasMoreTokens();) {
            resultArr[index++] = Integer.parseInt(st.nextToken());
        }
    }

    private static void mkDpArr() {
        int length = 0;

        for(int i = 0; i < resultArr.length; i++) {
            int value = resultArr[i];

            int index = binarySearch(value, 0, length, length + 1);

            if(index == -1) {
                dpArr[length] = value;
                length++;
                continue;
            }

            dpArr[index] = value;
        }
    }

    private static int binarySearch(int value, int start, int end, int size) {
        int index = -1;

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

    private static void printResult() {
        int count = 0;

        for(int value : dpArr) {
            if(value != 0) count++;
        }

        System.out.println(count);
    }
}
