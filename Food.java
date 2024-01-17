import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Food {
    private static Taste[] resultArr;
    private static long minGap = Long.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        initResultArr(N);

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");

            int sour = Integer.parseInt(st.nextToken());
            int bitter = Integer.parseInt(st.nextToken());

            resultArr[i] = new Taste(sour, bitter);
        }

        for(int i = 1; i <= N; i++) {
            boolean[] visitArr = new boolean[N];
            getPermutation(visitArr, 0, 0, i);
        }

        System.out.println(minGap);
    }

    private static void initResultArr(int length) {
        resultArr = new Taste[length];
    }

    private static void getPermutation(boolean[] visit, int start, int current, int target) {
        if(current == target) {
            long resultGap = getResultGap(visit);
            minGap = Math.min(minGap, resultGap);
            return;
        }

        for(int i = start; i < visit.length; i++) {
            if(!visit[i]) {
                visit[i] = true;
                getPermutation(visit, i+1, current+1, target);
                visit[i] = false;
            }
        }
    }

    private static long getResultGap(boolean[] visit) {
        long sourResult = 1;
        long bitterResult = 0;

        for(int i = 0; i < visit.length; i++) {
            if(!visit[i]) continue;

            Taste taste = resultArr[i];

            sourResult *= taste.getSour();
            bitterResult += taste.getBitter();
        }

        return Math.abs(sourResult - bitterResult);
    }

    private static class Taste {
        private int sour;
        private int bitter;

        public Taste(int sour, int bitter) {
            this.sour = sour;
            this.bitter = bitter;
        }

        public int getSour() {
            return this.sour;
        }

        public int getBitter() {
            return this.bitter;
        }
    }
}
