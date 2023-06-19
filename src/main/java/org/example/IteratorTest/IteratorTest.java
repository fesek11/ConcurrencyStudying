package org.example.IteratorTest;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class IteratorTest {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();

        list.add("Sasha");
        list.add("Oleg");
        list.add("Xuo");

       ListIterator<String> iterator = list.listIterator();

        while (iterator.hasPrevious()) {
            System.out.println(iterator.previous());
        }

    }
}
