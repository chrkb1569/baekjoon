import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SuGangGround {
    private static int[] itemArr;
    private static int[][] resultArr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int N = Integer.parseInt(st.nextToken()); // region
        int M = Integer.parseInt(st.nextToken()); // rangeLimit
        int R = Integer.parseInt(st.nextToken()); // path

        initItemArr(N);
        initResultArr(N);

        st = new StringTokenizer(br.readLine(), " ");
        mkItemArr(st);

        for(int path = 0; path < R; path++) {
            st = new StringTokenizer(br.readLine(), " ");

            int startNumber = Integer.parseInt(st.nextToken());
            int endNumber = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            resultArr[startNumber][endNumber] = weight;
            resultArr[endNumber][startNumber] = weight;
        }

        mkResultArr();

        System.out.println(getMaxItem(M));
    }

    private static void initItemArr(int length) {
        itemArr = new int[length];
    }

    private static void initResultArr(int length) {
        resultArr = new int[length + 1][length + 1];

        for(int height = 0; height < resultArr.length; height++) {
            Arrays.fill(resultArr[height], Integer.MAX_VALUE);
        }

        for(int height = 0; height < resultArr.length; height++) {
            resultArr[height][height] = 0;
        }
    }

    private static void mkItemArr(StringTokenizer st) {
        int index = 0;

        for(;st.hasMoreTokens();) {
            itemArr[index++] = Integer.parseInt(st.nextToken());
        }
    }

    private static void mkResultArr() {
        for(int via = 0; via < resultArr.length; via++) {
            for(int height = 0; height < resultArr.length; height++) {
                for(int width = 0; width < resultArr.length; width++) {
                    if(height == width || height == via || width == via) continue;
                    if(resultArr[height][via] == Integer.MAX_VALUE || resultArr[via][width] == Integer.MAX_VALUE) continue;

                    resultArr[height][width] = Math.min(resultArr[height][width],
                            resultArr[height][via] + resultArr[via][width]);
                }
            }
        }
    }

    private static int getMaxItem(int rangeLimit) {
        int maxItem = Integer.MIN_VALUE;

        for(int height = 1; height < resultArr.length; height++) {
            int sum = 0;

            for(int width = 1; width < resultArr.length; width++) {
                if(resultArr[height][width] <= rangeLimit) sum += itemArr[width-1];
            }

            maxItem = Math.max(maxItem, sum);
        }

        return maxItem;
    }
}
