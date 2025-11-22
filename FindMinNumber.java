import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class FindMinNumber {

    private static int[] resultArr;

    private final static ArrayDeque<int[]> resultDeque = new ArrayDeque<>();
    private final static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int N = Integer.parseInt(st.nextToken()); // length 1 <= N <= 5000000
        int L = Integer.parseInt(st.nextToken()); // windowSize 1 <= L <= N

        st = new StringTokenizer(br.readLine(), " ");

        initResultArr(N);
        mkResultArr(st);

        mkResultString(L);

        System.out.println(sb);
    }

    private static void initResultArr(int length) {
        resultArr = new int[length + 1];
    }

    private static void mkResultArr(StringTokenizer st) {
        for(int index = 1; index < resultArr.length; index++) {
            int value = Integer.parseInt(st.nextToken());
            resultArr[index] = value;
        }
    }

    // {index, value}
    private static void mkResultString(int windowSize) {
        for(int index = 1; index < resultArr.length; index++) {
            int value = resultArr[index];
            int[] element = new int[]{index, value};

            while(!resultDeque.isEmpty()) {
                int[] lastInfo = resultDeque.peekLast();
                int lastValue = lastInfo[1];

                if(lastValue > value) {
                    resultDeque.removeLast();
                    continue;
                }

                break;
            }

            resultDeque.addLast(element);

            removeOutOfIndex(index, windowSize);

            int[] firstInfo = resultDeque.peekFirst();
            sb.append(firstInfo[1]).append(" ");
        }
    }

    private static void removeOutOfIndex(int currentIndex, int windowSize) {
        int[] minInfo = resultDeque.peekFirst();
        int minIndex = minInfo[0];

        if(currentIndex - minIndex >= windowSize) resultDeque.removeFirst();
    }
}