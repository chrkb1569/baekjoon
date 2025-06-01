import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class ListOfUniqueNumbers {

    private static int[] resultArr;

    private static long UNIQUE_COUNT = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        initResultArr(N);

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        mkResultArr(st);

        increaseUniqueCount();

        System.out.println(UNIQUE_COUNT);
    }

    private static void initResultArr(int length) {
        resultArr = new int[length];
    }

    private static void mkResultArr(StringTokenizer st) {
        int index = 0;

        for(;st.hasMoreTokens();) {
            resultArr[index++] = Integer.parseInt(st.nextToken());
        }
    }

    private static void increaseUniqueCount() {
        boolean[] visitArr = new boolean[100_001];
        int end = 0;

        for(int start = 0; start < resultArr.length; start++) {
            int startValue = resultArr[start];

            while(end < resultArr.length) {
                int endValue = resultArr[end];

                if(visitArr[endValue]) break;

                visitArr[endValue] = true;
                end++;
            }

            UNIQUE_COUNT += (end - start);
            visitArr[startValue] = false;
        }
    }
}
