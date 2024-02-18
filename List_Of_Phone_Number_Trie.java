import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class List_Of_Phone_Number_Trie {
    private static List<String> userInput;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int testCase = Integer.parseInt(br.readLine());

        for(int i = 0; i < testCase; i++) {
            int N = Integer.parseInt(br.readLine());
            Node node = new Node();
            userInput = new ArrayList<>();

            for(int j = 0; j < N; j++) {
                String inputValue = br.readLine();

                node.insertData(inputValue);
                userInput.add(inputValue);
            }

            if(!checkValidation(node)) {
                sb.append("NO").append("\n");
                continue;
            }

            sb.append("YES").append("\n");
        }

        System.out.println(sb);
    }

    private static boolean checkValidation(Node node) {
        for(String value : userInput) {
            if(node.containsData(value)) return false;
        }

        return true;
    }

    private static class Node {
        private final Map<Character, Node> childNode = new HashMap<>();
        private boolean isEnd;

        public void insertData(String data) {
            Node root = this;

            for(int index = 0; index < data.length(); index++) {
                char key = data.charAt(index);

                root.childNode.putIfAbsent(key, new Node());
                root = root.childNode.get(key);

                if(index == data.length() - 1) {
                    root.isEnd = true;
                }
            }
        }

        public boolean containsData(String data) {
            Node root = this;

            for(int index = 0; index < data.length(); index++) {
                char key = data.charAt(index);

                if(!root.childNode.containsKey(key)) return false;
                root = root.childNode.get(key);
            }

            if(root.isEnd) return !root.childNode.isEmpty();
            return true;
        }
    }
}