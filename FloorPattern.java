import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class FloorPattern {
    private static Node[][] resultArr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int N = Integer.parseInt(st.nextToken()); // height
        int M = Integer.parseInt(st.nextToken()); // width

        initResultArr(N, M);

        for(int height = 0; height < N; height++) {
            char[] infoArr = br.readLine().toCharArray();
            mkResultArr(infoArr, height);
        }

        int patternCount = getPatternCount();

        System.out.println(patternCount);
    }

    private static void initResultArr(int height, int width) {
        resultArr = new Node[height][width];
    }

    private static void mkResultArr(char[] infoArr, int height) {
        for(int index = 0; index < infoArr.length; index++) {
            resultArr[height][index] = new Node(infoArr[index]);
        }
    }

    private static int getPatternCount() {
        int count = 0;

        for(int height = 0; height < resultArr.length; height++) {
            for(int width = 0; width < resultArr[0].length; width++) {
                Node node = resultArr[height][width];

                if(node.isVisit()) continue;

                node.visitNode();

                count++;

                findPattern(height, width, node.getPattern());
            }
        }

        return count;
    }

    private static void findPattern(int height, int width, char pattern) {
        if(pattern == '-') {
            if(width + 1 >= resultArr[0].length) return;

            Node nextNode = resultArr[height][width + 1];

            if(nextNode.getPattern() != '-') return;
            if(nextNode.isVisit()) return;

            nextNode.visitNode();

            findPattern(height, width + 1, pattern);

            return;
        }

        if(height + 1 >= resultArr.length) return;

        Node nextNode = resultArr[height + 1][width];

        if(nextNode.getPattern() != '|') return;
        if(nextNode.isVisit()) return;

        nextNode.visitNode();

        findPattern(height + 1, width, pattern);
    }

    private static class Node {
        private char pattern;
        private boolean visit;

        public Node(char pattern) {
            this.pattern = pattern;
            this.visit = false;
        }

        public char getPattern() {
            return this.pattern;
        }

        public boolean isVisit() {
            return this.visit;
        }

        public void visitNode() {
            this.visit = true;
        }
    }
}
