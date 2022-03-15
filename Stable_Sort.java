import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

class Member implements Comparable {
    private String name;
    private int age;

    public Member(int age, String str) {
        this.age = age;
        this.name = str;
    }

    @Override
    public String toString() {
        return age + " " + name;
    }

    @Override
    public int compareTo(Object obj) {
        Member m = (Member)obj;

        if(this.age > m.age) {
            return 1;
        }

        else if(this.age < m.age) {
            return -1;
        }

        else return 0;
    }
}

public class Stable_Sort {
    public static void main(String[] args) throws IOException  {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int num = Integer.parseInt(br.readLine());
        ArrayList<Member> arr = new ArrayList<>();

        for(int i = 0; i < num; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            arr.add(new Member(Integer.parseInt(st.nextToken()), st.nextToken()));
        }

        Collections.sort(arr);

        for(Member i : arr) {
            System.out.println(i);
        }
    }
}
