package by.astondev.homework2.taskcollection.runner;

import by.astondev.homework2.taskcollection.CustomLinkedList;

public class MainLinkedList {
    public static void main(String[] args) {
        CustomLinkedList<Integer> list = new CustomLinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);
        list.add(7);
        System.out.println(list.size());
        System.out.println();

        for (Integer i : list) {
            System.out.println(i);
        }

        System.out.println("list.get(0) = " + list.get(0));
        System.out.println("list.get(2) = " + list.get(2));
        System.out.println("list.get(5) = " + list.get(5));
        System.out.println(list.get(list.size()/2));
        System.out.println(list);
   //     System.out.println("list.get(7) = " + list.get(7));
        System.out.println(list);
        list.addFirst(1222);
        list.add(11111);
        System.out.println("list.size() = " + list.size());
        System.out.println(list);

        System.out.println("list.removeFirst() = " + list.removeFirst());
        System.out.println(list);

        System.out.println("list.removeLast() = " + list.removeLast());
        System.out.println(list);

        list.remove(1);
        System.out.println(list);
        System.out.println(list.size());

        CustomLinkedList<Integer> list2 = new CustomLinkedList<>();
        list2.add(2222);
        list2.add(3333);
        list2.add(4444);
        list.addAll(list2);
        System.out.println("list = " + list);
    }
}
