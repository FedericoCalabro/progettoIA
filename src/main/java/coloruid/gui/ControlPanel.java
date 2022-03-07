package coloruid.gui;

import coloruid.utils.Utils;
import coloruid.actions.*;
import coloruid.dlv.DlvExecutor;
import lombok.Data;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
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
    private JComboBox<String> comboBoxEncoder = new JComboBox<>();
    private JComboBox<String> mode = new JComboBox<>();

    SpinnerNumberModel roundModel = new SpinnerNumberModel(4, 1, 20, 1);
    JLabel maxRoundsLabel = new JLabel("MAX ROUNDS: ");
    private JSpinner maxRoundsSpinner = new JSpinner(roundModel);

    JLabel currentMoveLabel = new JLabel("STEP: 0/" + maxRoundsSpinner.getValue());

    public ControlPanel() {

        this.comboBoxEncoder.addItem(DlvExecutor.ENCODING_STD);
        this.comboBoxEncoder.addItem(DlvExecutor.ENCODING_CUST);
        this.comboBoxEncoder.setSelectedIndex(0);

        this.mode.addItem("Building Mode");
        this.mode.addItem("Solving Mode");
        this.mode.setSelectedIndex(0);

        this.setLayout(new GridLayout(1,3));
        this.setBackground(Color.lightGray);

        //this.add(defaultButton);
        //this.add(new JButton(clearAll));
        //this.add(new JButton(color));

        JPanel left = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel center = new JPanel();
        JPanel right = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        left.add(new JButton(load));
        left.add(new JButton(solve));
        center.add(new JLabel("CURRENT COLOR:"));
        center.add(new JLabel(hueIcon));
        center.add(maxRoundsLabel);
        center.add(maxRoundsSpinner);

        this.maxRoundsSpinner.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                Utils.updateStepLabel();
            }
        });


        center.add(currentMoveLabel);
        right.add(comboBoxEncoder);
        right.add(mode);

        popup.add(new JMenuItem(newNode));
        popup.add(new JMenuItem(color));
        popup.add(new JMenuItem(connect));
        popup.add(new JMenuItem(delete));
        popup.add(new JMenuItem(clearAll));

        this.add(left, FlowLayout.LEFT);
        this.add(center, FlowLayout.CENTER);
        this.add(right, FlowLayout.RIGHT);
    }

}
