package Java_Project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FarmSimulation extends JPanel {
    private Farm farm;

    public FarmSimulation(Farm farm) {
        this.farm = farm;
        JFrame frame = new JFrame("Java_Project.Farm Simulation");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(this);
        frame.setSize(800, 600);
        frame.setVisible(true);

        Timer timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                farm.step();
                repaint();
            }
        });
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Cell[][] grid = farm.getGrid();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                Cell cell = grid[i][j];
                if (cell.hasCrop()) {
                    g.setColor(Color.GREEN);
                } else if (cell.getFarmer() != null) {
                    g.setColor(Color.BLUE);
                } else if (cell.getPest() != null) {
                    g.setColor(Color.RED);
                } else if (cell.getProtector() != null) {
                    g.setColor(Color.BLACK);
                } else {
                    g.setColor(Color.WHITE);
                }
                g.fillRect(i * 20, j * 20, 20, 20);
            }
        }
    }

    public static void main(String[] args) {
        Farm farm = new Farm(40, 30);
        new FarmSimulation(farm);
    }
}
