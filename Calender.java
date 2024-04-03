import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Calender {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine()); // testCase

        for(int testCase = 0; testCase < T; testCase++) {
            st = new StringTokenizer(br.readLine(), " ");

            int M = Integer.parseInt(st.nextToken()); // endX
            int N = Integer.parseInt(st.nextToken()); // endY
            int x = Integer.parseInt(st.nextToken()); // targetX
            int y = Integer.parseInt(st.nextToken()); // targetY

            sb.append(getDate(M, N, x-1, y-1)).append("\n");
        }

        System.out.println(sb);
    }

    private static int getDate(int M, int N, int x, int y) {
        for(int index = x; index < N * M; index += M) {
            if(index % N == y) {
                return index + 1;
            }
        }

        return -1;
    }
}