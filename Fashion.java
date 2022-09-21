import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Fashion {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int testCase = Integer.parseInt(br.readLine());

        for(int i = 0; i < testCase; i++) {
            int choice = Integer.parseInt(br.readLine());
            Map<String, Integer> cloth = new HashMap<>();

            for(int j = 0; j < choice; j++) {
                StringTokenizer st = new StringTokenizer(br.readLine(), " ");

                String item = st.nextToken();
                String category = st.nextToken();

                cloth.put(category, cloth.getOrDefault(category, 0) + 1);
            }

            int result = 1;

            for(Map.Entry<String, Integer> entry : cloth.entrySet()) {
                result *= (entry.getValue() + 1);
            }

            result--;

            System.out.println(result);
        }
    }
}
