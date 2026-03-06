import java.util.Objects;

/**
 * Reprezinta o locatie abstracta dintr-o retea.
 * Este o clasa de tip sealed, restrictionand ierarhia doar la City, Airport si GasStation.
 */
public abstract sealed class Location permits City, Airport, GasStation {
    private String name;
    private String country;
    private double area;

    /**
     * Construieste o noua locatie cu detaliile specificate.
     * * @param name Numele locatiei
     * @param country Tara in care se afla locatia
     * @param area Suprafata ocupata
     */
    public Location(String name, String country, double area) {
        this.name = name;
        this.country = country;
        this.area = area;
    }

    /** @return Numele locatiei */
    public String getName() {
        return name;
    }

    /** @param name Numele de setat */
    public void setName(String name) {
        this.name = name;
    }

    /** @return Tara locatiei */
    public String getCountry() {
        return country;
    }

    /** @param country Tara de setat */
    public void setCountry(String country) {
        this.country = country;
    }

    /** @return Suprafata locatiei */
    public double getArea() {
        return area;
    }

    /** @param area Suprafata de setat */
    public void setArea(double area) {
        this.area = area;
    }

    /**
     * Verifica egalitatea intre locatia curenta si un alt obiect.
     * * @param o Obiectul comparat
     * @return true daca au aceeasi arie, nume si tara, altfel false
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Location location = (Location) o;
        return Double.compare(location.area, area) == 0 &&
                Objects.equals(name, location.name) &&
                Objects.equals(country, location.country);
    }

    /**
     * Calculeaza codul hash pe baza proprietatilor locatiei.
     * * @return Hash-ul locatiei
     */
    @Override
    public int hashCode() {
        return Objects.hash(name, country, area);
    }

    /**
     * Returneaza reprezentarea textuala a locatiei.
     * * @return String formatat cu numele, tara si aria
     */
    @Override
    public String toString() {
        return  name + ':' +
                " country='" + country + '\'' +
                ", area=" + area ;
    }
}