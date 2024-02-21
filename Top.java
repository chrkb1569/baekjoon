import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Top {
    private static Node[] resultArr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        initResultArr(N);
        mkResultArr(st);

        System.out.println(getResultString());
    }

    private static void initResultArr(int length) {
        resultArr = new Node[length];
    }

    private static void mkResultArr(StringTokenizer st) {
        int index = 0;

        for(;st.hasMoreTokens();) {
            resultArr[index] = new Node(Integer.parseInt(st.nextToken()), index + 1);
            index++;
        }
    }

    private static String getResultString() {
        Stack<Node> resultStack = new Stack<>();
        StringBuilder sb = new StringBuilder();

        for(int index = 0; index < resultArr.length; index++) {
            Node node = resultArr[index];

            if(resultStack.isEmpty()) {
                resultStack.push(node);
                sb.append(0).append(" ");
                continue;
            }

            Node peekValue = resultStack.peek();

            if(peekValue.getValue() >= node.getValue()) {
                resultStack.push(node);
                sb.append(peekValue.getIndex()).append(" ");
                continue;
            }

            while(!resultStack.isEmpty()) {
                Node peek = resultStack.peek();

                if(peek.getValue() < node.getValue()) {
                    resultStack.pop();
                    continue;
                }

                break;
            }

            if(resultStack.isEmpty()) {
                resultStack.push(node);
                sb.append(0).append(" ");
                continue;
            }

            Node peek = resultStack.peek();
            sb.append(peek.getIndex()).append(" ");
            resultStack.push(node);
        }

        return sb.toString();
    }

    private static class Node {
        private int value;
        private int index;

        public Node(int value, int index) {
            this.value = value;
            this.index = index;
        }

        public int getValue() {
            return this.value;
        }

        public int getIndex() {
            return this.index;
        }
    }
}