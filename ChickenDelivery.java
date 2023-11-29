import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

public class ChickenDelivery {
    private static Node[][] resultArr;
    private static List<Node> houseList = new LinkedList<>();
    private static List<Node> chickenList = new LinkedList<>();
    private static int result = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] infoArr = br.readLine().split(" ");

        int N = Integer.parseInt(infoArr[0]);
        int M = Integer.parseInt(infoArr[1]);

        initResultArr(N);

        for(int i = 0; i < N; i++) {
            infoArr = br.readLine().split(" ");
            mkResultArr(infoArr, i);
        }

        boolean[] visit = new boolean[chickenList.size()];

        getPermutation(visit, 0, 0, M);

        System.out.println(result);
    }

    private static void initResultArr(int N) {
        resultArr = new Node[N][N];
    }

    private static void mkResultArr(String[] infoArr, int height) {
        for(int i = 0; i < infoArr.length; i++) {
            int value = Integer.parseInt(infoArr[i]);

            Node node = new Node(i, height);

            if(value == 1) houseList.add(node);
            if(value == 2) chickenList.add(node);
        }
    }

    private static void getPermutation(boolean[] visit, int start, int current, int total) {
        if(current == total) {
            int distanceFromHouse = getDistanceFromHouse(visit);
            result = Math.min(result, distanceFromHouse);
            return;
        }

        for(int i = start; i < visit.length; i++) {
            if(!visit[i]) {
                visit[i] = true;
                getPermutation(visit, i + 1, current + 1, total);
                visit[i] = false;
            }
        }
    }

    private static int getDistanceFromHouse(boolean[] chickens) {
        int resultDistance = Integer.MAX_VALUE;
        int distance = 0;

        for(int i = 0; i < houseList.size(); i++) {
            Node house = houseList.get(i);

            int houseX = house.getX();
            int houseY = house.getY();

            for(int j = 0; j < chickens.length; j++) {
                if(!chickens[j]) continue;

                Node chicken = chickenList.get(j);

                int chickenX = chicken.getX();
                int chickenY = chicken.getY();

                resultDistance = Math.min(resultDistance, Math.abs(chickenX - houseX) + Math.abs(chickenY - houseY));
            }

            distance += resultDistance;
            resultDistance = Integer.MAX_VALUE;
        }

        return distance;
    }

    private static class Node {
        private int x;
        private int y;

        public Node(int x, int y) {
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
