import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class DualPriorityQueue {
    private static TreeMap<Integer, Integer> resultMap;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int testCase = Integer.parseInt(br.readLine());

        for(int i = 0; i < testCase; i++) {
            int commands = Integer.parseInt(br.readLine());
            resultMap = new TreeMap<>();

            for(int j = 0; j < commands; j++) {
                st = new StringTokenizer(br.readLine(), " ");
                String command = st.nextToken();
                int value = Integer.parseInt(st.nextToken());

                processCommand(command, value);
            }

            sb.append(getResultString()).append("\n");
        }

        System.out.println(sb);
    }

    private static void processCommand(String command, int value) {
        if(command.equals("D") && value == 1) {
            if(resultMap.size() == 0) return;
            int key = resultMap.lastKey();

            if(resultMap.get(key) == 1) {
                resultMap.remove(key);
                return;
            }

            resultMap.put(key, resultMap.get(key) - 1);
            return;
        }

        if(command.equals("D") && value == -1) {
            if(resultMap.size() == 0) return;
            int key = resultMap.firstKey();

            if(resultMap.get(key) == 1) {
                resultMap.remove(key);
                return;
            }

            resultMap.put(key, resultMap.get(key) - 1);
            return;
        }

        resultMap.put(value, resultMap.getOrDefault(value, 0) + 1);
    }

    private static String getResultString() {
        if(resultMap.size() == 0) return "EMPTY";

        int firstKey = resultMap.firstKey();
        int lastKey = resultMap.lastKey();

        return lastKey + " " + firstKey;
    }
}
