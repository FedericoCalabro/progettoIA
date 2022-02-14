package coloruid.actions;

import coloruid.gui.GraphPanel;
import coloruid.utils.Utils;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class ClearAction extends AbstractAction {

    public ClearAction(String name) {
        super(name);
    }

    public void actionPerformed(ActionEvent e) {
        GraphPanel.id = 0;
        GraphPanel.getInstance().getNodes().clear();
        GraphPanel.getInstance().getEdges().clear();
        GraphPanel.getInstance().repaint();

        Utils.updateStepLabel(0);
    }
}
