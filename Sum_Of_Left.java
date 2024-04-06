import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Sum_Of_Left {
    private static long[] resultArr; // 누적합을 저장하기 위한 배열
    private static long[] leftArr; // 나머지 값들을 저장하기 위한 배열
    private static long count = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int N = Integer.parseInt(st.nextToken()); // numbers
        int M = Integer.parseInt(st.nextToken()); // divider

        st = new StringTokenizer(br.readLine(), " ");

        initResultArr(N);
        initLeftArr(M);

        mkResultArr(st, M);
        mkLeftArr();

        addCount();

        System.out.println(count);
    }

    private static void initResultArr(int length) {
        resultArr = new long[length + 1];
    }

    private static void initLeftArr(int length) {
        leftArr = new long[length];
    }

    private static void mkResultArr(StringTokenizer st, int divider) {
        int index = 1;

        for(;st.hasMoreTokens();) {
            int value = Integer.parseInt(st.nextToken());

            resultArr[index] = (resultArr[index - 1] + value) % divider;
            index++;
        }
    }

    private static void mkLeftArr() {
        for(int index = 1; index < resultArr.length; index++) {
            if(resultArr[index] == 0) count++;
            leftArr[(int)resultArr[index]]++;
        }
    }

    private static void addCount() {
        for(int index = 0; index < leftArr.length; index++) {
            if(leftArr[index] < 1) continue;

            count += (leftArr[index] * (leftArr[index] - 1))/2;
        }
    }
}