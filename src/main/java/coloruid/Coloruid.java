package coloruid;

import coloruid.gui.GraphPanel;

import javax.swing.*;
import java.awt.*;

public class Coloruid {
    public static void main(String[] args) {
        JFrame f = new JFrame("coloruid.gui.GraphPanel");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        GraphPanel gp = GraphPanel.getInstance();
        f.add(gp.getControlPanel(), BorderLayout.NORTH);
        f.add(new JScrollPane(gp), BorderLayout.CENTER);
        //f.add(new JScrollPane(gp.getTextArea()),BorderLayout.EAST);
        f.add(gp.getScrollPane(),BorderLayout.EAST);
        gp.getTextArea().setEditable(false);
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, gp, gp.getScrollPane());
        splitPane.setOneTouchExpandable(true);
        splitPane.setDividerLocation(600);
        gp.setMinimumSize(new Dimension(100,50));
        gp.getScrollPane().setMinimumSize(new Dimension(100,50));

        f.add(splitPane, BorderLayout.CENTER);
        f.getRootPane().setDefaultButton(gp.getControlPanel().getDefaultButton());
        f.pack();
        f.setLocationByPlatform(true);
        f.setVisible(true);
    }
}
