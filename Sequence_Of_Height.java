import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Sequence_Of_Height {
    private static boolean[][] resultArr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int N = Integer.parseInt(st.nextToken()); // student
        int M = Integer.parseInt(st.nextToken()); // compare

        initResultArr(N);

        for(int testCase = 0; testCase < M; testCase++) {
            st = new StringTokenizer(br.readLine(), " ");

            // a b -> a는 b보다 작다.
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            resultArr[a][b] = true;
        }

        floyd();

        int count = getCount();

        System.out.println(count);
    }

    private static void initResultArr(int length) {
        resultArr = new boolean[length + 1][length + 1];
    }

    private static void floyd() {
        for(int tmp = 1; tmp < resultArr.length; tmp++) {
            for(int height = 1; height < resultArr.length; height++) {
                for(int width = 1; width < resultArr.length; width++) {
                    if(height == tmp || width == tmp) continue;
                    if(!resultArr[height][tmp] || !resultArr[tmp][width]) continue;

                    resultArr[height][width] = true;
                }
            }
        }
    }

    private static int getCount() {
        int[] countArr = new int[resultArr.length];
        int count = 0;

        for(int height = 1; height < resultArr.length; height++) {
            for(int width = 1; width < resultArr.length; width++) {
                if(resultArr[height][width] || resultArr[width][height]) countArr[height]++;
            }
        }

        for(int value : countArr) {
            if(value == resultArr.length - 2) count++;
        }

        return count;
    }
}
