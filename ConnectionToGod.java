import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class ConnectionToGod {

    private static Point[] pointArr;
    private static int[] unionArr;
    private static long[][] distanceArr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int N = Integer.parseInt(st.nextToken()); // 우주신들의 개수
        int M = Integer.parseInt(st.nextToken()); // 신들과 연결된 통로의 수

        initPointArr(N);
        initUnionArr(N);
        initDistanceArr(N);

        for(int index = 1; index <= N; index++) {
            st = new StringTokenizer(br.readLine(), " ");

            int X = Integer.parseInt(st.nextToken());
            int Y = Integer.parseInt(st.nextToken());

            pointArr[index] = new Point(X, Y);
        }

        mkDistanceArr();

        for(int index = 1; index <= M; index++) {
            st = new StringTokenizer(br.readLine(), " ");

            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            union(start, end);
        }

        double minLength = getMinLength();

        System.out.println(String.format("%.2f", minLength));
    }

    private static void initPointArr(int length) {
        pointArr = new Point[length + 1];
    }

    private static void initUnionArr(int length) {
        unionArr = new int[length + 1];

        for(int index = 0; index < unionArr.length; index++) {
            unionArr[index] = index;
        }
    }

    private static void initDistanceArr(int length) {
        distanceArr = new long[length + 1][length + 1];
    }

    private static void mkDistanceArr() {
        for(int from = 1; from < distanceArr.length; from++) {
            Point start = pointArr[from];

            int startX = start.getX();
            int startY = start.getY();

            for(int to = 1; to < distanceArr.length; to++) {
                if(from == to) continue;

                Point end = pointArr[to];

                int endX = end.getX();
                int endY = end.getY();

                long gapX = endX - startX;
                long gapY = endY - startY;

                distanceArr[from][to] = (long)Math.pow(gapX, 2) + (long)Math.pow(gapY, 2);
            }
        }
    }

    private static double getMinLength() {
        PriorityQueue<long[]> resultQueue = new PriorityQueue<>(Comparator.comparingLong(o -> o[2]));
        double length = 0;

        for(int from = 1; from < distanceArr.length; from++) {
            for(int to = from + 1; to < distanceArr.length; to++) {
                int fromRoot = findRoot(from);
                int toRoot = findRoot(to);

                if(fromRoot == toRoot) continue;
                resultQueue.add(new long[]{from, to, distanceArr[from][to]});
            }
        }

        while(!resultQueue.isEmpty()) {
            long[] arr = resultQueue.poll();

            int start = (int)arr[0];
            int end = (int)arr[1];
            long distance = arr[2];

            int rootStart = findRoot(start);
            int rootEnd = findRoot(end);

            if(rootStart == rootEnd) continue;

            union(start, end);
            length += Math.sqrt(distance);
        }

        return length;
    }

    private static int findRoot(int number) {
        int root = unionArr[number];

        if(root == number) return root;
        unionArr[number] = findRoot(root);

        return unionArr[number];
    }

    private static void union(int from, int to) {
        int rootFrom = findRoot(from);
        int rootTo = findRoot(to);

        if(rootFrom == rootTo) return;
        unionArr[rootFrom] = rootTo;
    }

    private static class Point {
        private final int x;
        private final int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return this.x;
        }

        public int getY() {
            return this.y;
        }
    }
}