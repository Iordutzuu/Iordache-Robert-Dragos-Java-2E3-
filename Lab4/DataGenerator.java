package org.example;

import com.github.javafaker.Faker;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class DataGenerator {

    public static List<Intersection> generateFakeIntersections(int count, Faker faker) {
        return IntStream.rangeClosed(1, count)
                .mapToObj(i -> new Intersection(faker.address().cityName()))
                .collect(Collectors.toList());
    }

    public static List<Street> generateFakeStreets(List<Intersection> nodes, Faker faker) {
        List<Street> streets = new LinkedList<>();

        streets.add(new Street(faker.address().streetName(), 2.5, nodes.get(0), nodes.get(1)));
        streets.add(new Street(faker.address().streetName(), 1.2, nodes.get(1), nodes.get(2)));
        streets.add(new Street(faker.address().streetName(), 3.0, nodes.get(2), nodes.get(3)));
        streets.add(new Street(faker.address().streetName(), 0.8, nodes.get(3), nodes.get(0)));
        streets.add(new Street(faker.address().streetName(), 1.5, nodes.get(0), nodes.get(2)));
        streets.add(new Street(faker.address().streetName(), 2.0, nodes.get(1), nodes.get(3)));

        return streets;
    }
}