package Java_Project;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serial;

public class GUI extends JPanel implements ActionListener, ChangeListener {
    @Serial
    private static final long serialVersionUID = 1L;
    private Timer     timer;
    private Board     board;
    private JButton   start;
    private JButton   clear;
    private JButton   add;
    private JButton wheat;
    private JButton ladyBug;
    private JButton cockroach;
    private JSlider   pred;
    private JComboBox figures;
    private JFrame    frame;
    private       int     iterNum   = 0;
    private final int     maxDelay  = 500;
    private final int     initDelay = 100;
    private       boolean running   = false;

    public GUI(JFrame jf) {
        frame = jf;
        timer = new Timer(initDelay, this);
        timer.stop();
    }

    public void initialize(Container container) {
        container.setLayout(new BorderLayout());
        container.setSize(new Dimension(1024, 768));

        JPanel buttonPanel = new JPanel();

        start = new JButton("Start");
        start.setActionCommand("Start");
        start.setToolTipText("Starts clock");
        start.addActionListener(this);

        clear = new JButton("Clear");
        clear.setActionCommand("clear");
        clear.setToolTipText("Clears the board");
        clear.addActionListener(this);

        add = new JButton("Add");
        add.setActionCommand("add");
        add.setToolTipText("Adds predefined pattern");
        add.addActionListener(this);

        wheat = new JButton("Wheat");
        wheat.setActionCommand("wheat");
        wheat.setToolTipText("Changes to wheat adding");
        wheat.addActionListener(this);

        ladyBug = new JButton("LadyBug");
        ladyBug.setActionCommand("ladyBug");
        ladyBug.setToolTipText("Changes to ladyBug adding");
        ladyBug.addActionListener(this);

        cockroach = new JButton("Cockroach");
        cockroach.setActionCommand("cockroach");
        cockroach.setToolTipText("Changes to cockroach adding");
        cockroach.addActionListener(this);

        figures = new JComboBox(Pattern.setPattern());
        figures.setToolTipText("Pattern choice");

        pred = new JSlider();
        pred.setMinimum(0);
        pred.setMaximum(maxDelay);
        pred.setToolTipText("Time speed");
        pred.addChangeListener(this);
        pred.setValue(maxDelay - timer.getDelay());

        buttonPanel.add(start);
        buttonPanel.add(clear);
        buttonPanel.add(figures);
        buttonPanel.add(add);
        buttonPanel.add(wheat);
        buttonPanel.add(cockroach);
        buttonPanel.add(ladyBug);
        buttonPanel.add(pred);

        board = new Board(1024, 768 - buttonPanel.getHeight());
        container.add(board, BorderLayout.CENTER);
        container.add(buttonPanel, BorderLayout.SOUTH);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(timer)) {
            iterNum++;
            frame.setTitle("Game of Life (" + Integer.toString(iterNum) + " iteration)");
            board.iteration();
        } else {
            String command = e.getActionCommand();
            if (command.equals("Start")) {
                if (!running) {
                    timer.start();
                    start.setText("Pause");
                } else {
                    timer.stop();
                    start.setText("Start");
                }
                running = !running;
                clear.setEnabled(true);

            } else if (command.equals("clear")) {
                iterNum = 0;
                timer.stop();
                start.setEnabled(true);
                board.clear();
                frame.setTitle("Cellular Automata Toolbox");
            } else if (command.equals("add")) {
                board.loadPattern((Pattern) figures.getSelectedItem());
            } else if (command.equals("wheat")) {
                board.setState(1);
            } else if (command.equals("cockroach")) {
                board.setState(2);
            } else if (command.equals("ladyBug")) {
                board.setState(3);
            }

        }
    }

    public void stateChanged(ChangeEvent e) {
        timer.setDelay(maxDelay - pred.getValue());
    }
}