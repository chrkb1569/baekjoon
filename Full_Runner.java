import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Full_Runner {

    public static Map<String, Integer> resultMap;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int runner = Integer.parseInt(br.readLine());

        initResultMap();

        for(int i = 0; i < runner; i++) {
            String runnerName = br.readLine();
            resultMap.put(runnerName, resultMap.getOrDefault(runnerName, 0) + 1);
        }

        for(int i = 0; i < runner-1; i++) {
            String runnerName = br.readLine();
            resultMap.put(runnerName, resultMap.get(runnerName)-1);
        }

        for(String key : resultMap.keySet()) {
            if(resultMap.get(key) != 0) System.out.println(key);
        }
    }

    public static void initResultMap() {
        resultMap = new HashMap<>();
    }
}
