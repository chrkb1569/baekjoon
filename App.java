import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class App {
    private static int[] memoryArr;
    private static int[] costArr;
    private static int[][] resultArr;
    private static int minCost = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int length = Integer.parseInt(st.nextToken());
        int memory = Integer.parseInt(st.nextToken());

        initMemoryArr(length);
        initCostArr(length);
        initResultArr(length);

        st = new StringTokenizer(br.readLine(), " ");
        mkMemoryArr(st);

        st = new StringTokenizer(br.readLine(), " ");
        mkCostArr(st);

        mkResultArr(memory);

        System.out.println(minCost);
    }

    private static void initMemoryArr(int length) {
        memoryArr = new int[length];
    }

    private static void initCostArr(int length) {
        costArr = new int[length];
    }

    private static void initResultArr(int height) {
        resultArr = new int[height][10_001];
    }

    private static void mkMemoryArr(StringTokenizer st) {
        int index = 0;

        for(;st.hasMoreTokens();) {
            memoryArr[index++] = Integer.parseInt(st.nextToken());
        }
    }

    private static void mkCostArr(StringTokenizer st) {
        int index = 0;

        for(;st.hasMoreTokens();) {
            costArr[index++] = Integer.parseInt(st.nextToken());
        }
    }

    private static void mkResultArr(int reqMemory) {
        for(int i = 0; i < resultArr.length; i++) {
            int memory = memoryArr[i];
            int cost = costArr[i];

            for(int j = 0; j < resultArr[0].length; j++) {
                if(i == 0 && j >= cost) resultArr[i][j] = memory;
                if(i != 0 && j >= cost) resultArr[i][j] = Math.max(resultArr[i-1][j-cost] + memory, resultArr[i-1][j]);
                if(i != 0 && j < cost) resultArr[i][j] = resultArr[i-1][j];

                if(resultArr[i][j] >= reqMemory) minCost = Math.min(minCost, j);
            }
        }
    }
}
