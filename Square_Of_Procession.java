import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Square_Of_Procession {
    private static final Map<Long, int[][]> resultMap = new HashMap<>();
    private static int[][] resultArr;
    private final static int DIVIDER = 1_000;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int N = Integer.parseInt(st.nextToken());
        long B = Long.parseLong(st.nextToken());

        initResultArr(N);

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            mkResultArr(st, i);
        }

        int[][] processionArr = getProcessionArr(B);

        printResult(processionArr);
    }

    private static void initResultArr(int N) {
        resultArr = new int[N][N];
    }

    private static void mkResultArr(StringTokenizer st, int height) {
        int width = 0;

        for(;st.hasMoreTokens();) {
            resultArr[height][width++] = Integer.parseInt(st.nextToken());
        }
    }

    private static int[][] getProcessionArr(long square) {
        if(square == 1) return resultArr;
        if(resultMap.containsKey(square)) return resultMap.get(square);

        int[][] processionArr = new int[resultArr.length][resultArr.length];

        if(square % 2 == 0) mkProcessionArr_Even(processionArr, square);
        else mkProcessionArr_Odd(processionArr, square);

        resultMap.put(square, processionArr);

        return processionArr;
    }

    private static void mkProcessionArr_Even(int[][] arr, long square) {
        int[][] halfArr = getProcessionArr(square/2);

        for(int height = 0; height < arr.length; height++) {
            for(int width = 0; width < arr.length; width++) {
                int value = 0;

                for(int k = 0; k < arr.length; k++) {
                    value += (halfArr[height][k] * halfArr[k][width]);
                }

                arr[height][width] = value % DIVIDER;
            }
        }
    }

    private static void mkProcessionArr_Odd(int[][] arr, long square) {
        int[][] halfArr = getProcessionArr(square/2);
        int[][] leftArr = resultArr;

        int[][] tmpArr = new int[resultArr.length][resultArr.length];

        for(int height = 0; height < arr.length; height++) {
            for(int width = 0; width < arr.length; width++) {
                int value = 0;

                for(int k = 0; k < arr.length; k++) {
                    value += (halfArr[height][k] * halfArr[k][width]);
                }

                tmpArr[height][width] = value % DIVIDER;
            }
        }

        for(int height = 0; height < arr.length; height++) {
            for(int width = 0; width < arr.length; width++) {
                int value = 0;

                for(int k = 0; k < arr.length; k++) {
                    value += (tmpArr[height][k] * leftArr[k][width]);
                }

                arr[height][width] = value % DIVIDER;
            }
        }
    }

    private static void printResult(int[][] arr) {
        StringBuilder sb = new StringBuilder();

        for(int[] subArr : arr) {
            for(int value : subArr) {
                sb.append(value % DIVIDER).append(" ");
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }
}