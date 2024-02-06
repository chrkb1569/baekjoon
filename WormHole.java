import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class WormHole {
    private static List<List<Link>> resultList;
    private static int[] costArr;
    private static int MAX_VALUE = 1_000_000_000;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int testCase = Integer.parseInt(br.readLine());

        for(int i = 0; i < testCase; i++) {
            st = new StringTokenizer(br.readLine(), " ");

            int node = Integer.parseInt(st.nextToken());
            int link = Integer.parseInt(st.nextToken());
            int wormhole = Integer.parseInt(st.nextToken());

            initResultList(node);

            for(int j = 0; j < link; j++) {
                st = new StringTokenizer(br.readLine(), " ");
                mkResultList(st);
            }

            for(int j = 0; j < wormhole; j++) {
                st = new StringTokenizer(br.readLine(), " ");
                mkReverseList(st);
            }

            initCostArr(node);

            sb.append(getResultString()).append("\n");
        }

        System.out.println(sb);
    }

    private static void initResultList(int length) {
        resultList = new ArrayList<>(length + 1);

        for(int i = 0; i <= length; i++) {
            resultList.add(new ArrayList<>());
        }
    }

    private static void initCostArr(int length) {
        costArr = new int[length + 1];

        Arrays.fill(costArr, MAX_VALUE);
    }

    private static void mkResultList(StringTokenizer st) {
        int from = Integer.parseInt(st.nextToken());
        int to = Integer.parseInt(st.nextToken());
        int weight = Integer.parseInt(st.nextToken());

        resultList.get(from).add(new Link(to, weight));
        resultList.get(to).add(new Link(from, weight));
    }

    private static void mkReverseList(StringTokenizer st) {
        int from = Integer.parseInt(st.nextToken());
        int to = Integer.parseInt(st.nextToken());
        int weight = Integer.parseInt(st.nextToken());

        resultList.get(from).add(new Link(to, -1 * weight));
    }

    private static String getResultString() {
        if(checkValidation()) return "YES";
        return "NO";
    }

    private static boolean checkValidation() {
        costArr[1] = 0;
        boolean flag = false;

        for(int i = 1; i < costArr.length - 1; i++) {
            flag = false;

            for(int currentIndex = 1; currentIndex < costArr.length; currentIndex++) {
                int weight = costArr[currentIndex];

                for(Link link : resultList.get(currentIndex)) {
                    int nextLocation = link.getTo();
                    int reqWeight = link.getWeight();

                    if(costArr[nextLocation] > weight + reqWeight) {
                        costArr[nextLocation] = weight + reqWeight;
                        flag = true;
                    }
                }
            }

            if(!flag) break;
        }

        if(flag) {
            for(int currentIndex = 1; currentIndex < costArr.length; currentIndex++) {
                int weight = costArr[currentIndex];

                for(Link link : resultList.get(currentIndex)) {
                    int nextLocation = link.getTo();
                    int reqWeight = link.getWeight();

                    if(costArr[nextLocation] > weight + reqWeight) return true;
                }
            }
        }

        return false;
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