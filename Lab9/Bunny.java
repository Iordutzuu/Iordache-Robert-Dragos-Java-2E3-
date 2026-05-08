package org.example;

import java.util.Random;

public class Bunny implements Runnable {
    private final Maze maze;
    private final int[] pos;
    private final Random rand = new Random();

    private int speed = 300;
    private boolean isPaused = false;

    public Bunny(Maze maze, int startX, int startY) {
        this.maze = maze;
        this.pos = new int[]{startX, startY};
        maze.spawn("B", pos);
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
        int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        while (maze.isGameRunning()) {
            try {
                checkPaused();

                int[] dir = directions[rand.nextInt(4)];
                maze.move("B", pos, dir[0], dir[1]);

                Thread.sleep(speed);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
}