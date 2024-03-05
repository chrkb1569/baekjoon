import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class MakeBridge_2 {
    private static int[] parentArr;
    private static int[][] resultArr;
    private static PriorityQueue<Link> resultQueue = new PriorityQueue<>();
    private static final int[] dx = {1, -1, 0, 0};
    private static final int[] dy = {0, 0, 1, -1};
    private static int minValue = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int N = Integer.parseInt(st.nextToken()); // height
        int M = Integer.parseInt(st.nextToken()); // width

        initResultArr(N, M);

        for(int height = 0; height < N; height++) {
            st = new StringTokenizer(br.readLine(), " ");
            mkResultArr(st, height);
        }

        int islandCount = getIslandCount();

        initParentArr(islandCount);
        mkResultQueue(islandCount);

        while(!resultQueue.isEmpty()) {
            Link link = resultQueue.poll();

            int from = link.getFrom();
            int to = link.getTo();
            int cost = link.getCost();

            int parentFrom = findParent(from);
            int parentTo = findParent(to);

            if(parentTo != parentFrom) {
                union(from, to);
                minValue += cost;
            }
        }

        if(checkValidation()) {
            System.out.println(minValue);
            return;
        }

        System.out.println(-1);
    }

    private static void initResultArr(int height, int width) {
        resultArr = new int[height][width];
    }

    private static void mkResultArr(StringTokenizer st, int height) {
        int index = 0;

        for(;st.hasMoreTokens();) {
            resultArr[height][index++] = Integer.parseInt(st.nextToken());
        }
    }

    private static int getIslandCount() {
        int islandCount = 0;

        boolean[][] visitArr = new boolean[resultArr.length][resultArr[0].length];

        for(int height = 0; height < resultArr.length; height++) {
            for(int width = 0; width < resultArr[0].length; width++) {
                if(resultArr[height][width] == 0) continue;
                if(visitArr[height][width]) continue;

                islandCount++;
                search(visitArr, height, width, islandCount);
            }
        }

        return islandCount;
    }

    private static void search(boolean[][] visit, int height, int width, int type) {
        Queue<int[]> resultQueue = new LinkedList<>();
        visit[height][width] = true;
        resultArr[height][width] = type;
        resultQueue.add(new int[]{height, width});

        while(!resultQueue.isEmpty()) {
            int[] arr = resultQueue.poll();

            int y = arr[0];
            int x = arr[1];

            for(int i = 0; i < 4; i++) {
                int xValue = x + dx[i];
                int yValue = y + dy[i];

                if(xValue < 0 || yValue < 0 || xValue >= resultArr[0].length || yValue >= resultArr.length) continue;
                if(visit[yValue][xValue]) continue;
                if(resultArr[yValue][xValue] == 0) continue;

                visit[yValue][xValue] = true;
                resultArr[yValue][xValue] = type;

                resultQueue.add(new int[]{yValue, xValue});
            }
        }
    }

    private static void mkResultQueue(int islandCount) {
        int[][] distanceArr = new int[islandCount + 1][islandCount + 1];

        for(int height = 0; height < distanceArr.length; height++) {
            Arrays.fill(distanceArr[height], Integer.MAX_VALUE);
        }

        for(int number = 1; number <= islandCount; number++) {
            for(int height = 0; height < resultArr.length; height++) {
                for(int width = 0; width < resultArr[0].length; width++) {
                    if(resultArr[height][width] != number) continue;

                    mkDistanceArr(distanceArr[number], height, width);
                }
            }
        }

        for(int height = 0; height < distanceArr.length; height++) {
            for(int width = height; width < distanceArr[0].length; width++) {
                int value = distanceArr[height][width];

                if(value == Integer.MAX_VALUE || value < 2) continue;

                resultQueue.add(new Link(height, width, value));
            }
        }
    }

    private static void mkDistanceArr(int[] distanceArr, int height, int width) {
        int length = 0;

        for(int w = width + 1; w < resultArr[0].length; w++) {
            if(resultArr[height][w] == resultArr[height][width]) break;
            if(resultArr[height][w] == 0) {
                length++;
                continue;
            }

            if(resultArr[height][w] != resultArr[height][width]) {
                if(length >= 2) {
                    distanceArr[resultArr[height][w]] = Math.min(length, distanceArr[resultArr[height][w]]);
                }
                break;
            }
        }

        length = 0;

        for(int w = width - 1; w >= 0; w--) {
            if(resultArr[height][w] == resultArr[height][width]) break;
            if(resultArr[height][w] == 0) {
                length++;
                continue;
            }

            if(resultArr[height][w] != resultArr[height][width]) {
                if(length >= 2) {
                    distanceArr[resultArr[height][w]] = Math.min(length, distanceArr[resultArr[height][w]]);
                }
                break;
            }
        }

        length = 0;

        for(int h = height + 1; h < resultArr.length; h++) {
            if(resultArr[h][width] == resultArr[height][width]) break;
            if(resultArr[h][width] == 0) {
                length++;
                continue;
            }

            if(resultArr[h][width] != resultArr[height][width]) {
                if(length >= 2) {
                    distanceArr[resultArr[h][width]] = Math.min(length, distanceArr[resultArr[h][width]]);
                }
                break;
            }
        }

        length = 0;

        for(int h = height - 1; h >= 0; h--) {
            if(resultArr[h][width] == resultArr[height][width]) break;
            if(resultArr[h][width] == 0) {
                length++;
                continue;
            }

            if(resultArr[h][width] != resultArr[height][width]) {
                if(length >= 2) {
                    distanceArr[resultArr[h][width]] = Math.min(length, distanceArr[resultArr[h][width]]);
                }
                break;
            }
        }
    }

    private static void initParentArr(int length) {
        parentArr = new int[length + 1];

        for(int index = 0; index < parentArr.length; index++) {
            parentArr[index] = index;
        }
    }

    private static int findParent(int index) {
        if(parentArr[index] == index) return index;

        parentArr[index] = findParent(parentArr[index]);

        return parentArr[index];
    }

    private static void union(int a, int b) {
        int parentA = findParent(a);
        int parentB = findParent(b);

        if(parentA < parentB) {
            parentArr[parentB] = parentA;
            return;
        }

        parentArr[parentA] = parentB;
    }

    private static boolean checkValidation() {
        int parent = parentArr[1];

        for(int index = 2; index < parentArr.length; index++) {
            if(parentArr[index] != parent) {
                int parentValue = findParent(parentArr[index]);

                if(parentValue != parent) return false;
            }
        }

        return true;
    }

    private static class Link implements Comparable<Link> {
        private int from;
        private int to;
        private int cost;

        public Link(int from, int to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }

        public int getFrom() {
            return this.from;
        }

        public int getTo() {
            return this.to;
        }

        public int getCost() {
            return this.cost;
        }

        @Override
        public int compareTo(Link link) {
            return this.cost - link.getCost();
        }
    }
}