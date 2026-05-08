package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Se inițializează jocul...");

        Maze maze = new Maze(10, 10);
        SharedMemory memory = new SharedMemory();

        Bunny bunny = new Bunny(maze, 0, 0);
        Robot r1 = new Robot("R1", maze, memory, 9, 0);
        Robot r2 = new Robot("R2", maze, memory, 0, 9);

        Thread bThread = new Thread(bunny);
        Thread r1Thread = new Thread(r1);
        Thread r2Thread = new Thread(r2);

        Thread commandListener = new Thread(() -> {
            Scanner scanner = new Scanner(System.in);
            System.out.println(">>> COMENZI: [tinta] [actiune]");
            System.out.println(">>> Tinte: b, r1, r2, all");
            System.out.println(">>> Actiuni: stop, resume, faster, slower");

            while (maze.isGameRunning()) {
                if (scanner.hasNextLine()) {
                    String input = scanner.nextLine().trim().toLowerCase();
                    if (input.isEmpty()) continue;

                    String[] parts = input.split("\\s+");

                    if (parts.length == 2) {
                        String target = parts[0];
                        String cmd = parts[1];

                        System.out.println("\n[SISTEM] >>> Execut comanda '" + cmd + "' pentru '" + target + "' <<<");

                        boolean applyB = target.equals("b") || target.equals("all");
                        boolean applyR1 = target.equals("r1") || target.equals("all");
                        boolean applyR2 = target.equals("r2") || target.equals("all");

                        if (cmd.equals("stop")) {
                            if (applyB) bunny.pauseEntity();
                            if (applyR1) r1.pauseEntity();
                            if (applyR2) r2.pauseEntity();
                        } else if (cmd.equals("resume")) {
                            if (applyB) bunny.resumeEntity();
                            if (applyR1) r1.resumeEntity();
                            if (applyR2) r2.resumeEntity();
                        } else if (cmd.equals("faster")) {
                            if (applyB) bunny.modifySpeed(-150);
                            if (applyR1) r1.modifySpeed(-150);
                            if (applyR2) r2.modifySpeed(-150);
                        } else if (cmd.equals("slower")) {
                            if (applyB) bunny.modifySpeed(150);
                            if (applyR1) r1.modifySpeed(150);
                            if (applyR2) r2.modifySpeed(150);
                        } else {
                            System.out.println("[SISTEM] Actiune necunoscuta. Foloseste: stop, resume, faster, slower.");
                        }
                    } else {
                        System.out.println("[SISTEM] Format invalid! Foloseste: [tinta] [actiune] (ex: all stop)");
                    }
                }
            }
        });
        commandListener.setDaemon(true);

        long startTime = System.currentTimeMillis();
        long TIME_LIMIT = 20;

        Thread daemonManager = new Thread(() -> {
            while (maze.isGameRunning()) {
                try {
                    Thread.sleep(1000);
                    long elapsedSeconds = (System.currentTimeMillis() - startTime) / 1000;

                    if (elapsedSeconds >= TIME_LIMIT) {
                        System.out.println("\n>>> TIMP EXPIRAT! (" + TIME_LIMIT + "s). Jocul a fost oprit de Manager.");
                        maze.forceStop();
                        break;
                    }

                    maze.display(elapsedSeconds);

                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        });
        daemonManager.setDaemon(true);

        bThread.start();
        r1Thread.start();
        r2Thread.start();
        commandListener.start();
        daemonManager.start();

        try {
            bThread.join();
            r1Thread.join();
            r2Thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Simulare încheiată.");
        memory.printLogs();
        System.exit(0);
    }
}