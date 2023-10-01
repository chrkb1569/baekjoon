import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class MoveOfKnight {
    public static StringBuilder sb = new StringBuilder();
    public static boolean[][] resultArr;
    public static int[] dx = {-1, 1, -1, 1, -2, 2, -2, 2};
    public static int[] dy = {2, 2, -2, -2, 1, 1, -1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int testCase = Integer.parseInt(br.readLine());

        for(int i = 0; i < testCase; i++) {
            int N = Integer.parseInt(br.readLine());

            initResultArr(N);

            String[] startInfo = br.readLine().split(" ");
            String[] endInfo = br.readLine().split(" ");

            int startX = Integer.parseInt(startInfo[0]);
            int startY = Integer.parseInt(startInfo[1]);
            int endX = Integer.parseInt(endInfo[0]);
            int endY = Integer.parseInt(endInfo[1]);

            getResult(startX, startY, endX, endY);
        }

        System.out.println(sb);
    }

    public static void getResult(int startX, int startY, int endX, int endY) {
        Queue<Node> resultQueue = new LinkedList<>();

        Node value = new Node(startX, startY);
        value.setCount(0);
        resultQueue.offer(value);

        while(!resultQueue.isEmpty()) {
            Node node = resultQueue.poll();

            int x = node.getX();
            int y = node.getY();
            int count = node.getCount();

            if(x == endX && y == endY) {
                sb.append(count).append("\n");
                break;
            }
            if(resultArr[y][x]) continue;

            resultArr[y][x] = true;

            for(int i = 0; i < 8; i++) {
                int xValue = x + dx[i];
                int yValue = y + dy[i];

                if(xValue >= 0 && yValue >= 0 && xValue < resultArr.length && yValue < resultArr.length) {
                    Node insertNode = new Node(xValue, yValue);
                    insertNode.setCount(count + 1);
                    resultQueue.offer(insertNode);
                }
            }
        }
    }

    public static void initResultArr(int length) {
        resultArr = new boolean[length][length];
    }

    public static class Node {
        private int x;
        private int y;
        private int count;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
            this.count = Integer.MAX_VALUE;
        }

        public int getX() {
            return this.x;
        }

        public int getY() {
            return this.y;
        }

        public int getCount() {
            return this.count;
        }

        public void setCount(int count) {
            this.count = Math.min(count, this.count);
        }
    }
}
/**
 * 3
 * 8
 * 0 0
 * 7 0
 * 100
 * 0 0
 * 30 50
 * 10
 * 1 1
 * 1 1
 *
 * 5
 * 28
 * 0
 *
 * 입력의 첫째 줄에는 테스트 케이스의 개수가 주어진다.
 *
 * 각 테스트 케이스는 세 줄로 이루어져 있다. 첫째 줄에는 체스판의 한 변의 길이 l(4 ≤ l ≤ 300)이 주어진다.
 * 체스판의 크기는 l × l이다. 체스판의 각 칸은 두 수의 쌍 {0, ..., l-1} × {0, ..., l-1}로 나타낼 수 있다.
 * 둘째 줄과 셋째 줄에는 나이트가 현재 있는 칸, 나이트가 이동하려고 하는 칸이 주어진다.
 */