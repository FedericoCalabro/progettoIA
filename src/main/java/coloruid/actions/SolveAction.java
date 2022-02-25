package coloruid.actions;

import coloruid.dlv.DlvExecutor;
import coloruid.gui.GraphPanel;
import lombok.SneakyThrows;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class SolveAction extends AbstractAction {
    public SolveAction(String name) {
        super(name);
    }

    @SneakyThrows
    public void actionPerformed(ActionEvent e){
        GraphPanel.getInstance().exportCurrentFacts();
        GraphPanel.getInstance().getControlPanel().getMode().setSelectedIndex(1);
        DlvExecutor.execute();
    }
}
