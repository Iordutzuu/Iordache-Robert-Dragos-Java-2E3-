package org.example;

public class Maze {
    private final Cell[][] grid;
    private final int width, height;
    private volatile boolean isRunning = true;

    public Maze(int width, int height) {
        this.width = width;
        this.height = height;
        grid = new Cell[height][width];

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                boolean isExit = (i == height - 1 && j == width - 1);
                grid[i][j] = new Cell(j, i, isExit);
            }
        }
    }

    public boolean isGameRunning() {
        return isRunning;
    }

    public void forceStop() {
        isRunning = false;
    }

    public synchronized void spawn(String id, int[] pos) {
        grid[pos[1]][pos[0]].occupant = id;
    }

    public synchronized boolean move(String entityId, int[] currentPos, int dx, int dy) {
        if (!isRunning) return false;

        int newX = currentPos[0] + dx;
        int newY = currentPos[1] + dy;

        if (newX >= 0 && newX < width && newY >= 0 && newY < height) {
            Cell target = grid[newY][newX];
            Cell current = grid[currentPos[1]][currentPos[0]];

            if (entityId.equals("B")) {
                if (target.occupant != null && target.occupant.startsWith("R")) {
                    System.out.println("\n>>> JOC TERMINAT: Bunny a sărit direct pe " + target.occupant + "!");
                    isRunning = false;
                    return true;
                }
                if (target.isExit) {
                    System.out.println("\n>>> JOC TERMINAT: Bunny a găsit ieșirea și a scăpat!");
                    isRunning = false;
                    return true;
                }
            } else if (entityId.startsWith("R")) {
                if ("B".equals(target.occupant)) {
                    System.out.println("\n>>> JOC TERMINAT: Robotul " + entityId + " a prins Iepurele!");
                    isRunning = false;
                    return true;
                }
                if (target.occupant != null) {
                    return false;
                }
            }

            current.occupant = null;
            target.occupant = entityId;
            currentPos[0] = newX;
            currentPos[1] = newY;

            return true;
        }
        return false;
    }

    public synchronized void display(long elapsedSeconds) {
        if (!isRunning) return;
        System.out.println("\n--- Stare Labirint [ Timp scurs: " + elapsedSeconds + "s ] ---");
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (grid[i][j].occupant != null) {
                    System.out.printf("[%2s]", grid[i][j].occupant);
                } else if (grid[i][j].isExit) {
                    System.out.print("[ E]");
                } else {
                    System.out.print("[  ]");
                }
            }
            System.out.println();
        }
    }
}