package org.example;

import javax.swing.*;

public class ControlPanel extends JPanel {
    final MainFrame frame;
    JButton createBtn, resetBtn, validateBtn, exportBtn, saveBtn, loadBtn, exitBtn;

    public ControlPanel(MainFrame frame) {
        this.frame = frame;
        init();
    }

    private void init() {
        createBtn = new JButton("Create");
        resetBtn = new JButton("Reset");
        validateBtn = new JButton("Validate");
        exportBtn = new JButton("Export PNG");
        saveBtn = new JButton("Save");
        loadBtn = new JButton("Load");
        exitBtn = new JButton("Exit");

        createBtn.addActionListener(e -> frame.drawingPanel.createRandomMaze());
        resetBtn.addActionListener(e -> frame.drawingPanel.resetMaze());
        validateBtn.addActionListener(e -> frame.drawingPanel.validateMaze());
        exportBtn.addActionListener(e -> frame.drawingPanel.exportPNG());
        saveBtn.addActionListener(e -> frame.drawingPanel.saveMaze());
        loadBtn.addActionListener(e -> frame.drawingPanel.loadMaze());
        exitBtn.addActionListener(e -> System.exit(0));

        add(createBtn);
        add(resetBtn);
        add(validateBtn);
        add(exportBtn);
        add(saveBtn);
        add(loadBtn);
        add(exitBtn);
    }
}