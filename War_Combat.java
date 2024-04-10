import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class War_Combat {
    private static Node[][] resultArr;
    private static final int[] dx = {1, -1, 0, 0};
    private static final int[] dy = {0, 0, 1, -1};
    private static final char TEAM_SIGNAL = 'W'; // 아군
    private static int teamCount = 0;
    private static int enemyCount = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int N = Integer.parseInt(st.nextToken()); // width
        int M = Integer.parseInt(st.nextToken()); // height

        initResultArr(M, N);

        for(int height = 0; height < M; height++) {
            char[] infoArr = br.readLine().toCharArray();
            mkResultArr(infoArr, height);
        }

        for(int height = 0; height < resultArr.length; height++) {
            for(int width = 0; width < resultArr[0].length; width++) {
                Node node = resultArr[height][width];

                if(node.isVisit()) continue;

                char signal = node.getValue();

                if(signal == TEAM_SIGNAL) {
                    int count = getTeamCount(node);
                    teamCount += count * count;
                    continue;
                }

                int count = getTeamCount(node);
                enemyCount += count * count;
            }
        }

        System.out.println(teamCount + " " + enemyCount);
    }

    private static void initResultArr(int height, int width) {
        resultArr = new Node[height][width];
    }

    private static void mkResultArr(char[] infoArr, int height) {
        for(int width = 0; width < infoArr.length; width++) {
            resultArr[height][width] = new Node(width, height, infoArr[width]);
        }
    }

    private static int getTeamCount(Node startNode) {
        Queue<Node> resultQueue = new LinkedList<>();
        startNode.visitNode();
        resultQueue.add(startNode);
        int count = 0;

        while(!resultQueue.isEmpty()) {
            Node node = resultQueue.poll();

            int width = node.getX();
            int height = node.getY();
            char value = node.getValue();

            count++;

            for(int i = 0; i < 4; i++) {
                int xValue = width + dx[i];
                int yValue = height + dy[i];

                if(xValue < 0 || yValue < 0 || xValue >= resultArr[0].length || yValue >= resultArr.length) continue;
                if(resultArr[yValue][xValue].isVisit()) continue;
                if(resultArr[yValue][xValue].getValue() != value) continue;

                resultArr[yValue][xValue].visitNode();
                resultQueue.add(resultArr[yValue][xValue]);
            }
        }

        return count;
    }

    private static class Node {
        private int x;
        private int y;
        private char value;
        private boolean visit;

        public Node(int x, int y, char value) {
            this.x = x;
            this.y = y;
            this.value = value;
            this.visit = false;
        }

        public int getX() {
            return this.x;
        }

        public int getY() {
            return this.y;
        }

        public char getValue() {
            return this.value;
        }

        public boolean isVisit() {
            return this.visit;
        }

        public void visitNode() {
            this.visit = true;
        }
    }
}
