package org.example;

import java.util.Random;

public class Robot implements Runnable {
    private final Maze maze;
    private final SharedMemory memory;
    private final String id;
    private final int[] pos;
    private final Random rand = new Random();

    private int speed = 400;
    private boolean isPaused = false;

    public Robot(String id, Maze maze, SharedMemory memory, int startX, int startY) {
        this.id = id;
        this.maze = maze;
        this.memory = memory;
        this.pos = new int[]{startX, startY};
        maze.spawn(id, pos);
    }

    public synchronized void pauseEntity() { isPaused = true; }
    public synchronized void resumeEntity() { isPaused = false; notify(); }
    public synchronized void modifySpeed(int delta) { speed = Math.max(50, speed + delta); }

    private synchronized void checkPaused() throws InterruptedException {
        while (isPaused) {
            wait();
        }
    }

    @Override
    public void run() {
        int[][] directions = {{0, -1}, {1, 0}, {0, 1}, {-1, 0}};
        int currentDirIndex = rand.nextInt(4);

        while (maze.isGameRunning()) {
            try {
                checkPaused();

                int dx = directions[currentDirIndex][0];
                int dy = directions[currentDirIndex][1];

                boolean moved = maze.move(id, pos, dx, dy);

                if (moved) {
                    if (rand.nextInt(10) > 7) {
                        memory.addLog(id + " a patrulat coordonatele: x=" + pos[0] + ", y=" + pos[1]);
                    }
                } else {
                    currentDirIndex = (currentDirIndex + 1) % 4;
                }

                Thread.sleep(speed);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
}