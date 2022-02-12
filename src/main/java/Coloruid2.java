import javax.swing.*;
import java.awt.*;

public class Coloruid2 {
    public static void main(String[] args) {
        JFrame f = new JFrame("GraphPanel");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        GraphPanel gp = GraphPanel.getInstance();
        f.add(gp.getControlPanel(), BorderLayout.NORTH);
        f.add(new JScrollPane(gp), BorderLayout.CENTER);
        f.add(gp.getTextArea(),BorderLayout.EAST);
        gp.getTextArea().setEditable(false);

        f.getRootPane().setDefaultButton(gp.getControlPanel().getDefaultButton());
        f.pack();
        f.setLocationByPlatform(true);
        f.setVisible(true);
    }
}
