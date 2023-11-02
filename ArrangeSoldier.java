import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ArrangeSoldier {
    private static int[] resultArr;
    private static int[] dpArr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int length = Integer.parseInt(br.readLine());
        String[] infoArr = br.readLine().split(" ");

        initArr(length);
        mkResultArr(infoArr);
        mkDpArr();

        printResult();
    }

    public static void initArr(int length) {
        resultArr = new int[length];
        dpArr = new int[length];
    }

    public static void mkResultArr(String[] infoArr) {
        for(int i = 0; i < infoArr.length; i++) {
            resultArr[i] = Integer.parseInt(infoArr[i]);
        }
    }

    public static void mkDpArr() {
        int length = 0;

        for(int i = 0; i < dpArr.length; i++) {
            int index = binarySearch(resultArr[i], 0, length, length + 1);

            if(index == -1) {
                length++;
                dpArr[length] = resultArr[i];
                continue;
            }
            dpArr[index] = resultArr[i];
        }
    }

    public static int binarySearch(int value, int start, int end, int size) {
        int index = 0;

        while(start <= end) {
            int mid = (start + end) / 2;

            if(value >= dpArr[mid]) {
                index = mid;
                end = mid - 1;
                continue;
            }

            start = mid + 1;
        }

        if(start == size) return -1;

        return index;
    }

    public static void printResult() {
        int resultCount = 0;

        for(int value : dpArr) {
            if(value == 0) resultCount++;
        }

        System.out.println(resultCount);
    }
}
