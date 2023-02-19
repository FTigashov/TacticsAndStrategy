package task2;

import java.util.*;
public class StartApp {
    public static void main(String[] args) {
        // Добавление, получение, удаление
        MyLinkedList<Integer> list = new MyLinkedList<>();
        list.addFirst(1);
        list.addFirst(2);
        list.addFirst(3);
        list.addFirst(4);
        list.addLast(5);

        // Тестирование
        System.out.println(list);
        System.out.println(list.get(1));
        list.remove(1);
        System.out.println(list);
    }
}
