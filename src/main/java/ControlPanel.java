import lombok.Data;

import javax.swing.*;
import java.awt.*;

@Data
public class ControlPanel extends JToolBar {

    private Action newNode = new NewNodeAction("New");
    private Action clearAll = new ClearAction("Clear");
    private Action color = new ColorAction("Color");
    private Action connect = new ConnectAction("Connect");
    private Action delete = new DeleteAction("Delete");
    private Action solve = new SolveAction("Solve");
    private Action load = new LoadLevelAction("Load Level");
    private JButton defaultButton = new JButton(newNode);
    private ColorIcon hueIcon = new ColorIcon(Color.blue);
    private JPopupMenu popup = new JPopupMenu();

    SpinnerNumberModel roundModel = new SpinnerNumberModel(4, 1, 20, 1);
    JLabel maxRoundsLabel = new JLabel("MAX ROUNDS: ");
    private JSpinner maxRoundsSpinner = new JSpinner(roundModel);

    JLabel currentMoveLabel = new JLabel("STEP: 0/" + maxRoundsSpinner.getValue());

    public ControlPanel() {

        this.setLayout(new FlowLayout(FlowLayout.LEFT));
        this.setBackground(Color.lightGray);

        this.add(defaultButton);
        this.add(new JButton(clearAll));
        this.add(new JButton(color));
        this.add(new JLabel(hueIcon));
        this.add(new JButton(solve));
        this.add(maxRoundsLabel);
        this.add(maxRoundsSpinner);
        this.add(new JButton(load));
        this.add(currentMoveLabel);

        popup.add(new JMenuItem(newNode));
        popup.add(new JMenuItem(color));
        popup.add(new JMenuItem(connect));
        popup.add(new JMenuItem(delete));
    }

}
