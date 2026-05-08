package org.example;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class ConfigPanel extends JPanel {
    final MainFrame frame;
    JSpinner rowsSpinner, colsSpinner;
    JButton initBtn;

    public ConfigPanel(MainFrame frame) {
        this.frame = frame;
        init();
    }

    private void init() {
        add(new JLabel("Rows:"));
        rowsSpinner = new JSpinner(new SpinnerNumberModel(10, 2, 100, 1));
        add(rowsSpinner);

        add(new JLabel("Cols:"));
        colsSpinner = new JSpinner(new SpinnerNumberModel(10, 2, 100, 1));
        add(colsSpinner);

        initBtn = new JButton("Draw Cells");
        initBtn.addActionListener(this::initGrid);
        add(initBtn);
    }

    private void initGrid(ActionEvent e) {
        int rows = (Integer) rowsSpinner.getValue();
        int cols = (Integer) colsSpinner.getValue();
        frame.drawingPanel.initGrid(rows, cols);
    }
}