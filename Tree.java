import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Tree {
    private static Node[] resultArr;
    private static long count = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine()); // node
        st = new StringTokenizer(br.readLine(), " ");

        initResultArr(N);
        mkResultArr(st);

        int rmNumber = Integer.parseInt(br.readLine());

        for(int index = 0; index < resultArr.length; index++) {
            if(resultArr[index].getChildren().size() == 1 && resultArr[index].getChildren().get(0) == rmNumber) {
                count += findParent(resultArr[index].getNumber(), rmNumber);
                continue;
            }
            if(!resultArr[index].getChildren().isEmpty()) continue;

            count += findParent(resultArr[index].getNumber(), rmNumber);
        }

        System.out.println(count);
    }

    private static void initResultArr(int length) {
        resultArr = new Node[length];

        for(int index = 0; index < resultArr.length; index++) {
            resultArr[index] = new Node(index);
        }
    }

    private static void mkResultArr(StringTokenizer st) {
        int index = 0;

        for(;st.hasMoreTokens();) {
            int parent = Integer.parseInt(st.nextToken());

            resultArr[index].setParent(parent);

            if(parent != -1) {
                resultArr[parent].getChildren().add(index);
            }

            index++;
        }
    }

    private static int findParent(int number, int rmNumber) {
        if(number == rmNumber) return 0;
        if(resultArr[number].getParent() == rmNumber) return 0;
        if(resultArr[number].getParent() == -1) return 1;

        return findParent(resultArr[number].getParent(), rmNumber);
    }

    private static class Node {
        private int number;
        private int parent;
        private List<Integer> children = new ArrayList<>();

        public Node(int number) {
            this.number = number;
        }

        public int getNumber() {
            return this.number;
        }

        public int getParent() {
            return this.parent;
        }

        public List<Integer> getChildren() {
            return this.children;
        }

        public void setParent(int parent) {
            this.parent = parent;
        }
    }
}