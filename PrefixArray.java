import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class PrefixArray {
    private static PriorityQueue<String> resultQueue = new PriorityQueue<>();
    private static StringBuffer sb = new StringBuffer();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String S = br.readLine();

        mkResultQueue(S);
        mkResultString();

        System.out.println(sb);
    }

    private static void mkResultQueue(String value) {
        int length = value.length();

        for(int index = 0; index < length; index++) {
            resultQueue.add(value.substring(index, length));
        }
    }

    private static void mkResultString() {
        while(!resultQueue.isEmpty()) {
            sb.append(resultQueue.poll()).append("\n");
        }
    }
}