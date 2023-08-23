import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.Queue;

public class Wedding {

    public static Person[] resultArr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int person = Integer.parseInt(br.readLine());
        int testCase = Integer.parseInt(br.readLine());

        initResultArr(person);

        for(int i = 0; i < testCase; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int startIndex = Integer.parseInt(st.nextToken());
            int endIndex = Integer.parseInt(st.nextToken());

            resultArr[startIndex].getLink().add(endIndex);
            resultArr[endIndex].getLink().add(startIndex);
        }

        System.out.println(getResultCount());
    }

    public static int getResultCount() {
        int resultCount = 0;
        Queue<Person> queue = new LinkedList<>();
        Person person1 = resultArr[1];
        person1.setDepth(0);
        queue.offer(resultArr[1]);

        while(!queue.isEmpty()) {
            Person person = queue.poll();

            if(person.visit) continue;
            person.visitPerson();

            int depth = person.getDepth();

            for(int index : person.getLink()) {
                resultArr[index].setDepth(Math.min(depth + 1, resultArr[index].getDepth()));
                queue.offer(resultArr[index]);
            }
        }

        for(Person person : resultArr) {
            int value = person.getDepth();

            if(value <= 2) resultCount++;
        }

        return resultCount - 1;
    }

    public static void initResultArr(int value) {
        resultArr = new Person[value + 1];

        for(int i = 0; i <= value; i++) {
            resultArr[i] = new Person(i);
        }
    }

    public static class Person {
        private int number;
        private int depth;
        private boolean visit;
        private List<Integer> link;

        public Person(int number) {
            this.number = number;
            this.depth = Integer.MAX_VALUE;
            this.visit = false;
            this.link = new LinkedList<>();
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
            this.depth = depth;
        }

        public void visitPerson() {
            this.visit = true;
        }
    }
}
