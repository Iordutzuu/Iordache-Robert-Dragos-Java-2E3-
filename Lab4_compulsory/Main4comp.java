package org.example;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main4comp {
    public static void main(String[] args) {

        System.out.println("--- Crearea a 10 intersecții folosind Streams ---");
        List<Intersection> nodes = IntStream.rangeClosed(1, 10)
                .mapToObj(i -> new Intersection("I" + i))
                .collect(Collectors.toList());

        System.out.println("Intersecții generate: " + nodes);

        System.out.println("\n--- Crearea listei de străzi (LinkedList) ---");
        List<Street> streets = new LinkedList<>();
        streets.add(new Street("S1", 2.5, nodes.get(0), nodes.get(1)));
        streets.add(new Street("S2", 1.2, nodes.get(1), nodes.get(2)));
        streets.add(new Street("S3", 3.0, nodes.get(2), nodes.get(3)));
        streets.add(new Street("S4", 0.8, nodes.get(0), nodes.get(2)));

        System.out.println("Străzi înainte de sortare:");
        streets.forEach(System.out::println);

        streets.sort(Comparator.comparingDouble(Street::getLength));

        System.out.println("\nStrăzi DUPĂ sortarea după lungime:");
        streets.forEach(System.out::println);

        System.out.println("\n--- Verificarea HashSet-ului  ---");
        Set<Intersection> intersectionSet = new HashSet<>(nodes);
        System.out.println("Dimensiunea Set-ului inițial: " + intersectionSet.size());

        Intersection duplicateNode = new Intersection("I1");
        boolean isAdded = intersectionSet.add(duplicateNode);

        System.out.println("A fost adăugat duplicatul? " + isAdded);
        System.out.println("Dimensiunea Set-ului după încercarea de adăugare: " + intersectionSet.size());

        System.out.println("\nConținutul Set-ului final (observă că I1 nu apare de două ori):");
        System.out.println(intersectionSet);
    }
}