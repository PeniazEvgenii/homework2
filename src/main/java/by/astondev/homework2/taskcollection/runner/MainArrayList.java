package by.astondev.homework2.taskcollection.runner;

import by.astondev.homework2.taskcollection.CustomArrayList;

import java.util.ArrayList;
import java.util.List;

public class MainArrayList {
    public static void main(String[] args) {
        //Есть тест JUnit
        CustomArrayList<Integer> arrayList = new CustomArrayList<>(2);
        List<Integer> l = new ArrayList<>();
        arrayList.add(1);
        arrayList.add(2);
        arrayList.add(3);
        arrayList.add(4);
        arrayList.add(5);
  //      arrayList.add(null);
        arrayList.add(6);
        System.out.println("arrayList = " + arrayList);

        System.out.println("arrayList.getSize() = " + arrayList.size());

        System.out.println(arrayList.contains(6));
        System.out.println(arrayList.contains(7));
        System.out.println(arrayList.getFirst());
        System.out.println(arrayList.getLast());

        System.out.println("arrayList.remove(0) = " + arrayList.remove(0));
        System.out.println("arrayList.getSize() = " + arrayList.size());
        System.out.println("arrayList.remove(2) = " + arrayList.remove(Integer.valueOf(2)));
        System.out.println(arrayList.getFirst());
        System.out.println("arrayList.getSize() = " + arrayList.size());

        System.out.println("arrayList = " + arrayList);

        CustomArrayList<Integer> objectCustomArrayList = new CustomArrayList<>();
        objectCustomArrayList.add(111);
        objectCustomArrayList.add(102);
        objectCustomArrayList.add(103);
        System.out.println(objectCustomArrayList);

        arrayList.addAll(objectCustomArrayList);

        System.out.println(arrayList);

        for (Number number : arrayList) {
            System.out.println(number);
        }

        System.out.println(arrayList);
    }
}