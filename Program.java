package Java_Project;

import javax.swing.*;

public class Program extends JFrame {

    private static final long serialVersionUID = 1L;
    private GUI gof;

    public Program() {
        setTitle("Hay Day");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        gof = new GUI(this);
        gof.initialize(getContentPane());

        setSize(1024, 768);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Program();
    }

}