import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class ABCDE {
    private static Node[] resultArr;
    private static final int TARGET_CONNECTION_COUNT = 5;
    private static int CONNECTION_COUNT;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        initResultArr(N);

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");

            int from = Integer.parseInt(st.nextToken()) + 1;
            int to = Integer.parseInt(st.nextToken()) + 1;

            resultArr[from].getLinks().add(to);
            resultArr[to].getLinks().add(from);
        }

        printResult(N);
    }

    private static void initResultArr(int length) {
        resultArr = new Node[length + 1];

        for(int i = 1; i < resultArr.length; i++) {
            resultArr[i] = new Node(i);
        }
    }

    private static boolean checkValidation(int totalPerson) {
        for(int personNumber = 1; personNumber <= totalPerson; personNumber++) {
            boolean[] visitArr = new boolean[resultArr.length];
            CONNECTION_COUNT = 0;

            getConnectionCount(visitArr, personNumber, 0);

            if(CONNECTION_COUNT >= TARGET_CONNECTION_COUNT) return true;
        }

        return false;
    }

    private static void getConnectionCount(boolean[] visitArr, int currentNumber, int count) {
        if(CONNECTION_COUNT >= TARGET_CONNECTION_COUNT) return;
        if(count >= TARGET_CONNECTION_COUNT) {
            CONNECTION_COUNT = TARGET_CONNECTION_COUNT;
            return;
        }

        for(int nextIndex : resultArr[currentNumber].getLinks()) {
            if(!visitArr[nextIndex]) {
                visitArr[nextIndex] = true;
                getConnectionCount(visitArr, nextIndex, count + 1);
                visitArr[nextIndex] = false;
            }
        }
    }

    private static void printResult(int totalPerson) {
        if(checkValidation(totalPerson)) {
            System.out.println(1);
            return;
        }

        System.out.println(0);
    }

    private static class Node {
        private int number;
        private List<Integer> links;

        public Node(int number) {
            this.number = number;
            this.links = new ArrayList<>();
        }

        public int getNumber() {
            return this.number;
        }

        public List<Integer> getLinks() {
            return this.links;
        }
    }
}
