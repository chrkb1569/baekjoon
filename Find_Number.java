import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Find_Number {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Map<Integer, Integer> map = new HashMap<>();
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        for(int i = 0; i < N; i++) {
            map.put(Integer.parseInt(st.nextToken()), 0);
        }

        int M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        int[] arr2 = new int[M];

        for(int j = 0; j < M; j++) {
            if(map.containsKey(Integer.parseInt(st.nextToken()))) {
                arr2[j] = 1;
            }
            else arr2[j] = 0;
        }

        for(int s : arr2) {
            System.out.println(s);
        }

    }
}
