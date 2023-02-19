package task2;

import java.util.*;
public class StartApp {
    public static void main(String[] args) {
        MyLinkedList<Integer> list = new MyLinkedList<>();
        MyArrayList<Integer> array = new MyArrayList<>();

        list.addFirst(1);
        list.addFirst(2);
        list.addFirst(3);
        list.addFirst(4);
        list.addLast(5);

        System.out.println(list);
        System.out.println(list.get(1));
        list.remove(1);
        System.out.println(list);

        array.add(1);
        array.add(2);
        array.add(3);
        array.add(4);
        array.add(5);

        System.out.println(array);

        System.out.println(array.get(0));
        array.remove(1);
        System.out.println(array);
    }
}
