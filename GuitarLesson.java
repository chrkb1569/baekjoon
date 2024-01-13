import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class GuitarLesson {
    private static int[] resultArr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine(), " ");

        initResultArr(N);
        mkResultArr(st);

        int value = binarySearch(M);

        System.out.println(value);
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

    private static int binarySearch(int key) {
        int low = Arrays.stream(resultArr).max().getAsInt();
        int high = Arrays.stream(resultArr).sum();

        while(low < high) {
            int mid = (low + high)/2;
            int sum = 0;
            int count = 0;

            for(int i = 0; i < resultArr.length; i++) {
                if(sum + resultArr[i] > mid) {
                    sum = 0;
                    count++;
                }

                sum += resultArr[i];
            }

            if(sum > 0) count++;

            if(count <= key) {
                high = mid;
                continue;
            }

            low = mid + 1;
        }

        return low;
    }
}
