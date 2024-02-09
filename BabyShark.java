import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BabyShark {
    private final static List<Fish> fishes = new LinkedList<>();
    private static Fish[][] fishArr;
    private static Node[][] nodeArr;
    private static Shark shark;
    private final static int[] dx = {1, -1, 0, 0};
    private final static int[] dy = {0, 0, 1, -1};
    private final static int SHARK_VALUE = 9;
    private final static int INITIAL_SIZE = 2;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int resultDistance = 0;
        int N = Integer.parseInt(br.readLine());

        initFishArr(N);
        initNodeArr(N);

        for(int height = 0; height < N; height++) {
            st = new StringTokenizer(br.readLine(), " ");
            mkFishArr(st, height);
        }

        mkNodeArr();

        Collections.sort(fishes);

        while(checkEdibleFish()) {
            SearchResult searchResult = getBestFish();

            if(searchResult.getBestFish() == null) break;

            Fish bestFish = searchResult.getBestFish();
            int distance = searchResult.getDistance();

            int x = bestFish.getX();
            int y = bestFish.getY();

            shark.eatFish();
            shark.setX(x);
            shark.setY(y);

            fishArr[y][x].setSize(0);
            resultDistance += distance;
        }

        System.out.println(resultDistance);
    }

    private static void initFishArr(int length) {
        fishArr = new Fish[length][length];
    }

    private static void initNodeArr(int length) {
        nodeArr = new Node[length][length];
    }

    private static void mkFishArr(StringTokenizer st, int height) {
        int index = 0;

        for(;st.hasMoreTokens();) {
            int size = Integer.parseInt(st.nextToken());

            if(size == SHARK_VALUE) {
                shark = new Shark(index, height);
                size = 0;
            }

            if(size != 0) {
                fishes.add(new Fish(index, height, size));
            }

            fishArr[height][index] = new Fish(index, height, size);
            index++;
        }
    }

    private static void mkNodeArr() {
        for(int height = 0; height < fishArr.length; height++) {
            for(int width = 0; width < fishArr.length; width++) {
                nodeArr[height][width] = new Node(width, height);
            }
        }
    }

    // 매 TestCase 마다 먹을 수 있는 물고기의 존재 확인
    private static boolean checkEdibleFish() {
        if(fishes.isEmpty()) return false;
        int sharkSize = shark.getSize();

        for(Fish fish : fishes) {
            int fishSize = fish.getSize();

            if(fishSize < sharkSize) return true;
        }

        return false;
    }

    private static SearchResult getBestFish() {
        int resultDistance = Integer.MAX_VALUE;
        int index = 0;
        Fish bestFish = new Fish(Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE);

        for(int i = 0; i < fishes.size(); i++) {
            Fish fish = fishes.get(i);
            int size = fish.getSize();

            if(size >= shark.getSize()) continue;

            initNodeArr();
            int distance = bfs(fish);

            if(distance > resultDistance) continue;

            int x = fish.getX();
            int y = fish.getY();

            if(distance == resultDistance) {
                int bestX = bestFish.getX();
                int bestY = bestFish.getY();

                if(y < bestY) {
                    bestFish = fish;
                    index = i;
                    continue;
                }

                if(y == bestY && x < bestX) {
                    bestFish = fish;
                    index = i;
                    continue;
                }

                continue;
            }

            bestFish = fish;
            resultDistance = distance;
            index = i;
        }

        fishes.remove(index);

        if(resultDistance == Integer.MAX_VALUE) return new SearchResult(null, 0);
        return new SearchResult(bestFish, resultDistance);
    }

    private static int bfs(Fish targetFish) {
        int length = fishArr.length;
        int distance = Integer.MAX_VALUE;

        Queue<Node> resultQueue = new LinkedList<>();
        boolean[][] visitArr = new boolean[length][length];

        int startX = shark.getX();
        int startY = shark.getY();
        int startSize = shark.getSize();

        visitArr[startY][startX] = true;

        Node startNode = new Node(startX, startY);
        startNode.setDepth(0);

        resultQueue.offer(startNode);

        while(!resultQueue.isEmpty()) {
            Node node = resultQueue.poll();

            int x = node.getX();
            int y = node.getY();
            int depth = node.getDepth();

            if(x == targetFish.getX() && y == targetFish.getY()) {
                distance = Math.min(distance, depth);
                continue;
            }

            for(int i = 0; i < 4; i++) {
                int xValue = x + dx[i];
                int yValue = y + dy[i];

                if(xValue < 0 || yValue < 0 || xValue >= length || yValue >= length) continue;
                if(visitArr[yValue][xValue]) continue;

                Fish nextFish = fishArr[yValue][xValue];
                Node nextNode = nodeArr[yValue][xValue];

                if(nextFish.getSize() > startSize) continue;
                if(nextNode.getDepth() < depth + 1) continue;

                nextNode.setDepth(depth + 1);
                visitArr[yValue][xValue] = true;

                resultQueue.offer(nextNode);
            }
        }

        return distance;
    }

    private static void initNodeArr() {
        for(int height = 0; height < nodeArr.length; height++) {
            for(int width = 0; width < nodeArr.length; width++) {
                nodeArr[height][width].setDepth(Integer.MAX_VALUE);
            }
        }
    }

    private static class Fish implements Comparable<Fish> {
        private int x;
        private int y;
        private int size;

        public Fish(int x, int y, int size) {
            this.x = x;
            this.y = y;
            this.size = size;
        }

        public int getX() {
            return this.x;
        }

        public int getY() {
            return this.y;
        }

        public int getSize() {
            return this.size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        @Override
        public int compareTo(Fish fish) {
            return this.size - fish.getSize();
        }
    }

    private static class Shark {
        private int x;
        private int y;
        private int size;
        private int eatCount;

        public Shark(int x, int y) {
            this.x = x;
            this.y = y;
            this.size = INITIAL_SIZE;
            this.eatCount = 0;
        }

        public int getX() {
            return this.x;
        }

        public int getY() {
            return this.y;
        }

        public int getSize() {
            return this.size;
        }

        public void eatFish() {
            eatCount++;

            if(eatCount == size) {
                eatCount = 0;
                size++;
            }
        }

        public void setX(int x) {
            this.x = x;
        }

        public void setY(int y) {
            this.y = y;
        }
    }

    private static class Node {
        private int x;
        private int y;
        private int depth;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
            this.depth = Integer.MAX_VALUE;
        }

        public int getX() {
            return this.x;
        }

        public int getY() {
            return this.y;
        }

        public int getDepth() {
            return this.depth;
        }

        public void setDepth(int depth) {
            this.depth = depth;
        }
    }

    private static class SearchResult {
        private Fish bestFish;
        private int distance;

        public SearchResult(Fish bestFish, int distance) {
            this.bestFish = bestFish;
            this.distance = distance;
        }

        public Fish getBestFish() {
            return this.bestFish;
        }

        public int getDistance() {
            return this.distance;
        }
    }
}