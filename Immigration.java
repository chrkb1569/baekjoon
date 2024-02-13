import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Immigration {
    private static long[] valueArr;
    private static long MAX_VALUE = 0;
    private static long MIN_TIME = Long.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        initValueArr(N);

        for(int i = 0; i < N; i++) {
            long value = Long.parseLong(br.readLine());
            valueArr[i] = value;
            MAX_VALUE = Math.max(MAX_VALUE, value);
        }

        Arrays.sort(valueArr);
        binarySearch(M);

        System.out.println(MIN_TIME);
    }

    private static void initValueArr(int length) {
        valueArr = new long[length];
    }

    private static void binarySearch(int totalPerson) {
        long low = 0L;
        long high = totalPerson * MAX_VALUE;

        while(low <= high) {
            long time = (low + high) / 2;
            long person = 0;

            for(int i = 0; i < valueArr.length; i++) {
                person += (time / valueArr[i]);

                if(person > totalPerson) break;
            }

            if(totalPerson <= person) {
                MIN_TIME = Math.min(MIN_TIME, time);
                high = time - 1;
                continue;
            }

            low = time + 1;
        }
    }
}
