import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class LIS_2 {
    private static int[] resultArr;
    private static int[] indexArr;
    private static int[] dpArr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine(), " ");

        initArr(N);
        mkResultArr(st);

        int length = getLISLength();

        printResult(length);
    }

    private static void initArr(int length) {
        resultArr = new int[length];
        dpArr = new int[length];
        indexArr = new int[length];

        Arrays.fill(dpArr, Integer.MIN_VALUE);
    }

    private static void mkResultArr(StringTokenizer st) {
        int index = 0;

        for(;st.hasMoreTokens();) {
            resultArr[index++] = Integer.parseInt(st.nextToken());
        }
    }

    private static int getLISLength() {
        int length = 0;

        for(int i = 0; i < resultArr.length; i++) {
            int value = resultArr[i];
            int index = binarySearch(value, 0, length, length + 1);

            if(index == -1) {
                indexArr[i] = length;
                dpArr[length] = value;
                length++;
                continue;
            }

            indexArr[i] = index;
            dpArr[index] = value;
        }

        return length;
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

        if(start == size) return -1;

        return index;
    }

    private static void printResult(int LISLength){
        StringBuilder sb = new StringBuilder();
        int[] finalArr = new int[LISLength];
        int index = finalArr.length - 1;
        int value = LISLength - 1;

        System.out.println(LISLength);

        for(int i = indexArr.length - 1; i >= 0; i--) {
            if(indexArr[i] == value) {
                value--;
                finalArr[index--] = resultArr[i];
            }
        }

        for(int resultValue : finalArr) {
            sb.append(resultValue).append(" ");
        }

        System.out.println(sb);
    }
}

// 6
// 71 0 -1 20 1 50