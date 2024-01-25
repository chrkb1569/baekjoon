import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class MakeStar {
    private static Star[] starArr;
    private static int[] parentArr;
    private static PriorityQueue<Distance> resultQueue = new PriorityQueue<>();
    private static double minWeight = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int star = Integer.parseInt(br.readLine());

        initStartArr(star);
        initParentArr(star);

        for(int i = 0; i < star; i++) {
            st = new StringTokenizer(br.readLine(), " ");

            double x = Double.parseDouble(st.nextToken());
            double y = Double.parseDouble(st.nextToken());

            starArr[i] = new Star(x, y);
        }

        mkResultQueue();
        mkParentArr();

        System.out.printf("%.2f", minWeight);
    }

    private static void initStartArr(int length) {
        starArr = new Star[length];
    }

    private static void initParentArr(int length) {
        parentArr = new int[length];

        for(int i = 0; i < parentArr.length; i++) {
            parentArr[i] = i;
        }
    }

    private static void mkResultQueue() {
        for(int i = 0; i < starArr.length; i++) {
            Star star = starArr[i];
            double x_1 = star.getX();
            double y_1 = star.getY();

            for(int j = i+1; j < starArr.length; j++) {
                Star nextStar = starArr[j];

                double x_2 = nextStar.getX();
                double y_2 = nextStar.getY();
                double weight = Math.sqrt(Math.pow(x_2 - x_1, 2) + Math.pow(y_2 - y_1, 2));

                resultQueue.offer(new Distance(i, j, weight));
            }
        }
    }

    private static void mkParentArr() {
        while(!resultQueue.isEmpty()) {
            Distance distance = resultQueue.poll();

            int from = distance.getFrom();
            int to = distance.getTo();
            double weight = distance.getWeight();

            int parentFrom = findParent(from);
            int parentTo = findParent(to);

            if(parentFrom == parentTo) continue;
            union(from, to);
            minWeight += weight;
        }
    }

    private static int findParent(int index) {
        if(parentArr[index] == index) return index;
        parentArr[index] = findParent(parentArr[index]);
        return parentArr[index];
    }

    private static void union(int indexA, int indexB) {
        int parentA = findParent(indexA);
        int parentB = findParent(indexB);

        if(parentA < parentB) {
            parentArr[parentB] = parentA;
            return;
        }

        parentArr[parentA] = parentB;
    }

    private static class Distance implements Comparable<Distance> {
        private int from;
        private int to;
        private double weight;

        public Distance(int from, int to, double weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        public int getFrom() {
            return this.from;
        }

        public int getTo() {
            return this.to;
        }

        public double getWeight() {
            return this.weight;
        }

        @Override
        public int compareTo(Distance distance) {
            return Double.compare(this.weight, distance.getWeight());
        }
    }

    private static class Star {
        private double x;
        private double y;

        public Star(double x, double y) {
            this.x = x;
            this.y = y;
        }

        public double getX() {
            return this.x;
        }

        public double getY() {
            return this.y;
        }
    }
}
