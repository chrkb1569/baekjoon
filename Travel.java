import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.Queue;

class Nation {
    private int nation;
    private boolean visit;
    private List<Integer> link;

    public Nation(int nation) {
        this.nation = nation;
        this.visit = false;
        link = new LinkedList<>();
    }

    public int getNation() {
        return nation;
    }

    public List<Integer> getLink() {
        return link;
    }

    public boolean isVisit() {
        return visit;
    }

    public void setVisit(boolean visit) {
        this.visit = visit;
    }
}

public class Travel {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int testCase = Integer.parseInt(br.readLine());
        int count = 0;

        for(int i = 0; i < testCase; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");

            Nation[] nation = new Nation[Integer.parseInt(st.nextToken())];

            for(int s = 0; s < nation.length; s++) {
                nation[s] = new Nation(s+1);
            }

            int plane = Integer.parseInt(st.nextToken());

            for(int j = 0; j < plane; j++) {
                st = new StringTokenizer(br.readLine(), " ");

                int value = Integer.parseInt(st.nextToken());
                int value2 = Integer.parseInt(st.nextToken());

                nation[value-1].getLink().add(value2);
                nation[value2-1].getLink().add(value);
            }

            getTravel(nation, count);
        }
    }

    public static void getTravel(Nation[] nation, int count) {
        Queue<Nation> queue = new LinkedList<>();

        queue.offer(nation[0]);

        while(!queue.isEmpty()) {
            Nation value = queue.poll();
            if(value.isVisit()) continue;

            value.setVisit(true);
            count++;

            for(Integer s : value.getLink()) {
                queue.offer(nation[s-1]);
            }
        }

        System.out.println(count-1);
    }
}