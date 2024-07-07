import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class ConveyorBelt {
    private static Conveyor conveyor;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int stage = 0;

        st = new StringTokenizer(br.readLine(), " ");

        Belt[] beltArr = getBeltArr(N, st);

        conveyor = new Conveyor(beltArr, K);

        while(true) {
            stage++;

            conveyor.moveConveyorBelt();
            conveyor.moveRobot();
            conveyor.putRobot();

            if(conveyor.checkCondition()) break;
        }

        System.out.println(stage);
    }

    private static Belt[] getBeltArr(int length, StringTokenizer st) {
        Belt[] beltArr = new Belt[2 * length];
        int index = 0;

        for(;st.hasMoreTokens();) {
            beltArr[index++] = new Belt(Integer.parseInt(st.nextToken()));
        }

        return beltArr;
    }

    private static class Conveyor {
        private List<Belt> upperBelt = new LinkedList<>();
        private List<Belt> lowerBelt = new LinkedList<>();
        private int stopCondition;

        public Conveyor(Belt[] beltArr, int stopCondition) {
            for(int index = 0; index < beltArr.length/2; index++) {
                upperBelt.add(beltArr[index]);
            }

            for(int index = beltArr.length - 1; index >= beltArr.length/2; index--) {
                lowerBelt.add(beltArr[index]);
            }

            this.stopCondition = stopCondition;
        }

        public List<Belt> getUpperBelt() {
            return this.upperBelt;
        }

        public List<Belt> getLowerBelt() {
            return this.lowerBelt;
        }

        public void moveConveyorBelt() {
            Belt toLower = upperBelt.remove(upperBelt.size() - 1);
            Belt toUpper = lowerBelt.remove(0);

            upperBelt.add(0, toUpper);
            lowerBelt.add(lowerBelt.size(), toLower);
        }

        public void moveRobot() {
            if(upperBelt.get(upperBelt.size()-1).isRobot()) upperBelt.get(upperBelt.size()-1).takeRobot();

            for(int index = upperBelt.size()-1; index > 0; index--) {
                Belt currentBelt = upperBelt.get(index);
                Belt nextBelt = upperBelt.get(index - 1);

                if(currentBelt.getWeight() == 0) continue;
                if(currentBelt.isRobot()) continue;
                if(!nextBelt.isRobot()) continue;

                currentBelt.putRobot();
                nextBelt.takeRobot();
            }

            if(upperBelt.get(upperBelt.size()-1).isRobot()) upperBelt.get(upperBelt.size()-1).takeRobot();
        }

        public void putRobot() {
            Belt belt = upperBelt.get(0);

            if(belt.getWeight() > 0) belt.putRobot();
        }

        public boolean checkCondition() {
            int zeroCount = 0;

            for(Belt belt : upperBelt) {
                if(belt.getWeight() == 0) zeroCount++;
            }

            for(Belt belt : lowerBelt) {
                if(belt.getWeight() == 0) zeroCount++;
            }

            return zeroCount >= stopCondition;
        }
    }

    private static class Belt {
        private int weight;
        private boolean robot;

        public Belt(int weight) {
            this.weight = weight;
            this.robot = false;
        }

        public int getWeight() {
            return this.weight;
        }

        public boolean isRobot() {
            return this.robot;
        }

        public void putRobot() {
            this.robot = true;
            this.weight--;
        }

        public void takeRobot() {
            this.robot = false;
        }
    }
}
