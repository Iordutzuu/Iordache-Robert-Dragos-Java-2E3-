/**
 * Reprezinta un oras cu un numar specific de locuitori.
 */
public final class City extends Location {
    private int population;

    /**
     * Constructor pentru City.
     * @param name Numele orasului.
     * @param country Tara.
     * @param area Suprafata.
     * @param population Populatia orasului.
     */
    public City(String name, String country, double area, int population) {
        super(name, country, area);
        this.population = population;
    }

    /** @return Numarul de locuitori. */
    public int getPopulation() { return population; }
    /** @param population Populatia de setat. */
    public void setPopulation(int population) { this.population = population; }
}