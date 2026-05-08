package org.example;

import java.util.ArrayList;
import java.util.List;

public class SharedMemory {
    private final List<String> logs = new ArrayList<>();

    public synchronized void addLog(String log) {
        logs.add(log);
    }

    public synchronized void printLogs() {
        System.out.println("Memorie partajată (Jurnal Roboți):");
        for (String log : logs) {
            System.out.println(" - " + log);
        }
    }
}