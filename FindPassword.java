import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class FindPassword {
    private static Map<String, String> resultMap = new HashMap<>();
    private static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int N = Integer.parseInt(st.nextToken()); // sites
        int M = Integer.parseInt(st.nextToken()); // req

        for(int word = 0; word < N; word++) {
            st = new StringTokenizer(br.readLine(), " ");

            String siteName = st.nextToken();
            String password = st.nextToken();

            resultMap.put(siteName, password);
        }

        for(int key = 0; key < M; key++) {
            String keyValue = br.readLine();
            sb.append(resultMap.get(keyValue)).append("\n");
        }

        System.out.println(sb);
    }
}