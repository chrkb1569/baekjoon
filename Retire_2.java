import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Retire_2 {

    public static class Consulting {
        private int weight;
        private int money;

        public Consulting(int weight, int money) {
            this.weight = weight;
            this.money = money;
        }

        public int getWeight() {
            return this.weight;
        }

        public int getMoney() {
            return this.money;
        }
    }

    public static Consulting[] resultArr;
    public static int[] dpArr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int day = Integer.parseInt(br.readLine());

        initResultArr(day);
        initDpArr(day);

        for(int i = 1; i <= day; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            mkResultArr(i, st);
        }

        mkDpArr();

        System.out.println(dpArr[day + 1]);
    }

    public static void mkDpArr() {
        for(int i = 1; i < resultArr.length; i++) {
            if(dpArr[i] == 0) dpArr[i] = dpArr[i-1];

            dpArr[i] = Math.max(dpArr[i], dpArr[i-1]);

            Consulting value = resultArr[i];

            if(i + value.getWeight() >= dpArr.length) continue;
            dpArr[i + value.getWeight()] = Math.max(dpArr[i] + value.getMoney(), dpArr[i + value.getWeight()]);
        }
    }
    public static void mkResultArr(int index, StringTokenizer st) {
        int weight = Integer.parseInt(st.nextToken());
        int money = Integer.parseInt(st.nextToken());

        resultArr[index] = new Consulting(weight, money);
    }

    public static void initResultArr(int value) {
        resultArr = new Consulting[value + 2];

        resultArr[0] = new Consulting(0, 0);
        resultArr[value + 1] = new Consulting(0, 0);
    }

    public static void initDpArr(int value) {
        dpArr = new int[value + 2];
    }
}
