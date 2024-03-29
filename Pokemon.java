import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.regex.Pattern;

public class Pokemon {
    private static Map<String, Integer> nameMap = new HashMap<>();
    private static Map<Integer, String> numberMap = new HashMap<>();
    private static StringBuilder sb = new StringBuilder();
    private static final String REG_EXP = "[0-9]{1,}";

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int N = Integer.parseInt(st.nextToken()); // pokemon
        int M = Integer.parseInt(st.nextToken()); // question

        for(int testCase = 0; testCase < N; testCase++) {
            String pokemon = br.readLine();
            mkResultMap(pokemon, testCase);
        }

        for(int testCase = 0; testCase < M; testCase++) {
            String question = br.readLine();
            answerByQuestion(question);
        }

        System.out.println(sb);
    }

    private static void mkResultMap(String pokemon, int number) {
        nameMap.put(pokemon, number + 1);
        numberMap.put(number + 1, pokemon);
    }

    private static void answerByQuestion(String question) {
        if(Pattern.matches(REG_EXP, question)) {
            sb.append(numberMap.get(Integer.parseInt(question))).append("\n");
            return;
        }

        sb.append(nameMap.get(question)).append("\n");
    }
}
