import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class LIS_4 {
    private static int[] resultArr;
    private static int[] valueArr;
    private static int[] indexArr;
    private static int size = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        initArr(N);

        StringTokenizer st = new StringTokenizer(br.readLine());

        mkResultArr(st);
        mkValueArr();

        printResult();
    }

    private static void initArr(int length) {
        resultArr = new int[length];
        valueArr = new int[length];
        indexArr = new int[length];
    }

    private static void mkResultArr(StringTokenizer st) {
        int index = 0;

        for(;st.hasMoreTokens();) {
            resultArr[index++] = Integer.parseInt(st.nextToken());
        }
    }

    private static void mkValueArr() {
        int size = 0;

        for(int index = 0; index < resultArr.length; index++) {
            int convertedIndex = binarySearch(resultArr[index], 0, size, size + 1);

            if(convertedIndex == -1) {
                indexArr[index] = size;
                valueArr[size++] = resultArr[index];
                continue;
            }

            valueArr[convertedIndex] = resultArr[index];
            indexArr[index] = convertedIndex;
        }
    }

    private static int binarySearch(int value, int start, int end, int size) {
        int index = 0;

        while(start <= end) {
            int mid = (start + end) / 2;

            if(value <= valueArr[mid]) {
                end = mid - 1;
                index = mid;
                continue;
            }

            start = mid + 1;
        }

        if(start == size) return -1;

        return index;
    }

    private static void printResult() {
        Stack<Integer> resultStack = new Stack<>();
        StringBuilder sb = new StringBuilder();

        for(int value : valueArr) {
            if(value != 0) size++;
        }

        sb.append(size).append("\n");

        for(int index = indexArr.length - 1; index >= 0; index--) {
            if(indexArr[index] == size - 1) {
                resultStack.push(resultArr[index]);
                size--;
            }
        }

        while(!resultStack.isEmpty()) {
            sb.append(resultStack.pop()).append(" ");
        }

        System.out.println(sb);
    }
}

// 13
//
//3 4 5 6 2 3 1 7 4 3 5 6 7