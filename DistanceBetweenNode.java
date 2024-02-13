import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class DistanceBetweenNode {
    private static List<List<Link>> valueList;
    private static boolean[] visitArr;
    private static int distance = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        initValueArr(N);

        for(int i = 0; i < N-1; i++) {
            st = new StringTokenizer(br.readLine(), " ");

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            valueList.get(from).add(new Link(to, weight));
            valueList.get(to).add(new Link(from, weight));
        }

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            visitArr = new boolean[N + 1];
            visitArr[from] = true;

            getDistance(from, to, 0);

            sb.append(distance).append("\n");
        }

        System.out.println(sb);
    }

    private static void initValueArr(int length) {
        valueList = new ArrayList<>(length + 1);

        for(int i = 0; i <= length; i++) {
            valueList.add(new ArrayList<>());
        }
    }


    private static void getDistance(int current, int destination, int totalDistance) {
        if(current == destination) {
            distance = totalDistance;
            return;
        }

        for(Link link : valueList.get(current)) {
            int to = link.getTo();
            int weight = link.getWeight();

            if(visitArr[to]) continue;

            visitArr[to] = true;
            getDistance(to, destination, totalDistance + weight);
        }
    }

    private static class Link {
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
    }
}