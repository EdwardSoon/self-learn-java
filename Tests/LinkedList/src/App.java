import java.util.LinkedList;

public class App {
    public static void main(String[] args) throws Exception {
        LinkedList<Integer> ll = new LinkedList<>();
        ll.addFirst(10);
        ll.addFirst(12);
        ll.add(100);
        ll.add(100);
        System.out.println(ll);
    }
}
