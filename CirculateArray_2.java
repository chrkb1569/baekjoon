import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class CirculateArray_2 {
    private static int[][] resultArr;
    private static int[][] commandArr;
    private static int minValue = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int N = Integer.parseInt(st.nextToken()); // height
        int M = Integer.parseInt(st.nextToken()); // width
        int K = Integer.parseInt(st.nextToken()); // circulation

        initResultArr(N, M);
        initCommandArr(K);

        for(int height = 0; height < N; height++) {
            st = new StringTokenizer(br.readLine(), " ");
            mkResultArr(st, height);
        }

        for(int command = 0; command < K; command++) {
            st = new StringTokenizer(br.readLine(), " ");
            mkCommandArr(st, command);
        }

        boolean[] visitArr = new boolean[K];
        int[] indexArr = new int[K];

        getPermutation(visitArr, indexArr, 0, K);

        System.out.println(minValue);
    }

    private static void initResultArr(int height, int width) {
        resultArr = new int[height][width];
    }

    private static void initCommandArr(int length) {
        commandArr = new int[length][3];
    }

    private static void mkResultArr(StringTokenizer st, int height) {
        int index = 0;

        for(;st.hasMoreTokens();) {
            resultArr[height][index++] = Integer.parseInt(st.nextToken());
        }
    }

    private static void mkCommandArr(StringTokenizer st, int height) {
        int index = 0;

        for(;st.hasMoreTokens();) {
            commandArr[height][index++] = Integer.parseInt(st.nextToken());
        }
    }

    private static void getPermutation(boolean[] visitArr, int[] indexArr, int current, int target) {
        if(current == target) {
            int result = processCommand(indexArr);
            minValue = Math.min(result, minValue);
            return;
        }

        for(int index = 0; index < visitArr.length; index++) {
            if(!visitArr[index]) {
                visitArr[index] = true;
                indexArr[current] = index;
                getPermutation(visitArr, indexArr, current + 1, target);
                visitArr[index] = false;
            }
        }
    }

    private static int processCommand(int[] arr) {
        int[][] copyArr = copyArr();

        for(int commandIndex : arr) {
            int[] commands = commandArr[commandIndex];

            int r = commands[0];
            int c = commands[1];
            int s = commands[2];

            changeStatus(copyArr, r, c, s);
        }

        return calculateValue(copyArr);
    }

    private static int[][] copyArr() {
        int[][] arr = new int[resultArr.length][resultArr[0].length];

        for(int height = 0; height < resultArr.length; height++) {
            for(int width = 0; width < resultArr[0].length; width++) {
                arr[height][width] = resultArr[height][width];
            }
        }

        return arr;
    }

    private static void changeStatus(int[][] arr, int r, int c, int s) {
        int height = r - 1;
        int width = c - 1;

        for(int length = 1; length <= s; length++) {
            circulate(arr, height - length, width - length, length);
        }
    }

    private static void circulate(int[][] arr, int h, int w, int length) {
        int totalLength = 2 * length + 1;

        int tmp = arr[h][w + totalLength - 1];

        for(int width = w + totalLength - 1; width > w; width--) {
            arr[h][width] = arr[h][width-1];
        }

        int bin = arr[h + totalLength - 1][w + totalLength - 1];

        for(int height = h + totalLength - 1; height > h; height--) {
            arr[height][w + totalLength - 1] = arr[height - 1][w + totalLength - 1];
        }

        arr[h + 1][w + totalLength - 1] = tmp;

        tmp = arr[h + totalLength - 1][w];

        for(int width = w; width < w + totalLength - 1; width++) {
            arr[h + totalLength - 1][width] = arr[h + totalLength - 1][width+1];
        }

        arr[h + totalLength - 1][w + totalLength - 2] = bin;

        for(int height = h; height < h + totalLength - 1; height++) {
            arr[height][w] = arr[height + 1][w];
        }

        arr[h + totalLength - 2][w] = tmp;
    }

    private static int calculateValue(int[][] arr) {
        int minValue = Integer.MAX_VALUE;

        for(int height = 0; height < arr.length; height++) {
            int sum = 0;

            for(int width = 0; width < arr[0].length; width++) {
                sum += arr[height][width];
            }

            minValue = Math.min(minValue, sum);
        }

        return minValue;
    }
}