import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        // метод add(int i, T element)
        SuperArrayList<Integer> list = new SuperArrayList<>();
        list.add(0, 11);
        list.add(1, 9);
        list.add(2, 18);
        list.add(3, 31);
        list.add(4, 52);
        list.add(5, 113);
        list.add(6, 4);
        list.add(7, 1);
        list.add(8, 1);
        list.add(9, 73);
        list.add(10, 18);
        System.out.println(list);

        // метод addAll(Collection<? extends T> c)
        SuperArrayList<Integer> list2 = new SuperArrayList<>();
        list2.add(0, 1);
        list2.add(1, 115);
        list2.add(2, 48);
        list2.add(3, 31);
        list2.add(4, 22);
        System.out.println(list2);

        List<Integer> arrayList = Arrays.asList(1, 77, 38, 41);
        list2.addAll(arrayList);
        System.out.println(list2);

        list.addAll(list2);
        System.out.println(list);

        // метод clear()
        list2.clear();
        System.out.println(list2);

        // метод  get(int i)
        System.out.println(list.get(2));

        // метод isEmpty()
        System.out.println(list2.isEmpty());
        System.out.println(list.isEmpty());

        // метод remove(int i) remove(Object o)
        System.out.println(list.size());
        list.remove(2);
        System.out.println(list.get(2));
        list.remove((Integer) 115);
        System.out.println(list.size());
        System.out.println(list);

        // метод sort(Сomparator<? super T> c)

        list.sort(Integer::compareTo);
        System.out.println(list);
        list.sort(Collections.reverseOrder());
        System.out.println(list);

        SuperArrayList<Integer> list3 = new SuperArrayList<>(arrayList);
        System.out.println(list3);



    }
}
