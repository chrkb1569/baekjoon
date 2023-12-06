import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Make1 {
    private static Node[] resultArr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        initResultArr(N);
        mkResultArr();
        printResult(N);
    }

    private static void initResultArr(int length) {
        resultArr = new Node[length + 1];

        resultArr[1] = new Node(0, "1");
    }

    private static void mkResultArr() {
        for(int i = 2; i < resultArr.length; i++) {
            int div3 = 0;
            int div2 = 0;
            int sub1 = i - 1;

            if(i % 3 == 0) {
                div3 = i / 3;
            }

            if(i % 2 == 0) {
                div2 = i / 2;
            }

            int index = compareIndex(compareIndex(div2, div3), sub1);

            Node node = resultArr[index];
            int count = node.getCount();
            String process = node.getProcess();

            resultArr[i] = new Node(count + 1, i + " " + process);
        }
    }

    private static void printResult(int index) {
        Node node = resultArr[index];

        System.out.println(node.getCount());
        System.out.println(node.getProcess());
    }

    private static int compareIndex(int index1, int index2) {
        if(index1 == 0) return index2;
        if(index2 == 0) return index1;

        Node node1 = resultArr[index1];
        Node node2 = resultArr[index2];

        int value1 = node1.getCount();
        int value2 = node2.getCount();

        if(value1 < value2) return index1;
        return index2;
    }

    private static class Node {
        private int count;
        private String process;

        public Node(int count, String process) {
            this.count = count;
            this.process = process;
        }

        public int getCount() {
            return this.count;
        }

        public String getProcess() {
            return this.process;
        }
    }
}
