public class Main {
    public static void main(String[] args) {
        City iasi = new City("Iasi", "Romania", 94.0, 300000);
        City vaslui = new City("Vaslui", "Romania", 68.0, 55000);
        City bacau = new City("Bacau", "Romania", 43.0, 140000);
        Airport aeroportIasi = new Airport("Aeroportul Iasi", "Romania", 2.5, 3);
        GasStation petrom = new GasStation("Petrom Vaslui", "Romania", 0.5, 7.15);
        City cluj = new City("Cluj-Napoca", "Romania", 179.5, 320000);

        Road a7 = new Road("Autostrada A7", 130.0, 130.0, RoadType.HIGHWAY, iasi, bacau);
        Road dn24 = new Road("Drum National 24", 70.0, 90.0, RoadType.COUNTRY, iasi, vaslui);
        Road dj248 = new Road("Drum Judetean", 5.0, 50.0, RoadType.COUNTRY, vaslui, petrom);
        Road centura = new Road("Centura Iasi", 15.0, 80.0, RoadType.EXPRESS, iasi, aeroportIasi);

        Problem map = new Problem();
        System.out.println(iasi);
        System.out.println(petrom);
        System.out.println("Locatii care se repeta:");
        map.addLocation(iasi);
        map.addLocation(vaslui);
        map.addLocation(bacau);
        map.addLocation(aeroportIasi);
        map.addLocation(petrom);
        map.addLocation(cluj);
        map.addLocation(iasi);



        System.out.println("\nDrumuri care se repeta");
        map.addRoad(a7);
        map.addRoad(dn24);
        map.addRoad(dj248);
        map.addRoad(centura);


        System.out.println("\nValiditatea");
        System.out.println("Reteaua este valida? " + map.isValid());

        System.out.println("\nTest rute");
        System.out.println("Pot ajunge de la Iasi la Vaslui? " + map.canReach(iasi, vaslui));
        System.out.println("Pot ajunge de la Bacau la Aeroportul Iasi? " + map.canReach(bacau, aeroportIasi));
        System.out.println("Pot ajunge de la Iasi la Petrom Vaslui? " + map.canReach(iasi, petrom));
        System.out.println("Pot ajunge de la Iasi la Cluj-Napoca? " + map.canReach(iasi, cluj));
    }
}