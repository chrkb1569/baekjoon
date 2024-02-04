import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Party {
    private static List<List<Link>> originList;
    private static List<List<Link>> reverseList;
    private static int[] originCostArr;
    private static int[] reverseCostArr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int node = Integer.parseInt(st.nextToken());
        int link = Integer.parseInt(st.nextToken());
        int partyLocation = Integer.parseInt(st.nextToken());

        initList(node);
        initArr(node);

        for(int i = 0; i < link; i++) {
            st = new StringTokenizer(br.readLine(), " ");

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            originList.get(from).add(new Link(to, weight));
            reverseList.get(to).add(new Link(from, weight));
        }

        mkOriginCostArr(partyLocation);
        mkReverseCostArr(partyLocation);

        printMaxCost();
    }

    private static void initList(int length) {
        originList = new ArrayList<>(length + 1);
        reverseList = new ArrayList<>(length + 1);

        for(int i = 0; i < length + 1; i++) {
            originList.add(new ArrayList<>());
            reverseList.add(new ArrayList<>());
        }
    }

    private static void initArr(int length) {
        originCostArr = new int[length + 1];
        reverseCostArr = new int[length + 1];

        Arrays.fill(originCostArr, Integer.MAX_VALUE);
        Arrays.fill(reverseCostArr, Integer.MAX_VALUE);
    }

    private static void mkOriginCostArr(int start) {
        PriorityQueue<Link> resultQueue = new PriorityQueue<>();
        boolean[] visitArr = new boolean[originCostArr.length];
        visitArr[start] = true;
        originCostArr[start] = 0;
        resultQueue.offer(new Link(start, 0));

        while(!resultQueue.isEmpty()) {
            Link link = resultQueue.poll();

            int current = link.getTo();
            int weight = link.getWeight();

            for(Link nextLink : originList.get(current)) {
                int nextLocation = nextLink.getTo();
                int reqWeight = nextLink.getWeight();

                if(visitArr[nextLocation] && originCostArr[nextLocation] < weight + reqWeight) continue;

                visitArr[nextLocation] = true;
                originCostArr[nextLocation] = weight + reqWeight;

                resultQueue.offer(new Link(nextLocation, weight + reqWeight));
            }
        }
    }

    private static void mkReverseCostArr(int start) {
        PriorityQueue<Link> resultQueue = new PriorityQueue<>();
        boolean[] visitArr = new boolean[reverseCostArr.length];
        visitArr[start] = true;
        reverseCostArr[start] = 0;
        resultQueue.offer(new Link(start, 0));

        while(!resultQueue.isEmpty()) {
            Link link = resultQueue.poll();

            int current = link.getTo();
            int weight = link.getWeight();

            for(Link nextLink : reverseList.get(current)) {
                int nextLocation = nextLink.getTo();
                int reqWeight = nextLink.getWeight();

                if(visitArr[nextLocation] && reverseCostArr[nextLocation] < weight + reqWeight) continue;

                visitArr[nextLocation] = true;
                reverseCostArr[nextLocation] = weight + reqWeight;

                resultQueue.offer(new Link(nextLocation, weight + reqWeight));
            }
        }
    }

    private static void printMaxCost() {
        int maxValue = Integer.MIN_VALUE;

        for(int i = 1; i < originCostArr.length; i++) {
            maxValue = Math.max(maxValue, originCostArr[i] + reverseCostArr[i]);
        }

        System.out.println(maxValue);
    }

    private static class Link implements Comparable<Link> {
        private int to;
        private int weight;

        public Link(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }

        public int getTo() {
            return this.to;
        }

        public int getWeight() {
            return this.weight;
        }

        @Override
        public int compareTo(Link link) {
            return this.weight - link.getWeight();
        }
    }
}