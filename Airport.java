/**
 * Reprezinta un aeroport cu un numar de terminale.
 */
public final class Airport extends Location {
    private int numberOfTerminals;

    /**
     * Constructor pentru Airport.
     * @param name Numele aeroportului.
     * @param country Tara.
     * @param area Suprafata.
     * @param numberOfTerminals Numarul de terminale.
     */
    public Airport(String name, String country, double area, int numberOfTerminals) {
        super(name, country, area);
        this.numberOfTerminals = numberOfTerminals;
    }

    /** @return Numarul de terminale. */
    public int getNumberOfTerminals() { return numberOfTerminals; }
    /** @param numberOfTerminals Numarul de terminale de setat. */
    public void setNumberOfTerminals(int numberOfTerminals) { this.numberOfTerminals = numberOfTerminals; }
}