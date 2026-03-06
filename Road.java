import java.util.Objects;

/**
 * Reprezinta un drum care conecteaza doua locatii distincte.
 */
public class Road {
    private String name;
    private double length;
    private double speedLimit;
    private RoadType type;
    private Location node1;
    private Location node2;

    /**
     * Construieste un nou drum in retea.
     * * @param name Numele drumului
     * @param length Lungimea drumului
     * @param speedLimit Limita maxima de viteza admisa
     * @param type Tipul drumului (din enum RoadType)
     * @param node1 Prima locatie conectata de drum
     * @param node2 A doua locatie conectata de drum
     */
    public Road(String name, double length, double speedLimit, RoadType type, Location node1, Location node2) {
        this.name = name;
        this.length = length;
        this.speedLimit = speedLimit;
        this.type = type;
        this.node1 = node1;
        this.node2 = node2;
    }

    /** @return Numele drumului */
    public String getName() {
        return name;
    }

    /** @param name Numele drumului de setat */
    public void setName(String name) {
        this.name = name;
    }

    /** @return Lungimea drumului */
    public double getLength() {
        return length;
    }

    /** @param length Lungimea de setat */
    public void setLength(double length) {
        this.length = length;
    }

    /** @return Limita de viteza */
    public double getSpeedLimit() {
        return speedLimit;
    }

    /** @param speedLimit Limita de viteza de setat */
    public void setSpeedLimit(double speedLimit) {
        this.speedLimit = speedLimit;
    }

    /** @return Tipul drumului */
    public RoadType getType() {
        return type;
    }

    /** @param type Tipul drumului de setat */
    public void setType(RoadType type) {
        this.type = type;
    }

    /** @return Prima locatie a drumului */
    public Location getNode1() {
        return node1;
    }

    /** @param node1 Prima locatie de setat */
    public void setNode1(Location node1) {
        this.node1 = node1;
    }

    /** @return A doua locatie a drumului */
    public Location getNode2() {
        return node2;
    }

    /** @param node2 A doua locatie de setat */
    public void setNode2(Location node2) {
        this.node2 = node2;
    }

    /**
     * Verifica egalitatea intre drumul curent si un alt obiect.
     * * @param o Obiectul comparat
     * @return true daca au aceeasi lungime, limita de viteza, nume si tip
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Road road = (Road) o;
        return Double.compare(road.length, length) == 0 &&
                Double.compare(road.speedLimit, speedLimit) == 0 &&
                Objects.equals(name, road.name) &&
                type == road.type;
    }

    /**
     * Calculeaza codul hash pe baza proprietatilor drumului.
     * * @return Hash-ul drumului
     */
    @Override
    public int hashCode() {
        return Objects.hash(name, length, speedLimit, type);
    }

    /**
     * Returneaza reprezentarea textuala a drumului si a capetelor sale.
     * * @return String formatat continand numele si locatiile pe care le conecteaza
     */
    @Override
    public String toString() {
        return name + ':' +
                ", node1=" + node1.getName() +
                ", node2=" + node2.getName() ;
    }
}