import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class AlphaCentauri {
    private static final StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for(int testCase = 0; testCase < T; testCase++) {
            st = new StringTokenizer(br.readLine(), " ");

            int startNumber = Integer.parseInt(st.nextToken());
            int endNumber = Integer.parseInt(st.nextToken());

            int minCount = getMinCount(startNumber, endNumber);

            sb.append(minCount).append("\n");
        }

        System.out.println(sb);
    }

    private static int getMinCount(int startNumber, int endNumber) {
        int distance = endNumber - startNumber;
        int max = (int)Math.sqrt(distance);

        if(max == Math.sqrt(distance)) return 2 * max - 1;
        if(distance <= max * max + max) return 2 * max;
        return 2 * max + 1;
    }
}