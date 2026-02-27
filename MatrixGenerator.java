public class MatrixGenerator {

    public int[][] genereazaForma(int n, String forma) {
        int[][] matrice = new int[n][n];

        if (forma.equals("rectangle")) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    matrice[i][j] = 255;
                }
            }

            int margine = n / 4;
            for (int i = margine; i < n - margine; i++) {
                for (int j = margine; j < n - margine; j++) {
                    matrice[i][j] = 0;
                }
            }
        }

        if (forma.equals("circle")) {
            int centru = n / 2;
            int raza = n / 3;

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    int distantaX = i - centru;
                    int distantaY = j - centru;

                    if ((distantaX * distantaX) + (distantaY * distantaY) <= (raza * raza)) {
                        matrice[i][j] = 255;
                    }
                }
            }
        }
        return matrice;
    }

    public String construiesteImagine(int[][] matrice) {
        String rezultat = "";
        for (int i = 0; i < matrice.length; i++) {
            for (int j = 0; j < matrice[i].length; j++) {
                if (matrice[i][j] == 0) {
                    rezultat += "..";
                } else {
                    rezultat += "##";
                }
            }
            rezultat += "\n";
        }
        return rezultat;
    }
}