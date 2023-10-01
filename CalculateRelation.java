import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class CalculateRelation {
    public static Person[] resultArr;
    public static boolean[] visitArr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int person = Integer.parseInt(br.readLine());

        initArr(person);

        String[] personInfo = br.readLine().split(" ");
        int start = Integer.parseInt(personInfo[0]);
        int end = Integer.parseInt(personInfo[1]);

        int testCase = Integer.parseInt(br.readLine());

        for(int i = 0; i < testCase; i++) {
            String[] relationInfo = br.readLine().split(" ");

            int p1 = Integer.parseInt(relationInfo[0]);
            int p2 = Integer.parseInt(relationInfo[1]);

            resultArr[p1].getLink().add(p2);
            resultArr[p2].getLink().add(p1);
        }

        printStatus(start, end);
    }

    public static void printStatus(int start, int end) {
        getRelation(start, end);

        int depth = resultArr[end].getDepth();

        if(depth != Integer.MAX_VALUE) System.out.println(depth);
        else System.out.println(-1);
    }

    public static void getRelation(int start, int end) {
        Queue<Person> resultQueue = new LinkedList<>();

        Person startPerson = resultArr[start];
        startPerson.setDepth(0);
        resultQueue.offer(startPerson);

        while(!resultQueue.isEmpty()) {
            Person value = resultQueue.poll();

            int number = value.getNumber();
            int depth = value.getDepth();

            if(number == end) return;
            if(visitArr[number]) continue;

            visitArr[number] = true;

            for(int index : value.getLink()) {
                Person insertPerson = resultArr[index];

                if(insertPerson.getDepth() > depth) {
                    insertPerson.setDepth(depth + 1);
                    resultQueue.add(insertPerson);
                }
            }
        }
    }

    public static void initArr(int length) {
        resultArr = new Person[length + 1];
        visitArr = new boolean[length + 1];

        for(int i = 1; i < length + 1; i++) {
            resultArr[i] = new Person(i);
        }
    }

    public static class Person {
        private int number;
        private int depth;
        private List<Integer> link = new LinkedList<>();

        public Person(int number) {
            this.number = number;
            this.depth = Integer.MAX_VALUE;
        }

        public int getNumber() {
            return this.number;
        }

        public int getDepth() {
            return this.depth;
        }

        public List<Integer> getLink() {
            return this.link;
        }

        public void setDepth(int depth) {
            this.depth = Math.min(depth, this.depth);
        }
    }
}
