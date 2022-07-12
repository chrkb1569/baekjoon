import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

class Applicant implements Comparable<Applicant>{
    private int rank1;
    private int rank2;

    public Applicant(int r1, int r2) {
        this.rank1 = r1;
        this.rank2 = r2;
    }


    public int getRank1() {
        return rank1;
    }

    public int getRank2() {
        return rank2;
    }

    @Override
    public int compareTo(Applicant o) {
        if(this.getRank1() > o.getRank1()) {
            return 1;
        }
        else return -1;
    }
}

public class Select_Employee {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int total = Integer.parseInt(br.readLine());

        for(int i = 0; i < total; i++) {
            int person = Integer.parseInt(br.readLine());

            ArrayList<Applicant> lst = new ArrayList<>();

            for(int u = 0; u < person; u++) {
                st = new StringTokenizer(br.readLine(), " ");
                lst.add(new Applicant(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
            }

            Collections.sort(lst);

            int min = lst.get(0).getRank2();
            int count = person;

            for(int q = 1; q < person; q++) {
                if(lst.get(q).getRank2() > min) {
                    count = count-1;
                }

                else if(lst.get(q).getRank2() <= min) {
                    min = lst.get(q).getRank2();
                }
            }

            System.out.println(count);
        }

    }
}