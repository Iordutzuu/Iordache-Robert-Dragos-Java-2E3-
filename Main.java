void main(String[] args) {
    if (args.length < 2) {
        System.out.println("Eroare. Foloseste argumente la rulare. Ex: 50 circle");
        return;
    }

    int n = Integer.parseInt(args[0]);
    String forma = args[1];

    long timpInceputNano = System.nanoTime();

    MatrixGenerator generator = new MatrixGenerator();
    int[][] matrice = generator.genereazaForma(n, forma);

    long timpSfarsitNano = System.nanoTime();


        System.out.print(generator.construiesteImagine(matrice));


    System.out.println("Timp de rulare in nanosecunde: " + (timpSfarsitNano - timpInceputNano));
}