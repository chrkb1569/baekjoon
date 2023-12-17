import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.stream.Collectors;

public class StringExplosion {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String original = br.readLine();
        String target = br.readLine();

        String trimmedOriginal = removeTarget(original, target);

        printResult(trimmedOriginal);
    }

    private static String removeTarget(String original, String target) {
        Stack<Character> resultStack = new Stack<>();
        int length = target.length();

        for(char word : original.toCharArray()) {
            resultStack.push(word);

            if(resultStack.size() >= length) {
                boolean flag = true;

                for(int i = 0; i < length; i++) {
                    if(resultStack.get(resultStack.size() - length + i) != target.charAt(i)) {
                        flag = false;
                        break;
                    }
                }

                if(flag) {
                    for(int i = 0; i < target.length(); i++) {
                        resultStack.pop();
                    }
                }
            }
        }

        return resultStack.stream().map(String::valueOf).collect(Collectors.joining());
    }

    private static void printResult(String result) {
        if(result.isBlank() || result.isEmpty()) {
            System.out.println("FRULA");
            return;
        }
        System.out.println(result);
    }
}
