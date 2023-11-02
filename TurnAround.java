import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class TurnAround {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String S = br.readLine();

        int rmCount0 = getRmCount0(S);
        int rmCount1 = getRmCount1(S);

        System.out.println(Math.min(rmCount0, rmCount1));
    }

    public static int getRmCount1(String value) {
        StringTokenizer st = new StringTokenizer(value, "1");

        return st.countTokens();
    }

    public static int getRmCount0(String value) {
        StringTokenizer st = new StringTokenizer(value, "0");

        return st.countTokens();
    }
}
