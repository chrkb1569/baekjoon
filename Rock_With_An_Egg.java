import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Rock_With_An_Egg {
    private static Egg[] resultArr;
    private static int maxValue = Integer.MIN_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        initResultArr(N);

        for(int egg = 0; egg < N; egg++) {
            st = new StringTokenizer(br.readLine(), " ");

            int endurance = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            resultArr[egg] = new Egg(endurance, weight);
        }

        getPermutation(0, 0);

        System.out.println(maxValue);
    }

    private static void initResultArr(int length) {
        resultArr = new Egg[length];
    }

    private static void getPermutation(int currentIndex, int count) {
        if(currentIndex == resultArr.length) {
            maxValue = Math.max(maxValue, count);
            return;
        }

        if(resultArr[currentIndex].getEndurance() <= 0 || count == resultArr.length - 1) {
            getPermutation(currentIndex + 1, count);
            return;
        }

        int eggCount = count;

        for(int index = 0; index < resultArr.length; index++) {
            if(index == currentIndex) continue;
            if(resultArr[index].getEndurance() <= 0) continue;

            hitEgg(currentIndex, index);

            if(resultArr[currentIndex].getEndurance() <= 0) count++;
            if(resultArr[index].getEndurance() <= 0) count++;

            getPermutation(currentIndex + 1, count);

            initEgg(currentIndex, index);
            count = eggCount;
        }
    }

    private static void hitEgg(int currentIndex, int targetIndex) {
        Egg current = resultArr[currentIndex];
        Egg target = resultArr[targetIndex];

        int currentEndurance = current.getEndurance();
        int currentWeight = current.getWeight();

        int targetEndurance = target.getEndurance();
        int targetWeight = target.getWeight();

        current.setEndurance(currentEndurance - targetWeight);
        target.setEndurance(targetEndurance - currentWeight);
    }

    private static void initEgg(int currentIndex, int targetIndex) {
        Egg current = resultArr[currentIndex];
        Egg target = resultArr[targetIndex];

        int currentEndurance = current.getEndurance();
        int currentWeight = current.getWeight();

        int targetEndurance = target.getEndurance();
        int targetWeight = target.getWeight();

        current.setEndurance(currentEndurance + targetWeight);
        target.setEndurance(targetEndurance + currentWeight);
    }

    private static class Egg {
        private int endurance;
        private int weight;

        public Egg(int endurance, int weight) {
            this.endurance = endurance;
            this.weight = weight;
        }

        public int getEndurance() {
            return this.endurance;
        }

        public int getWeight() {
            return this.weight;
        }

        public void setEndurance(int endurance) {
            this.endurance = endurance;
        }
    }
}
