/**
 * Reprezinta o statie de alimentare cu un pret specific pentru combustibil.
 */
public final class GasStation extends Location {
    private double gasPrice;

    /**
     * Constructor pentru GasStation.
     * @param name Numele benzinariei.
     * @param country Tara.
     * @param area Suprafata.
     * @param gasPrice Pretul benzinei.
     */
    public GasStation(String name, String country, double area, double gasPrice) {
        super(name, country, area);
        this.gasPrice = gasPrice;
    }

    /** @return Pretul benzinei. */
    public double getGasPrice() { return gasPrice; }
    /** @param gasPrice Pretul benzinei de setat. */
    public void setGasPrice(double gasPrice) { this.gasPrice = gasPrice; }
}