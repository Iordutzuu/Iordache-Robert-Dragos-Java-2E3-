package org.example;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Random;

public class DrawingPanel extends JPanel {
    final MainFrame frame;
    int rows = 10, cols = 10;
    Cell[][] grid;
    int cellWidth, cellHeight;

    public DrawingPanel(MainFrame frame) {
        this.frame = frame;
        initGrid(rows, cols);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (grid == null) return;

                int pad = 20;
                int boardWidth = getWidth() - 2 * pad;
                int boardHeight = getHeight() - 2 * pad;

                int xOff = (getWidth() - cols * cellWidth) / 2;
                int yOff = (getHeight() - rows * cellHeight) / 2;

                int clickX = e.getX();
                int clickY = e.getY();

                if (clickX < xOff || clickX >= xOff + boardWidth ||
                        clickY < yOff || clickY >= yOff + boardHeight) {
                    return;
                }

                int col = (clickX - xOff) / cellWidth;
                int row = (clickY - yOff) / cellHeight;

                int relX = (clickX - xOff) % cellWidth;
                int relY = (clickY - yOff) % cellHeight;

                int distTop = relY;
                int distBottom = cellHeight - relY;
                int distLeft = relX;
                int distRight = cellWidth - relX;

                int minDist = Math.min(Math.min(distTop, distBottom), Math.min(distLeft, distRight));

                Cell cell = grid[row][col];

                if (minDist == distTop) cell.top = !cell.top;
                else if (minDist == distBottom) cell.bottom = !cell.bottom;
                else if (minDist == distLeft) cell.left = !cell.left;
                else if (minDist == distRight) cell.right = !cell.right;

                repaint();
            }
        });
    }

    public void initGrid(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        grid = new Cell[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                grid[i][j] = new Cell(i, j);
            }
        }
        repaint();
    }

    public void createRandomMaze() {
        Random rand = new Random();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (rand.nextBoolean()) grid[i][j].top = false;
                if (rand.nextBoolean()) grid[i][j].right = false;
                if (rand.nextBoolean()) grid[i][j].bottom = false;
                if (rand.nextBoolean()) grid[i][j].left = false;
            }
        }
        repaint();
    }

    public void resetMaze() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                grid[i][j].top = true;
                grid[i][j].right = true;
                grid[i][j].bottom = true;
                grid[i][j].left = true;
            }
        }
        repaint();
    }

    public void validateMaze() {
        if (grid == null || rows == 0 || cols == 0) return;
        boolean[][] visited = new boolean[rows][cols];
        boolean isTraversable = dfs(0, 0, visited);

        if (isTraversable) {
            JOptionPane.showMessageDialog(this, "Labirintul are soluție din colțul stânga-sus până în dreapta-jos!", "Validare Reușită", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Labirintul NU poate fi parcurs (este blocat).", "Eroare Validare", JOptionPane.ERROR_MESSAGE);
        }
    }

    private boolean dfs(int r, int c, boolean[][] visited) {
        if (r == rows - 1 && c == cols - 1) return true;
        visited[r][c] = true;
        Cell current = grid[r][c];

        if (!current.top && r > 0 && !visited[r - 1][c] && !grid[r - 1][c].bottom) {
            if (dfs(r - 1, c, visited)) return true;
        }
        if (!current.bottom && r < rows - 1 && !visited[r + 1][c] && !grid[r + 1][c].top) {
            if (dfs(r + 1, c, visited)) return true;
        }
        if (!current.left && c > 0 && !visited[r][c - 1] && !grid[r][c - 1].right) {
            if (dfs(r, c - 1, visited)) return true;
        }
        if (!current.right && c < cols - 1 && !visited[r][c + 1] && !grid[r][c + 1].left) {
            if (dfs(r, c + 1, visited)) return true;
        }
        return false;
    }

    public void exportPNG() {
        try {
            BufferedImage image = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2d = image.createGraphics();
            this.printAll(g2d);
            g2d.dispose();

            File file = new File("maze.png");
            ImageIO.write(image, "PNG", file);

            JOptionPane.showMessageDialog(this, "Imaginea a fost salvată ca 'maze.png'!", "Export Reușit", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Eroare la export: " + ex.getMessage(), "Eroare", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void saveMaze() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("maze.ser"))) {
            oos.writeObject(grid);
            oos.writeInt(rows);
            oos.writeInt(cols);
            JOptionPane.showMessageDialog(this, "Starea labirintului a fost salvată în 'maze.ser'!", "Salvare", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Eroare la salvare: " + ex.getMessage(), "Eroare", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void loadMaze() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("maze.ser"))) {
            grid = (Cell[][]) ois.readObject();
            rows = ois.readInt();
            cols = ois.readInt();
            repaint();
            JOptionPane.showMessageDialog(this, "Labirintul a fost încărcat cu succes!", "Încărcare", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException | ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(this, "Nu am găsit nicio salvare sau a apărut o eroare: " + ex.getMessage(), "Eroare", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        if (grid == null) return;

        int pad = 20;
        int boardWidth = getWidth() - 2 * pad;
        int boardHeight = getHeight() - 2 * pad;

        cellWidth = boardWidth / cols;
        cellHeight = boardHeight / rows;

        int xOff = (getWidth() - cols * cellWidth) / 2;
        int yOff = (getHeight() - rows * cellHeight) / 2;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int x = xOff + j * cellWidth;
                int y = yOff + i * cellHeight;

                if (i == 0 && j == 0) {
                    g2d.setColor(new Color(144, 238, 144));
                } else if (i == rows - 1 && j == cols - 1) {
                    g2d.setColor(new Color(255, 182, 193));
                } else {
                    g2d.setColor(new Color(220, 240, 255));
                }
                g2d.fillRect(x, y, cellWidth, cellHeight);

                g2d.setColor(Color.DARK_GRAY);
                g2d.setStroke(new BasicStroke(2));

                if (grid[i][j].top) g2d.drawLine(x, y, x + cellWidth, y);
                if (grid[i][j].bottom) g2d.drawLine(x, y + cellHeight, x + cellWidth, y + cellHeight);
                if (grid[i][j].left) g2d.drawLine(x, y, x, y + cellHeight);
                if (grid[i][j].right) g2d.drawLine(x + cellWidth, y, x + cellWidth, y + cellHeight);
            }
        }
    }
}