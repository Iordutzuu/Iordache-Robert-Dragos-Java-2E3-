package org.example;
public class Street implements Comparable<Street> {
    private String name;
    private double length;
    private Intersection start;
    private Intersection end;

    public Street(String name, double length, Intersection start, Intersection end) {
        this.name = name;
        this.length = length;
        this.start = start;
        this.end = end;
    }

    public String getName() {
        return name;
    }

    public double getLength() {
        return length;
    }

    public Intersection getStart() {
        return start;
    }

    public Intersection getEnd() {
        return end;
    }

    @Override
    public int compareTo(Street other) {
        return this.name.compareTo(other.name);
    }

    @Override
    public String toString() {
        return "Street{" + name + ", length=" + length + ", joins: " + start + "-" + end + "}";
    }
}