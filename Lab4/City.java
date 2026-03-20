package org.example;

import java.util.ArrayList;
import java.util.List;

public class City {
    private String name;
    private List<Intersection> intersections;
    private List<Street> streets;

    public City(String name) {
        this.name = name;
        this.intersections = new ArrayList<>();
        this.streets = new ArrayList<>();
    }

    public void addIntersection(Intersection intersection) {
        this.intersections.add(intersection);
    }

    public void addStreet(Street street) {
        this.streets.add(street);
    }

    public String getName() {
        return name;
    }

    public List<Intersection> getIntersections() {
        return intersections;
    }

    public List<Street> getStreets() {
        return streets;
    }

    public void displayStreetsMeetingCriteria(double minLength) {
        System.out.println("Străzi mai lungi de " + minLength + " care se unesc cu cel puțin alte 3 străzi:");

        java.util.Map<Intersection, Long> intersectionDegrees = this.streets.stream()
                .flatMap(street -> java.util.stream.Stream.of(street.getStart(), street.getEnd()))
                .collect(java.util.stream.Collectors.groupingBy(i -> i, java.util.stream.Collectors.counting()));

        this.streets.stream()
                .filter(street -> street.getLength() > minLength)
                .filter(street -> {
                    long connectedAtStart = intersectionDegrees.getOrDefault(street.getStart(), 0L) - 1;
                    long connectedAtEnd = intersectionDegrees.getOrDefault(street.getEnd(), 0L) - 1;
                    return (connectedAtStart + connectedAtEnd) >= 3;
                })
                .forEach(System.out::println);
    }

    @Override
    public String toString() {
        return "City: " + name + " (Intersecții: " + intersections.size() + ", Străzi: " + streets.size() + ")";
    }
}