import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class HideAndSeek_4 {
    private static boolean[] visitArr = new boolean[100_001];
    private static int[] parentArr = new int[100_001];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int start = Integer.parseInt(st.nextToken());
        int destination = Integer.parseInt(st.nextToken());

        bfs(start, destination);

        printRoute(start, destination);
    }

    private static void bfs(int startNumber, int destinationNumber) {
        Queue<Node> resultQueue = new LinkedList<>();
        resultQueue.offer(new Node(startNumber, 0));
        visitArr[startNumber] = true;

        while(!resultQueue.isEmpty()) {
            Node node = resultQueue.poll();

            int number = node.getNumber();
            int count = node.getCount();

            if(number == destinationNumber) {
                System.out.println(count);
                break;
            }

            if(2 * number < visitArr.length && !visitArr[2*number]) {
                Node nextNode = new Node(2*number, count+1);
                visitArr[2*number] = true;
                parentArr[2*number] = number;
                resultQueue.offer(nextNode);
            }

            if(number + 1 < visitArr.length && !visitArr[number + 1]) {
                Node nextNode = new Node(number+1, count+1);
                visitArr[number+1] = true;
                parentArr[number+1] = number;
                resultQueue.offer(nextNode);
            }

            if(number - 1 >= 0 && !visitArr[number - 1]) {
                Node nextNode = new Node(number-1, count+1);
                visitArr[number-1] = true;
                parentArr[number-1] = number;
                resultQueue.offer(nextNode);
            }
        }
    }

    private static void printRoute(int startNumber, int destination) {
        Stack<Integer> resultStack = new Stack<>();
        StringBuilder sb = new StringBuilder();

        int value = destination;

        while(value != startNumber) {
            resultStack.push(value);
            value = parentArr[value];
        }

        resultStack.push(value);

        while(!resultStack.isEmpty()) {
            sb.append(resultStack.pop()).append(" ");
        }

        System.out.println(sb);
    }

    private static class Node {
        private int number;
        private int count;

        public Node(int number, int count) {
            this.number = number;
            this.count = count;
        }

        public int getNumber() {
            return this.number;
        }

        private int getCount() {
            return this.count;
        }
    }
}
