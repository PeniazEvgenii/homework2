package by.astondev.homework2.taskcollection.runner;

import by.astondev.homework2.taskcollection.CustomHashSet;

public class MainHashSet {
    public static void main(String[] args) {
        //JUnit test есть
        CustomHashSet<Integer> hashSet = new CustomHashSet<>();
        System.out.println("hashSet.add(1) = " + hashSet.add(1));
        System.out.println("hashSet.add(2) = " + hashSet.add(2));
        System.out.println("hashSet.add(3) = " + hashSet.add(3));
        System.out.println("hashSet.add(4) = " + hashSet.add(4));
        System.out.println("hashSet.add(5) = " + hashSet.add(5));
        System.out.println("hashSet.add(6) = " + hashSet.add(6));
        System.out.println("hashSet.add(7) = " + hashSet.add(7));
        System.out.println("hashSet.add(8) = " + hashSet.add(8));
        System.out.println("hashSet.add(9) = " + hashSet.add(9));
        System.out.println("hashSet.add(10) = " + hashSet.add(10));
        System.out.println("hashSet.add(11) = " + hashSet.add(19));

        for (Integer i : hashSet) {
            System.out.println(i);
        }
        System.out.println("hashSet.size() = " + hashSet.size());

        System.out.println("hashSet.toString() = " + hashSet);
        hashSet.remove(10);
        System.out.println("hashSet.remove(15) = " + hashSet.remove(15));
        System.out.println("hashSet.remove(1) = " + hashSet.remove(1));
        System.out.println("hashSet.remove(2) = " + hashSet.remove(2));

        System.out.println("hashSet.toString() = " + hashSet);
        System.out.println("hashSet.size() = " + hashSet.size());
    }
}
