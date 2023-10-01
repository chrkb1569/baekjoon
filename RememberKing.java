import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class RememberKing {
    public static Set<Integer> resultSet;
    public static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int testCase = Integer.parseInt(br.readLine());

        for(int i = 0; i < testCase; i++) {
            initResultSet();
            int M = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine(), " ");
            mkResultSet(st);
            int N = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine(), " ");
            mkResult(st);
        }

        System.out.println(sb);
    }

    public static void initResultSet() {
        resultSet = new TreeSet<>();
    }

    public static void mkResultSet(StringTokenizer st) {
        for(;st.hasMoreTokens();) {
            resultSet.add(Integer.parseInt(st.nextToken()));
        }
    }

    public static void mkResult(StringTokenizer st) {
        for(;st.hasMoreTokens();) {
            int value = Integer.parseInt(st.nextToken());
            if(resultSet.contains(value)) {
                sb.append("1").append("\n");
                continue;
            }
            sb.append("0").append("\n");
        }
    }
}