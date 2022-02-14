package coloruid.actions;

import coloruid.gui.GraphPanel;
import coloruid.core.Node;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class NewNodeAction extends AbstractAction {

    public NewNodeAction(String name) {
        super(name);
    }

    public void actionPerformed(ActionEvent e) {
        Node.selectNone(GraphPanel.getInstance().getNodes());
        Point p = GraphPanel.getInstance().getMousePt().getLocation();
        Color color = GraphPanel.getInstance().getControlPanel().getHueIcon().getColor();
        Node n = new Node(GraphPanel.id++, p, color);
        n.setSelected(true);
        GraphPanel.getInstance().getNodes().add(n);
        GraphPanel.getInstance().repaint();
    }
}
