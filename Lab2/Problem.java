/**
 * Gestioneaza modelul intregii retele, adaugarea elementelor si validarea acestora.
 * Totodata contine si un algoritm de cautare a unei rute valide.
 */
public class Problem {
    private Location[] locations = new Location[100];
    private Road[] roads = new Road[100];
    private int locCount = 0;
    private int roadCount = 0;

    /**
     * Adauga o locatie noua in problema.
     * Daca locatia exista deja, se va afisa un mesaj in consola si nu va fi adaugata.
     * * @param loc Locatia pe care dorim sa o adaugam
     */
    public void addLocation(Location loc) {
        for (int i = 0; i < locCount; i++) {
            if (locations[i].equals(loc)) {
                System.out.println("Locatia " + loc.getName() + " exista deja!");
                return;
            }
        }
        locations[locCount++] = loc;
    }

    /**
     * Adauga un drum nou in problema.
     * Daca drumul exista deja, se va afisa un mesaj in consola si nu va fi adaugat.
     * * @param road Drumul pe care dorim sa il adaugam
     */
    public void addRoad(Road road) {
        for (int i = 0; i < roadCount; i++) {
            if (roads[i].equals(road)) {
                System.out.println("Drumul " + road.getName() + " exista deja!");
                return;
            }
        }
        roads[roadCount++] = road;
    }

    /**
     * Verifica daca reteaua de drumuri este complet valida.
     * O retea este valida daca toate capetele drumurilor se regasesc in lista de locatii.
     * * @return true daca problema este consistenta, false altfel
     */
    public boolean isValid() {
        for (int i = 0; i < roadCount; i++) {
            boolean found1 = false;
            boolean found2 = false;
            for (int j = 0; j < locCount; j++) {
                if (roads[i].getNode1().equals(locations[j])) found1 = true;
                if (roads[i].getNode2().equals(locations[j])) found2 = true;
            }
            if (!found1 || !found2) return false;
        }
        return true;
    }

    /**
     * Verifica posibilitatea de a ajunge dintr-o locatie in alta.
     * Metoda initializeaza un vector de elemente vizitate si porneste o cautare in adancime (DFS).
     * * @param start Locatia de pornire
     * @param end Locatia de sosire (destinatia)
     * @return true daca exista cel putin un traseu valid de la start la end
     */
    public boolean canReach(Location start, Location end) {
        boolean[] visited = new boolean[locCount];
        return dfs(start, end, visited);
    }

    /**
     * Parcurge reteaua in adancime (Depth-First Search) pentru a gasi o conexiune.
     * * @param current Locatia curenta explorata
     * @param target Locatia vizata
     * @param visited Array care tine evidenta locatiilor vizitate anterior
     * @return true in momentul in care s-a dat de target, altfel false la finalul parcurgerii
     */
    private boolean dfs(Location current, Location target, boolean[] visited) {
        if (current.equals(target)) return true;

        int currentIndex = -1;
        for (int i = 0; i < locCount; i++) {
            if (locations[i].equals(current)) {
                currentIndex = i;
                break;
            }
        }

        if (currentIndex == -1 || visited[currentIndex]) return false;
        visited[currentIndex] = true;

        for (int i = 0; i < roadCount; i++) {
            Road r = roads[i];
            if (r.getNode1().equals(current) && !visited[indexOf(r.getNode2())]) {
                if (dfs(r.getNode2(), target, visited)) return true;
            }
            if (r.getNode2().equals(current) && !visited[indexOf(r.getNode1())]) {
                if (dfs(r.getNode1(), target, visited)) return true;
            }
        }
        return false;
    }

    /**
     * Obtine pozitia (indexul) unei locatii in array-ul intern de locatii.
     * * @param loc Locatia cautata
     * @return Indicele intreg la care se afla, sau -1 daca nu apartine problemei
     */
    private int indexOf(Location loc) {
        for (int i = 0; i < locCount; i++) {
            if (locations[i].equals(loc)) return i;
        }
        return -1;
    }
}