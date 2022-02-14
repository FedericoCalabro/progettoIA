package coloruid.actions;

import coloruid.gui.GraphPanel;
import coloruid.gui.MyChooserPanel;
import coloruid.core.Node;

import javax.swing.*;
import javax.swing.colorchooser.AbstractColorChooserPanel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ColorAction extends AbstractAction {

    public ColorAction(String name) {
        super(name);
    }

    public void actionPerformed(ActionEvent e) {

        Color color = GraphPanel.getInstance().getControlPanel().getHueIcon().getColor();

        JColorChooser chooser = new JColorChooser(Color.blue);
        AbstractColorChooserPanel[] oldPanels = chooser.getChooserPanels();
        chooser.setPreviewPanel(new JPanel());
        for (int i = 0; i < oldPanels.length; i++) {
            chooser.removeChooserPanel(oldPanels[i]);
        }

        MyChooserPanel panel = new MyChooserPanel();
        chooser.addChooserPanel(panel);

        ActionListener okList = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Color newColor = chooser.getColor();
                if (newColor != null) {
                    Node.updateColor(GraphPanel.getInstance().getNodes(), newColor);
                    GraphPanel.getInstance().getControlPanel().getHueIcon().setColor(newColor);
                    GraphPanel.getInstance().getControlPanel().repaint();
                    GraphPanel.getInstance().repaint();
//                    repaint();
                }
            }
        };

        JDialog dialog = JColorChooser.createDialog(GraphPanel.getInstance(), "Choose a color", false, chooser, okList, null);
        dialog.setVisible(true);
    }
}
