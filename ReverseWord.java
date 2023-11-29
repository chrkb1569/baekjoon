import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class ReverseWord {
    private static Stack<Character> resultStack = new Stack<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String wordString = "";

        String value = br.readLine();

        for(int i = 0; i < value.length(); i++) {
            char word = value.charAt(i);

            if(word == '<') {
                resultStack.push(word);
                String reverseString = getReverseString(wordString);
                sb.append(reverseString);
                sb.append(word);
                wordString = "";
                continue;
            }

            if(word == '>') {
                resultStack.pop();
                sb.append(word);
                continue;
            }

            if(!resultStack.isEmpty() && word == ' ') {
                sb.append(word);
                continue;
            }

            if(resultStack.isEmpty() && word == ' ') {
                String reverseString = getReverseString(wordString);
                sb.append(reverseString);
                sb.append(word);
                wordString = "";
                continue;
            }

            if(!resultStack.isEmpty()) {
                sb.append(word);
                continue;
            }

            wordString += word;
        }

        if(!wordString.isBlank() || !wordString.isEmpty()) {
            String reverseString = getReverseString(wordString);
            sb.append(reverseString);
        }

        System.out.println(sb);
    }

    private static String getReverseString(String value) {
        StringBuilder sb = new StringBuilder();

        sb.append(value);

        return sb.reverse().toString();
    }
}

// <int><max>7463847412<long long><max>7085774586302733229