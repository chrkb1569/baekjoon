import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Number_Card_2{

    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int card = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        Map<Integer, Integer> map = new HashMap<>();

        for(int i = 0; i < card; i++) {
            map.put(Integer.parseInt(st.nextToken()), 0);
        }

        int result = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine(), " ");

        for(int i = 0; i < result; i++) {
            if(map.containsKey(Integer.parseInt(st.nextToken()))) {
                sb.append("1").append(" ");
            }
            else sb.append("0").append(" ");
        }

        bw.write(sb + "\n");
        bw.flush();
        bw.close();
        br.close();
    }
}
