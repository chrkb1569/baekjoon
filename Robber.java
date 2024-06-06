import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Robber {
    private static Jewerly[] resultArr;
    private static int[] bagArr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int N = Integer.parseInt(st.nextToken()); // 보석의 개수
        int K = Integer.parseInt(st.nextToken()); // 가방의 개수

        initResultArr(N);
        initBagArr(K);

        for(int item = 0; item < N; item++) {
            st = new StringTokenizer(br.readLine(), " ");

            int weight = Integer.parseInt(st.nextToken());
            int price = Integer.parseInt(st.nextToken());

            resultArr[item] = new Jewerly(weight, price);
        }

        for(int bag = 0; bag < K; bag++) {
            bagArr[bag] = Integer.parseInt(br.readLine());
        }

        long maxProfit = getMaxProfit();

        System.out.println(maxProfit);
    }

    private static void initResultArr(int length) {
        resultArr = new Jewerly[length];
    }

    private static void initBagArr(int length) {
        bagArr = new int[length];
    }

    private static long getMaxProfit() {
        PriorityQueue<Jewerly> resultQueue = new PriorityQueue<>();
        PriorityQueue<Jewerly> binQueue = new PriorityQueue<>((o1, o2) -> o2.getPrice() - o1.getPrice());
        long profit = 0;

        Arrays.sort(bagArr);
        resultQueue.addAll(Arrays.asList(resultArr));

        for(int index = 0; index < bagArr.length; index++) {
            int bagLimit = bagArr[index];
            int maxPrice = Integer.MIN_VALUE;
            Jewerly target = null;

            while(!resultQueue.isEmpty()) {
                Jewerly jewerly = resultQueue.poll();

                int weight = jewerly.getWeight();
                int price = jewerly.getPrice();

                if(weight > bagLimit) {
                    resultQueue.add(jewerly);
                    break;
                }

                if(price <= maxPrice) {
                    binQueue.add(jewerly);
                    continue;
                }

                if(target != null) {
                    binQueue.add(target);
                    target = jewerly;
                    maxPrice = price;
                    continue;
                }

                target = jewerly;
                maxPrice = price;
            }

            if(target == null && binQueue.isEmpty()) continue;
            if(binQueue.isEmpty()) {
                profit += maxPrice;
                continue;
            }

            Jewerly jewerly = binQueue.peek();

            int weight = jewerly.getWeight();
            int price = jewerly.getPrice();

            if(target == null && bagLimit >= weight && price > maxPrice) {
                maxPrice = price;
                binQueue.poll();
            }
            else if(price > maxPrice && bagLimit >= weight) {
                binQueue.add(target);
                maxPrice = price;
                binQueue.poll();
            }

            profit += maxPrice;
        }

        return profit;
    }

    private static class Jewerly implements Comparable<Jewerly> {
        private int weight;
        private int price;

        public Jewerly(int weight, int price) {
            this.weight = weight;
            this.price = price;
        }

        public int getWeight() {
            return this.weight;
        }

        public int getPrice() {
            return this.price;
        }

        @Override
        public int compareTo(Jewerly jewerly) {
            if(this.weight == jewerly.getWeight()) return this.price - jewerly.getPrice();
            return this.weight - jewerly.getWeight();
        }
    }
}
