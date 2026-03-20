package org.example;

import com.github.javafaker.Faker;

import java.util.*;

public class Main4 {

    public static void main(String[] args) {

        Faker faker = new Faker();

        System.out.println("\n--- Create a simple example with 10 intersections. Use streams. ---");
        List<Intersection> nodes = DataGenerator.generateFakeIntersections(10, faker);
        System.out.println("Intersecții generate: " + nodes);

        System.out.println("\n--- Create a list of streets LinkedList and sort it by length. ---");
        List<Street> streets = DataGenerator.generateFakeStreets(nodes, faker);

        System.out.println("Străzi înainte de sortare:");
        streets.forEach(System.out::println);

        streets.sort(Comparator.comparingDouble(Street::getLength));

        System.out.println("\nStrăzi DUPĂ sortarea după lungime:");
        streets.forEach(System.out::println);

        System.out.println("\n--- Create a set of intersections (HashSet). Verify no duplicates. ---");
        Set<Intersection> intersectionSet = new HashSet<>(nodes);
        System.out.println("Dimensiunea Set-ului inițial: " + intersectionSet.size());

        Intersection duplicateNode = new Intersection(nodes.get(0).getName());
        boolean isAdded = intersectionSet.add(duplicateNode);

        System.out.println("A fost adăugat duplicatul (" + duplicateNode.getName() + ")? " + isAdded);
        System.out.println("Dimensiunea Set-ului după încercare: " + intersectionSet.size());


        System.out.println("\n--- Create a class that describes the city. ---");
        System.out.println("--- Use a third-party library to generate fake names. ---");
        City myCity = new City("Orașul Test");

        nodes.forEach(myCity::addIntersection);
        streets.forEach(myCity::addStreet);
        System.out.println(myCity);

        System.out.println("\n--- Using Java Stream API, write a query... ---");
        myCity.displayStreetsMeetingCriteria(1.0);

        System.out.println("\n--- Write an algorithm that returns a list of possible solutions... ---");
        NetworkAlgorithm.displayKBestRoutes(myCity, 3);
    }
}