import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Queue;
import java.util.LinkedList;

public class StartLink {
    public static boolean[] visit;
    public static int minValue = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] infoArr = br.readLine().split(" ");

        int floor = Integer.parseInt(infoArr[0]);
        int current = Integer.parseInt(infoArr[1]);
        int target = Integer.parseInt(infoArr[2]);
        int up = Integer.parseInt(infoArr[3]);
        int down = Integer.parseInt(infoArr[4]);

        initVisit(floor);
        getResult(floor, current, target, up, down);

        printResult();
    }

    public static void printResult() {
        if(minValue == Integer.MAX_VALUE) System.out.println("use the stairs");
        else System.out.println(minValue);
    }

    public static void getResult(int floor, int current, int target, int up, int down) {
        Queue<Floor> resultQueue = new LinkedList<>();
        Floor offerValue = new Floor(current);
        offerValue.setDepth(0);

        resultQueue.offer(offerValue);

        while(!resultQueue.isEmpty()) {
            Floor value = resultQueue.poll();
            int floorValue = value.getNumber();
            int depth = value.getDepth();

            if(floorValue == target) {
                minValue = Math.min(minValue, depth);
                break;
            }
            if(floorValue > floor || floorValue < 1) continue;
            if(visit[floorValue]) continue;

            visit[floorValue] = true;

            if(up != 0) {
                Floor upValue = new Floor(floorValue + up);
                upValue.setDepth(depth + 1);
                resultQueue.offer(upValue);
            }

            if(down != 0) {
                Floor downValue = new Floor(floorValue - down);
                downValue.setDepth(depth + 1);
                resultQueue.offer(downValue);
            }
        }
    }

    public static void initVisit(int length) {
        visit = new boolean[length + 1];
    }

    public static class Floor {
        private int number;
        private int depth;

        public Floor(int number) {
            this.number = number;
            this.depth = Integer.MAX_VALUE;
        }

        public int getNumber() {
            return this.number;
        }

        public int getDepth() {
            return this.depth;
        }

        public void setDepth(int depth) {
            this.depth = Math.min(this.depth, depth);
        }
    }
}
