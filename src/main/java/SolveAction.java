import lombok.SneakyThrows;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class SolveAction extends AbstractAction {
    public SolveAction(String name) {
        super(name);
    }

    @SneakyThrows
    public void actionPerformed(ActionEvent e){
        GraphPanel.getInstance().getTextArea().append("Solve function still not implemented!\n");
        GraphPanel.getInstance().exportCurrentFacts();
    }
}
